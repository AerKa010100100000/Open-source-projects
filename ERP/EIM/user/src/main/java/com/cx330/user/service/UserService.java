package com.cx330.user.service;

import com.cx330.entity.User;
import org.apache.catalina.util.Introspection;

import java.util.List;


public interface UserService {
    // 编辑用户
    int updateUser(User user) throws Exception;
    // 删除用户
    int deleteUser(Integer userID);
    // 查询用户信息
    User queryUserById(Integer userId) throws Exception;
    // 查询用户列表
    List<User> queryUserList() throws Exception;
    // 管理员重置密码
    int resetPassword(Integer userId) throws Exception;
    // 用户修改密码
    int updatePassword(Integer userId, String oldPassword, String newPassword) throws Exception;
    // 用户注销
    int cancelUser(Integer userId) throws Exception;
    // 用户激活
    int activateUser(Integer userId) throws Exception;

    // 根据用户名查询用户信息
    User queryUserByUsername(String username);

    // 用户注册
    int registerUser(User user);
}
