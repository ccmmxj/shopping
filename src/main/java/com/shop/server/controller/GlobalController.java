package com.shop.server.controller;

import com.shop.server.utils.Result;
import com.shop.server.utils.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GlobalController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);

    @GetMapping("error")
    @ResponseBody
    public Result error(){
        return ResultFactory.newInstaceFailResult("错误",200L,"错误");
    }
}
