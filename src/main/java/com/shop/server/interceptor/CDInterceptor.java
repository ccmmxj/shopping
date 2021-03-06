package com.shop.server.interceptor;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class CDInterceptor {

    @Before(value = "execution(* com.shop.server.controller.*.*(..))")
    public void before(){
        HttpServletResponse response =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest request =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        CDResponse(response,request);
    }

    public static void CDResponse(HttpServletResponse response,HttpServletRequest request ){
        response.setContentType("text/html;charset=UTF-8");

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Origin", "*");

//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Methods", "*");

        response.setHeader("Access-Control-Max-Age", "0");

        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
//        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");

        response.setHeader("Access-Control-Allow-Credentials", "true");

//        response.setHeader("XDomainRequestAllowed","1");
    }
}
