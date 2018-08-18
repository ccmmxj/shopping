package com.shop.server.service;

import com.shop.server.dto.WxOrderDto;
import com.shop.server.model.WxOrderProduct;

import java.util.List;

public interface WxOrderProductService extends BaseService<WxOrderProduct,Long> {
    void setProductByOrder(WxOrderDto wxOrderDto);
    void setProductsByOrder(List<WxOrderDto> wxOrderDtoList);
}
