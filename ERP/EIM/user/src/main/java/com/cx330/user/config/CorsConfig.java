package com.cx330.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* 跨域配置
* */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //表示所有请求路径都可以跨域
        registry.addMapping("/**")
                //表示允许携带身份凭据
                .allowCredentials(true)
                //表示允许所有来源的请求跨域访问
                .allowedOriginPatterns("*")
                //表示允许跨域的请求方式
                .allowedMethods(new String[]{"GET","POST","PUT","DELETE"})
                //表示允许请求携带任何头部信息
                .allowedHeaders("*")
                //表示允许访问响应中的任何头部信息
                .exposedHeaders("*");
    }
}