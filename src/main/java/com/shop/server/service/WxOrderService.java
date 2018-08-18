package com.shop.server.service;

import com.shop.server.dto.TableData;
import com.shop.server.dto.WxOrderDto;
import com.shop.server.model.WxOrder;
import com.shop.server.model.WxOrderProduct;

import java.util.List;

public interface WxOrderService extends BaseService<WxOrder,Long> {
    WxOrderDto saveOrderDto(WxOrderDto wxOrderDto, List<WxOrderProduct> wxProducts);
    TableData<WxOrderDto> findWxOrderDtoTable(TableData<WxOrderDto> tableData, Long companyId, String oid);
    WxOrder delOrder(Long id, Long companyId);
    WxOrder upStatus(Long id, String type, Long companyId);
    WxOrder recOrder(Long id, Long companyId);
}
