package com.cx330.user.service.impl;

import com.cx330.entity.User;
import com.cx330.user.mapper.UserMapper;
import com.cx330.user.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public int register(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userMapper.addUser(user);
    }
}
