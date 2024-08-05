package com.cx330.user.web.handler;

import com.cx330.utility.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;


/*
* 身份验证入口点异常处理程序
*
* */
public class AuthenticationEntry implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        Result<String> stringResult = new Result<String>().error().message("用户名或密码错误！");
        response.getWriter().write(objectMapper.writeValueAsString(stringResult));
    }
}
