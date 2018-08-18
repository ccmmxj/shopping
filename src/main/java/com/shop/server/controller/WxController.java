package com.shop.server.controller;

import com.shop.server.common.Context;
import com.shop.server.model.ManageCompany;
import com.shop.server.service.ManageCompanyService;
import com.shop.server.service.WxService;
import com.shop.server.utils.NetWork;
import com.shop.server.utils.Result;
import com.shop.server.utils.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

@Controller
@RequestMapping("shopping")
public class WxController {
    private static final Logger logger = LoggerFactory.getLogger(WxController.class);
    @Autowired
    private ManageCompanyService manageCompanyService;
    @Autowired
    private WxService wxService;
    @PostMapping("getAccessToken")
    @ResponseBody
    public Result getAccessToken(Long companyId) throws URISyntaxException, IOException, InterruptedException {
        ManageCompany manageCompany = manageCompanyService.findOne(companyId);
        String strResult = NetWork.HttpGet(Context.WX_TOKEN + "?appid="+manageCompany.getWxAppId()+"&secret="+manageCompany.getWxAppSecret()+"&grant_type=client_credential");
        return ResultFactory.newInstaceFailResult("提示", 200L, strResult);
    }
    @PostMapping("send")
    @ResponseBody
    public Result send(String type,String accessToken,String openId) throws URISyntaxException, IOException, InterruptedException {
        String strResult = NetWork.HttpPost(Context.WX_SEND + "?access_token="+accessToken,wxService.getDataByType(type,openId),"utf-8");
        return ResultFactory.newInstaceFailResult("提示", 200L, strResult);
    }
}
