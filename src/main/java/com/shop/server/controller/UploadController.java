package com.shop.server.controller;

import com.shop.server.common.Context;
import com.shop.server.utils.FileUpload;
import com.shop.server.utils.Result;
import com.shop.server.utils.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("manage")
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @PostMapping("upload/{type}")
    @ResponseBody
    public Result upload(MultipartFile file, @PathVariable String type){
        try {
            String result = FileUpload.uploadFile(file,Context.uploadAddr() + type);
            return ResultFactory.newInstaceSuccessResult("上传成功",200L,Context.FILE_HOST + type + "/" + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultFactory.newInstaceFailResult("上传失败",200L,null);
    }
}
