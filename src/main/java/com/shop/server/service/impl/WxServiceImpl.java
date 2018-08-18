package com.shop.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.shop.server.service.WxService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WxServiceImpl implements WxService {
    @Override
    public void setDataByText(Map<String, String> map) {
        map.put("text", JSON.toJSONString(Map.of("content","Hello World"),SerializerFeature.UseSingleQuotes));
    }

    @Override
    public void setDataByImage(Map<String, String> map) {
        map.put("image", JSON.toJSONString(Map.of("media_id","MEDIA_ID")));
    }

    @Override
    public void setDataByLink(Map<String, String> map) {
        map.put("link", JSON.toJSONString(Map.of("title","Happy Day","description","Is Really A Happy Day","url","URL","thumb_url","THUMB_URL")));
    }
}
