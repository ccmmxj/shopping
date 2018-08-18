package com.shop.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.server.dto.TableData;
import com.shop.server.dto.WhereDto;
import com.shop.server.mapper.WxProductMapper;
import com.shop.server.model.WxProduct;
import com.shop.server.service.WxProductService;
import com.shop.server.utils.Result;
import com.shop.server.utils.TableDataFactory;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.Date;
import java.util.List;

@Service
public class WxProductServiceImpl implements WxProductService {
    private final static Logger logger = LoggerFactory.getLogger(WxProductServiceImpl.class);
    private final String ALL_TYPE = "全部";
    private final String ALL_ORDER = "综合";

    @Autowired
    private WxProductMapper wxProductMapper;
    @Override
    public WxProduct findOne(Long id) {
        return wxProductMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int saveOrUpdate(WxProduct wxProduct) {
        if(wxProduct.getId() != null) return update(wxProduct);
        return save(wxProduct);
    }

    @Override
    @Transactional
    public int save(WxProduct wxProduct) {
        Date now = new Date();
        wxProduct.setGmtCreated(now);
        wxProduct.setGmtModified(now);
        return wxProductMapper.insertSelective(wxProduct);
    }

    @Override
    @Transactional
    public int update(WxProduct wxProduct) {
        Date now = new Date();
        wxProduct.setGmtModified(now);
        return wxProductMapper.updateByPrimaryKeySelective(wxProduct);
    }

    @Override
    public List<WxProduct> findAll() {
        return wxProductMapper.selectAll();
    }

    @Override
    public TableData<WxProduct> findListTable(TableData<WxProduct> tableData,Long companyId, String type, Boolean isBanner) {
        PageHelper.startPage(tableData.getPageNumber(),tableData.getPageSize());
        Example.Builder builder = Example.builder(WxProduct.class);
        Sqls sqls = WhereDto.defaultWhere(companyId);
        if(StringUtils.isNotBlank(type) && !type.startsWith(ALL_TYPE)){
            sqls.andEqualTo("type",type);
        }
        if(BooleanUtils.isTrue(isBanner)){
            sqls.andEqualTo("isBanner",isBanner);
        }
        builder.where(sqls);
        if(StringUtils.isNotBlank(tableData.getSortName()) && !tableData.getSortName().startsWith(ALL_ORDER)){
            if("热度".equals(tableData.getSortName()))
                if("desc".equals(tableData.getSortOrder()))
                    builder.orderByDesc("hot");
                else
                    builder.orderBy("hot");
            if("价格".equals(tableData.getSortName()))
                if("desc".equals(tableData.getSortOrder()))
                    builder.orderByDesc("cost");
                else
                    builder.orderBy("cost");
            if("时间".equals(tableData.getSortName()))
                if("desc".equals(tableData.getSortOrder()))
                    builder.orderByDesc("gmtModified");
                else
                    builder.orderBy("gmtModified");
        }
        List<WxProduct> list = wxProductMapper.selectByExample(builder.build());
        PageInfo<WxProduct> page= new PageInfo<>(list);
        return TableDataFactory.newInstaceSuccessResult(tableData,page,list);
    }

    @Override
    public List<String> findType(Long companyId) {
        return wxProductMapper.selectType(companyId);
    }

    @Override
    public List<WxProduct> findBannerList(Long companyId) {
        Sqls sqls = WhereDto.defaultWhere(companyId);
        sqls.andEqualTo("isBanner",true);
        return wxProductMapper.selectByExample(Example.builder(WxProduct.class).where(sqls).build());
    }

    @Override
    public TableData<WxProduct> findProductTable(TableData<WxProduct> tableData, Long companyId, String title) {
        PageHelper.startPage(tableData.getPageNumber(),tableData.getPageSize());
        Sqls sqls = WhereDto.InIsDeletedWhere(companyId);
        if(StringUtils.isNotBlank(title)){
            sqls.andLike("title","%"+title+"%");
        }
        Example.Builder builder = Example.builder(WxProduct.class).where(sqls).orderByDesc("gmtModified");
        if(StringUtils.isNotBlank(tableData.getSortName()) && StringUtils.isNotBlank(tableData.getSortOrder())){
            if("desc".equals(tableData.getSortOrder())){
                builder = builder.orderByDesc(tableData.getSortName());
            }else{
                builder = builder.orderByAsc(tableData.getSortName());
            }
        }
        Example example = builder.build();
        List<WxProduct> wxProducts = wxProductMapper.selectByExample(example);
        PageInfo<WxProduct> page= new PageInfo<>(wxProducts);
        return TableDataFactory.newInstaceSuccessResult(tableData,page,wxProducts);
    }

    @Override
    public WxProduct delProduct(Long id, Long companyId) {
        Sqls sqls = WhereDto.InIsDeletedWhere(companyId);
        WxProduct wxProduct = wxProductMapper.selectOneByExample(Example.builder(WxProduct.class).where(sqls.andEqualTo("id",id)).build());
        if(wxProduct == null){
            return null;
        }
        wxProduct.setIsDeleted((byte)1);
        wxProduct.setCompanyId(companyId);
        logger.info("delProduct after =====> {}", JSON.toJSONString(wxProduct));
        int count = saveOrUpdate(wxProduct);
        logger.info("delProduct before =====> {}", JSON.toJSONString(wxProduct));
        if(count>0) {
            return wxProduct;
        }
        return null;
    }

    @Override
    public WxProduct recProduct(Long id, Long companyId) {
        Sqls sqls = WhereDto.InIsDeletedWhere(companyId);
        WxProduct wxProduct = wxProductMapper.selectOneByExample(Example.builder(WxProduct.class).where(sqls.andEqualTo("id",id)).build());
        if(wxProduct == null){
            return null;
        }
        wxProduct.setIsDeleted((byte)0);
        wxProduct.setCompanyId(companyId);
        logger.info("recProduct after =====> {}", JSON.toJSONString(wxProduct));
        int count = saveOrUpdate(wxProduct);
        logger.info("recProduct before =====> {}", JSON.toJSONString(wxProduct));
        if(count>0) {
            return wxProduct;
        }
        return null;
    }
}
