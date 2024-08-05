package com.cx330.user.service.impl;

import com.cx330.constant.ConstantValue;
import com.cx330.entity.Role;
import com.cx330.entity.User;
import com.cx330.entity.UserRoleMapping;
import com.cx330.user.entity.LoginUser;
import com.cx330.user.service.RoleService;
import com.cx330.user.service.UserRoleMappingService;
import com.cx330.user.service.UserService;
import com.cx330.utility.EncryptionUtil;
import com.cx330.utility.ThreadLocalBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/*
* 功能：获取用户登录所需要的信息并进行返回
*
*
* */

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleMappingService userRoleMappingService;

    @Autowired
    private RoleService roleService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名查询出用户所有信息
        User user = userService.queryUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 存放角色信息
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        // 获取用户密码
        String password = user.getPassword();

        // 获取角色id
        List<UserRoleMapping> userRoleMappings = userRoleMappingService.queryUserRoleMapping(user.getUserID());
        // 根据角色id获取角色名称，并添加到GrantedAuthority列表中
        for (UserRoleMapping userRoleMapping : userRoleMappings){
            Role role = roleService.getRoleName(userRoleMapping.getRoleId());
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            grantedAuthorities.add(simpleGrantedAuthority);
        }
        LoginUser loginUser = new LoginUser(username, password, grantedAuthorities);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        ThreadLocalBuffer.set(user);
        // 返回登录用户信息
        return loginUser;
    }
}
