package com.shop.server.service.impl;

import com.shop.server.dto.WhereDto;
import com.shop.server.dto.WxOrderDto;
import com.shop.server.mapper.WxOrderProductMapper;
import com.shop.server.model.WxOrder;
import com.shop.server.model.WxOrderProduct;
import com.shop.server.model.WxProduct;
import com.shop.server.service.WxOrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.Date;
import java.util.List;

@Service
public class WxOrderProductServiceImpl implements WxOrderProductService {
    @Autowired
    private WxOrderProductMapper wxOrderProductMapper;

    @Override
    public WxOrderProduct findOne(Long id) {
        return wxOrderProductMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int saveOrUpdate(WxOrderProduct wxOrderProduct) {
        if(wxOrderProduct.getId() != null) return update(wxOrderProduct);
        return save(wxOrderProduct);
    }

    @Override
    @Transactional
    public int save(WxOrderProduct wxOrderProduct) {
        Date now = new Date();
        wxOrderProduct.setGmtCreated(now);
        wxOrderProduct.setGmtModified(now);
        return wxOrderProductMapper.insertSelective(wxOrderProduct);
    }

    @Override
    @Transactional
    public int update(WxOrderProduct wxOrderProduct) {
        Date now = new Date();
        wxOrderProduct.setGmtModified(now);
        return wxOrderProductMapper.updateByPrimaryKeySelective(wxOrderProduct);
    }

    @Override
    public List<WxOrderProduct> findAll() {
        return wxOrderProductMapper.selectAll();
    }

    @Override
    public void setProductByOrder(WxOrderDto wxOrderDto) {
        Sqls sqls =  Sqls.custom().andEqualTo("orderId",wxOrderDto.getId()).andEqualTo("isDeleted",0);
        wxOrderDto.setWxOrderProducts(wxOrderProductMapper.selectByExample(Example.builder(WxOrderProduct.class).where(sqls).build()));
    }

    @Override
    public void setProductsByOrder(List<WxOrderDto> wxOrderDtoList) {
        wxOrderDtoList.stream().forEach(v -> {
            setProductByOrder(v);
        });
    }
}
