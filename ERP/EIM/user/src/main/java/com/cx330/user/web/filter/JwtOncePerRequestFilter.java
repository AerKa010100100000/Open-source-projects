package com.cx330.user.web.filter;

import com.cx330.constant.ConstantValue;
import com.cx330.entity.User;
import com.cx330.user.entity.IpUtil;
import com.cx330.user.service.impl.UserDetailsServiceImpl;
import com.cx330.utility.JWTUtil;
import com.cx330.utility.Result;
import com.cx330.utility.ThreadLocalBuffer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/*
* JWT过滤器
*
* */
@Component
public class JwtOncePerRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Getter
    @Setter
    public String ip = null;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String requestURI = request.getRequestURI();
        this.ip = IpUtil.getIp(request);
        // 登录页面不进行JWT验证，放行登录页面
        if (requestURI.startsWith("/login")){
            filterChain.doFilter(request, response);
            return ;
        }
        // 从请求头获取token
        String token = request.getHeader(ConstantValue.AUTHORIZATION);
        // 如果token为空，则放行
        if (StringUtils.isEmpty(token)){
            response.setContentType("application/json;charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            Result<String> stringResult = new Result<String>().error().message("token不能为空！");
            response.getWriter().write(objectMapper.writeValueAsString(stringResult));

            return ;
        }
        // 如果token失效
        if (!JWTUtil.verifyToken(token)){
            response.setContentType("application/json;charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            Result<String> stringResult = new Result<String>().error().message("token失效！");
            response.getWriter().write(objectMapper.writeValueAsString(stringResult));

            return ;
        }
        // 如果token不为空且SecurityContextHolder没有认证信息，则认证
        if (!StringUtils.isEmpty(token) && SecurityContextHolder.getContext().getAuthentication() == null){
            // 从token中获取用户信息
            User user = JWTUtil.parseToken(token);
            // 将用户名传入UserDetailsServiceImpl中，获取用户信息
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            // 将用户认证信息存储在Spring Security线程上下文中
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            // 将认证信息放入ThreadLocalBuffer
            ThreadLocalBuffer.set(user);
        }
        filterChain.doFilter(request, response);
    }


}
