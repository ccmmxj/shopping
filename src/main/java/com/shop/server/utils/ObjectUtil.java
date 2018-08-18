package com.shop.server.utils;


import com.shop.server.model.BaseModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectUtil {
    private ObjectUtil (){

    }
    public  static <T> T getDto(Object model , T t) throws InvocationTargetException, IllegalAccessException {
        if(model != null && t != null){
            ((BaseModel<Long>) t).setId(((BaseModel<Long>)model).getId());
            Method[] modelMethods = model.getClass().getDeclaredMethods();
            for(Method modelMethod:modelMethods){
                if(modelMethod.getName().startsWith("get")){
                    for(Method tMethod:modelMethods){
                        if(tMethod.getName().substring(2).equals(modelMethod.getName().substring(2)) && tMethod.getName().startsWith("set"))
                            tMethod.invoke(t,modelMethod.invoke(model,null));
                    }
                }
            }
        }
        return t;
    }
}
