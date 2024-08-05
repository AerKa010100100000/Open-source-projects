package com.cx330.user.service.impl;

import com.cx330.constant.ConstantValue;
import com.cx330.entity.User;
import com.cx330.user.aop.UserLog;
import com.cx330.user.mapper.UserMapper;
import com.cx330.user.service.UserRoleMappingService;
import com.cx330.user.service.UserService;
import com.cx330.utility.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMappingService userRoleMappingService;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
    // 编辑用户
    @UserLog(operation = ConstantValue.LOG_UPDATE, operationInfo = "修改用户信息")
    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
    // 删除用户
    @Override
    @Transactional
    @UserLog(operation = ConstantValue.LOG_DELETE, operationInfo = "删除用户信息")
    public int deleteUser(Integer userID) {
        int deleteUserRoleMappingByUserId = userRoleMappingService.deleteUserRoleMappingByUserId(userID);
        if (deleteUserRoleMappingByUserId > 0)
            return userMapper.deleteUser(userID);
        return 0;
    }
    // 查询用户信息
    @Override
    public User queryUserById(Integer userId)  {
        return userMapper.queryUserById(userId);
    }
    // 查询用户列表
    @Override
    public List<User> queryUserList()  {
        return userMapper.queryUserList();
    }
    // 管理员重置密码
    @Override
    @UserLog(operation = ConstantValue.LOG_UPDATE, operationInfo = "管理员重置了密码")
    public int resetPassword(Integer userId) {
        try {
            return userMapper.resetPassword(userId, passwordEncoder.encode(ConstantValue.NEW_PASSWORD));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 用户修改密码
    @Override
    @UserLog(operation = ConstantValue.LOG_UPDATE, operationInfo = "用户修改了密码")
    public int updatePassword(Integer userId, String oldPassword, String newPassword) {
        User user = userMapper.queryUserById(userId);
        try {
            String password = user.getPassword();
            if (password.equals(passwordEncoder.encode(oldPassword))) {
                return userMapper.resetPassword(userId, EncryptionUtil.encryptWithAES( newPassword, ConstantValue.KEY));
            } else {
                return 0;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 注销用户
    @Override
    @UserLog(operation = ConstantValue.LOG_UPDATE, operationInfo = "用户注销了账户")
    public int cancelUser(Integer userId) {

        return userMapper.setStatus(userId, ConstantValue.CANCEL_STATUS);
    }
    // 激活用户
    @Override
    @UserLog(operation = ConstantValue.LOG_UPDATE, operationInfo = "管理员激活了账户")
    public int activateUser(Integer userId) {
        return userMapper.setStatus(userId, ConstantValue.APPROVE_STATUS);
    }
    // 根据用户名查询用户
    @Override
    public User queryUserByUsername(String username) {
        return userMapper.queryUserByUsername(username);
    }

    // 注册用户
    @Override
    @UserLog(operation = ConstantValue.LOG_INSERT, operationInfo = "注册了新用户")
    public int registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.addUser(user);
    }
}
