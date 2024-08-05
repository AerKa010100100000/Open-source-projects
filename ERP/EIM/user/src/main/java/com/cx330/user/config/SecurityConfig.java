package com.cx330.user.config;

import com.cx330.user.web.filter.JwtOncePerRequestFilter;
import com.cx330.user.web.handler.AccessDeniedHandlerImpl;
import com.cx330.user.web.handler.AuthenticationEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
* Security 全局配置器
* */

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig {
    @Autowired
    private JwtOncePerRequestFilter jwtOncePerRequestFilter;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //用户登录控制
        http.formLogin((formLogin)-> formLogin.disable())
                .logout((logout) -> logout.disable())
                // 禁用session
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                //url权限控制
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/register").permitAll()
                                .anyRequest().authenticated()
                )
                // 添加JWT过滤器
                .addFilterBefore(jwtOncePerRequestFilter, UsernamePasswordAuthenticationFilter.class)
                // 认证异常/鉴权异常处理
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .accessDeniedHandler(new AccessDeniedHandlerImpl())
                                .authenticationEntryPoint(new AuthenticationEntry())
                );

        //禁用csrf保护
        http.csrf(AbstractHttpConfigurer::disable);
        //关闭跨域拦截
        http.cors(AbstractHttpConfigurer::disable);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
