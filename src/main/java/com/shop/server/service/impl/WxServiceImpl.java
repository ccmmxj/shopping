package com.shop.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.shop.server.service.WxService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WxServiceImpl implements WxService {
    @Override
    public void setDataByText(Map<String, String> map) {
//        map.put("text", JSON.toJSONString(Map.of("content","Hello World"),SerializerFeature.UseSingleQuotes));
        Map<String,String> mapV = new HashMap<>();
        mapV.put("content","Hello World");
        map.put("text", JSON.toJSONString(mapV,SerializerFeature.UseSingleQuotes));
    }

    @Override
    public void setDataByImage(Map<String, String> map) {
//        map.put("image", JSON.toJSONString(Map.of("media_id","MEDIA_ID")));
        Map<String,String> mapV = new HashMap<>();
        mapV.put("content","Hello World");
        map.put("image", JSON.toJSONString(mapV));
    }

    @Override
    public void setDataByLink(Map<String, String> map) {
//        map.put("link", JSON.toJSONString(Map.of("title","Happy Day","description","Is Really A Happy Day","url","URL","thumb_url","THUMB_URL")));
        Map<String,String> mapV = new HashMap<>();
        mapV.put("title","Happy Day");
        mapV.put("description","Is Really A Happy Day");
        mapV.put("url","URL");
        mapV.put("thumb_url","THUMB_URL");
        map.put("link", JSON.toJSONString(mapV));
    }
}
