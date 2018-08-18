package com.shop.server.config;

import com.shop.server.dto.UserDto;
import com.shop.server.interceptor.CDInterceptor;
import com.shop.server.utils.LoginUtil;
import com.shop.server.utils.ResultFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

/**
 * @author wangruyu
 * @since 2017/3/15-09:57
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDto user = LoginUtil.getLoginUser();
        HttpSession session = request.getSession(false);
//        response.setContentType("text/html;charset=UTF-8");
        CDInterceptor.CDResponse(response,request);
        PrintWriter printWriter = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(session.getId());
        user.setSessionId(session.getId());
        if(user == null){
            LOGGER.info("注消成功...");
            String result = objectMapper.writeValueAsString(ResultFactory.newInstaceSuccessResult("注消成功",200L,user));
            printWriter.print(result);
        }
        session.setAttribute(user.getManageUser().getUserName(),"ok");
        LOGGER.info(MessageFormat.format("用户{0}登录成功...",user.getManageUser().getEmpName()));
        String result = objectMapper.writeValueAsString(ResultFactory.newInstaceSuccessResult("登陆成功",200L,user));
        printWriter.print(result);
    }
}
