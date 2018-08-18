package com.shop.server.controller;

import com.alibaba.fastjson.JSON;
import com.shop.server.dto.WxOrderDto;
import com.shop.server.model.WxOrderProduct;
import com.shop.server.model.WxProduct;
import com.shop.server.service.WxOrderService;
import com.shop.server.utils.Result;
import com.shop.server.utils.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("shopping/order")
public class OrderController {

    @Autowired
    private WxOrderService wxProductService;

    @PostMapping("saveOrderAndProduct")
    @ResponseBody
    public Result saveOrderAndProduct(WxOrderDto wxOrderDto,String wxOrderProductJson) {
        return ResultFactory.newInstaceFailResult("获取成功", 200L, wxProductService.saveOrderDto(wxOrderDto,JSON.parseArray(wxOrderProductJson,WxOrderProduct.class)));
    }
}
