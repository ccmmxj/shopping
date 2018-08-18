package com.shop.server.dto;

import com.shop.server.model.WxOrder;
import com.shop.server.model.WxOrderProduct;

import java.util.List;

public class WxOrderDto extends WxOrder {
    private List<WxOrderProduct> wxOrderProducts;

    public List<WxOrderProduct> getWxOrderProducts() {
        return wxOrderProducts;
    }

    public void setWxOrderProducts(List<WxOrderProduct> wxOrderProducts) {
        this.wxOrderProducts = wxOrderProducts;
    }
}