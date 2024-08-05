package com.cx330.user.service.impl;

import com.cx330.entity.User;
import com.cx330.entity.UserSession;
import com.cx330.user.entity.LoginUser;
import com.cx330.user.mapper.SessionMapper;
import com.cx330.user.service.LoginService;
import com.cx330.user.web.filter.JwtOncePerRequestFilter;
import com.cx330.utility.ThreadLocalBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class LoginImpl implements LoginService {


    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SessionMapper sessionMapper;
    @Autowired
    private JwtOncePerRequestFilter jwtOncePerRequestFilter;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    UserSession userSession = new UserSession();
    Date loginDate ;
    Date logoutDate;
    @Override
    public User login(String username, String password) {
        try {
            AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authenticate = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        loginDate = new Date();
        //System.out.println(formatter.format(date));
        return (User) ThreadLocalBuffer.get();

    }

    @Override
    public boolean logout() {
        logoutDate = new Date();
        User user = (User) ThreadLocalBuffer.get();
        if (user != null){
            userSession.setUserId(user.getUserID());
            userSession.setStartTime(loginDate);
            userSession.setEndTime(logoutDate);
            userSession.setLoginIp(jwtOncePerRequestFilter.getIp());
        }
        sessionMapper.insertSession(userSession);

        // 清楚本地线程缓存
        ThreadLocalBuffer.remove();
        // 清除spring security缓存
        SecurityContextHolder.clearContext();
        return true;
    }
}
