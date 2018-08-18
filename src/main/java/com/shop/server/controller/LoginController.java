package com.shop.server.controller;

import com.shop.server.common.Context;
import com.shop.server.model.ManageCompany;
import com.shop.server.service.ManageCompanyService;
import com.shop.server.utils.NetWork;
import com.shop.server.utils.Result;
import com.shop.server.utils.ResultFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequestMapping("shopping")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private ManageCompanyService manageCompanyService;
    @PostMapping("wxLogin")
    @ResponseBody
    public Result wxLogin(String wxCode,Long companyId) throws URISyntaxException, IOException, InterruptedException {
        ManageCompany manageCompany = manageCompanyService.findOne(companyId);
        String strResult = NetWork.HttpGet (Context.WX_OPENED + "?appid="+manageCompany.getWxAppId()+"&secret="+manageCompany.getWxAppSecret()+"&js_code=" + wxCode+"&grant_type=authorization_code");
        return ResultFactory.newInstaceFailResult("提示", 200L, strResult);
    }
}
