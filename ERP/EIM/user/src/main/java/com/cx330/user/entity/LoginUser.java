package com.cx330.user.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


@Getter
@Setter
public class LoginUser extends User {
    private com.cx330.entity.User entityUser;
    public LoginUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public LoginUser(com.cx330.entity.User entityUser, Collection<? extends GrantedAuthority> authorities) {
        super(entityUser.getUsername(), entityUser.getPassword(), authorities);
        System.out.println("LoginUser constructor: " + entityUser.getUsername() + " " + entityUser.getPassword() + " " + authorities);
        this.entityUser = entityUser;
    }

}
