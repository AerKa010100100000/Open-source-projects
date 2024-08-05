package com.cx330.user.service;

import com.cx330.entity.User;

public interface LoginService {
    User login(String username, String password);

    boolean logout();

}
