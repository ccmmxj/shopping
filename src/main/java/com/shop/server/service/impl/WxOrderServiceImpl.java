package com.shop.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.server.common.Context;
import com.shop.server.dto.TableData;
import com.shop.server.dto.WhereDto;
import com.shop.server.dto.WxOrderDto;
import com.shop.server.enums.OrderStatus;
import com.shop.server.mapper.WxOrderMapper;
import com.shop.server.model.ManageCompany;
import com.shop.server.model.WxOrder;
import com.shop.server.model.WxOrderProduct;
import com.shop.server.model.WxProduct;
import com.shop.server.service.ManageCompanyService;
import com.shop.server.service.WxOrderProductService;
import com.shop.server.service.WxOrderService;
import com.shop.server.service.WxProductService;
import com.shop.server.utils.ObjectUtil;
import com.shop.server.utils.TableDataFactory;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WxOrderServiceImpl implements WxOrderService {
    private final static Logger logger = LoggerFactory.getLogger(WxOrderServiceImpl.class);
    @Autowired
    private WxOrderMapper wxOrderMapper;
    @Autowired
    private WxOrderProductService wxOrderProductService;
    @Autowired
    private WxProductService wxProductService;
    @Autowired
    private ManageCompanyService manageCompanyService;

    @Override
    public WxOrder findOne(Long id) {
        return wxOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int saveOrUpdate(WxOrder wxOrder) {
        if (wxOrder.getId() != null) return update(wxOrder);
        return save(wxOrder);
    }

    @Override
    @Transactional
    public int save(WxOrder wxOrder) {
        Date now = new Date();
        wxOrder.setGmtCreated(now);
        wxOrder.setGmtModified(now);
        return wxOrderMapper.insertSelective(wxOrder);
    }

    @Override
    @Transactional
    public int update(WxOrder wxOrder) {
        Date now = new Date();
        wxOrder.setGmtModified(now);
        return wxOrderMapper.updateByPrimaryKeySelective(wxOrder);
    }

    @Override
    public List<WxOrder> findAll() {
        return wxOrderMapper.selectAll();
    }

    @Override
    @Transactional
    public WxOrderDto saveOrderDto(WxOrderDto wxOrderDto, List<WxOrderProduct> wxOrderProducts) {
        ManageCompany manageCompany = manageCompanyService.findOne(wxOrderDto.getCompanyId());
        wxOrderDto.setStatus(OrderStatus.NOT_SENT.status());
        wxOrderDto.setOid(Context.getOrderId(manageCompany));
        int count = saveOrUpdate(wxOrderDto);
        if (count > 0) {
            wxOrderDto.setWxOrderProducts(wxOrderProducts);
            for (WxOrderProduct wxOrderProduct : wxOrderProducts) {
                wxOrderProduct.setOrderId(wxOrderDto.getId());
                wxOrderProductService.saveOrUpdate(wxOrderProduct);
                WxProduct wxProduct = wxProductService.findOne(wxOrderProduct.getProductId());
                wxProduct.setHot(wxProduct.getHot()+1);
                wxProductService.update(wxProduct);
            }
        }
        return wxOrderDto;
    }

    @Override
    public WxOrder delOrder(Long id, Long companyId) {
        Sqls sqls = WhereDto.InIsDeletedWhere(companyId);
        WxOrder wxOrder = wxOrderMapper.selectOneByExample(Example.builder(WxOrder.class).where(sqls.andEqualTo("id", id)).build());
        if (wxOrder == null) {
            return null;
        }
        wxOrder.setIsDeleted((byte) 1);
        wxOrder.setCompanyId(companyId);
        logger.info("delOrder after =====> {}", JSON.toJSONString(wxOrder));
        int count = saveOrUpdate(wxOrder);
        logger.info("delOrder before =====> {}", JSON.toJSONString(wxOrder));
        if (count > 0) {
            return wxOrder;
        }
        return null;
    }

    @Override
    public TableData<WxOrderDto> findWxOrderDtoTable(TableData<WxOrderDto> tableData, Long companyId, String oid) {
        PageHelper.startPage(tableData.getPageNumber(), tableData.getPageSize());
        Sqls sqls = WhereDto.InIsDeletedWhere(companyId);
        if (StringUtils.isNotBlank(oid)) {
            sqls.andLike("oid", "%" + oid + "%");
        }
        Example.Builder builder = Example.builder(WxOrder.class).where(sqls).orderByDesc("gmtModified");
        if (StringUtils.isNotBlank(tableData.getSortName()) && StringUtils.isNotBlank(tableData.getSortOrder())) {
            if ("desc".equals(tableData.getSortOrder())) {
                builder = builder.orderByDesc(tableData.getSortName());
            } else {
                builder = builder.orderByAsc(tableData.getSortName());
            }
        }
        Example example = builder.build();
        List<WxOrder> wxOrders = wxOrderMapper.selectByExample(example);
        PageInfo<WxOrder> page = new PageInfo<>(wxOrders);
        List<WxOrderDto> wxOrderDtos = newInstaceByWxOrder(wxOrders);
        wxOrderProductService.setProductsByOrder(wxOrderDtos);
        return TableDataFactory.newInstaceSuccessResult(tableData, page, wxOrderDtos);
    }

    private List<WxOrderDto> newInstaceByWxOrder(List<WxOrder> wxOrders){
        List<WxOrderDto> wxOrderDtos = new ArrayList<>();
        wxOrders.stream().forEach(v ->{
            wxOrderDtos.add(newInstaceByOrder(v));
        });
        return wxOrderDtos;
    }

    private WxOrderDto newInstaceByOrder(WxOrder wxOrder){
        WxOrderDto wxOrderDto = new WxOrderDto();
        try {
            ObjectUtil.getDto(wxOrder,wxOrderDto);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return wxOrderDto;
    }
    @Override
    public WxOrder upStatus(Long id, String type, Long companyId) {
        Sqls sqls = WhereDto.InIsDeletedWhere(companyId);
        WxOrder wxOrder = wxOrderMapper.selectOneByExample(Example.builder(WxOrder.class).where(sqls.andEqualTo("id",id)).build());
        if(wxOrder == null){
            return null;
        }
        wxOrder.setCompanyId(companyId);
        if(OrderStatus.NOT_SENT.name().equals(type)){
            wxOrder.setStatus(OrderStatus.NOT_SENT.status());
        }else if(OrderStatus.BEGIN_SENT.name().equals(type)){
            wxOrder.setStatus(OrderStatus.BEGIN_SENT.status());
        }else if(OrderStatus.ALREADY_SENT.name().equals(type)){
            wxOrder.setStatus(OrderStatus.ALREADY_SENT.status());
        }
        logger.info("upStatus after =====> {}", JSON.toJSONString(wxOrder));
        int count = saveOrUpdate(wxOrder);
        logger.info("upStatus before =====> {}", JSON.toJSONString(wxOrder));
        if(count>0) {
            return wxOrder;
        }
        return null;
    }

    @Override
    public WxOrder recOrder(Long id, Long companyId) {
        Sqls sqls = WhereDto.InIsDeletedWhere(companyId);
        WxOrder wxOrder = wxOrderMapper.selectOneByExample(Example.builder(WxOrder.class).where(sqls.andEqualTo("id",id)).build());
        if(wxOrder == null){
            return null;
        }
        wxOrder.setIsDeleted((byte)0);
        wxOrder.setCompanyId(companyId);
        logger.info("recOrder after =====> {}", JSON.toJSONString(wxOrder));
        int count = saveOrUpdate(wxOrder);
        logger.info("recOrder before =====> {}", JSON.toJSONString(wxOrder));
        if(count>0) {
            return wxOrder;
        }
        return null;
    }
}
