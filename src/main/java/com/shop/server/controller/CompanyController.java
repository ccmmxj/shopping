package com.shop.server.controller;

import com.shop.server.service.ManageCompanyService;
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
@RequestMapping("shopping/company")
public class CompanyController {
    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
    @Autowired
    private ManageCompanyService manageCompanyService;

    @GetMapping("test")
    @ResponseBody
    public Result test(){
        return ResultFactory.newInstaceFailResult("上传失败",200L,"TestController");
    }

    @PostMapping("findCompanyById")
    @ResponseBody
    public Result findCompanyById(Long companyId) {
        return ResultFactory.newInstaceFailResult("获取成功", 200L, manageCompanyService.findOne(companyId));
    }
}
