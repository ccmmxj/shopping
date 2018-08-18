package com.shop.server.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public interface WxService {
    default Map<String,String> getDataByType(String type,String openId){
        Map<String ,String> map = new LinkedHashMap<>();
        map.put("touser",openId);
        map.put("msgtype",type);
        if("text".equals(type)){
            setDataByText(map);
        }
        if("image".equals(type)){
            setDataByImage(map);
        }
        if("link".equals(type)){
            setDataByLink(map);
        }
        return map;
    }
    void setDataByText(Map<String ,String> map);
    void setDataByImage(Map<String ,String> map);
    void setDataByLink(Map<String ,String> map);
}
