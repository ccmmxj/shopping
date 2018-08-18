package com.shop.server.controller;

import com.shop.server.dto.TableData;
import com.shop.server.model.WxProduct;
import com.shop.server.service.ManageCompanyService;
import com.shop.server.service.WxProductService;
import com.shop.server.utils.Result;
import com.shop.server.utils.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("shopping/product")
public class ProductController {

    @Autowired
    private WxProductService wxProductService;

    @PostMapping("findListTable")
    @ResponseBody
    public Result findListTable(TableData<WxProduct> tableData,Long companyId, String type, Boolean isBanner) {
        return ResultFactory.newInstaceFailResult("获取成功", 200L, wxProductService.findListTable(tableData,companyId,type,isBanner));
    }

    @PostMapping("findBannerList")
    @ResponseBody
    public Result findBannerList(Long companyId) {
        return ResultFactory.newInstaceFailResult("获取成功", 200L, wxProductService.findBannerList(companyId));
    }
    @PostMapping("findType")
    @ResponseBody
    public Result findType(Long companyId) {
        return ResultFactory.newInstaceFailResult("获取成功", 200L, wxProductService.findType(companyId));
    }
}
