package com.cx330.user.service.impl;

import com.cx330.constant.ConstantValue;
import com.cx330.entity.Role;
import com.cx330.entity.User;
import com.cx330.entity.UserRoleMapping;
import com.cx330.user.aop.UserLog;
import com.cx330.user.mapper.UserRoleMappingMapper;
import com.cx330.user.service.RoleService;
import com.cx330.user.service.UserRoleMappingService;
import com.cx330.user.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleMappingImpl implements UserRoleMappingService {
    @Autowired
    private UserRoleMappingMapper userRoleMappingMapper;


    @Lazy
    @Autowired
    private UserService userService;


    @Lazy
    @Autowired
    private RoleService roleService;


    // 用户角色授权
    @Override
    @UserLog(operation = ConstantValue.LOG_INSERT, operationInfo = "管理员对用户角色授权")
    public int addUserRoleMapping(UserRoleMapping userRoleMapping) {
        // 校验用户和角色是否存在
        try {
            User user = userService.queryUserById(userRoleMapping.getUserId());
            Role roleName = roleService.getRoleName(userRoleMapping.getRoleId());
            if (user == null || roleName == null) {
                throw new Exception("用户或角色不存在");
            }
            return userRoleMappingMapper.addUserRoleMapping(userRoleMapping);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // 删除用户所有角色
    @Override
    @UserLog(operation = ConstantValue.LOG_DELETE, operationInfo = "管理员删除了对该用户的授权")
    public int deleteUserRoleMappingByUserId(Integer userId) {
        // 校验用户是否存在
        try {
            User user = userService.queryUserById(userId);
            if (user == null) {
                throw new Exception("用户不存在");
            }
            return userRoleMappingMapper.deleteUserRoleMappingByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 删除角色所有用户
    @Override
    @UserLog(operation = ConstantValue.LOG_DELETE, operationInfo = "管理员删除了该用户的所有授权")
    public int deleteUserRoleMappingByRoleId(Integer roleId) {
        // 校验角色是否存在
        try {
            Role roleName = roleService.getRoleName(roleId);
            if (roleName == null) {
                throw new Exception("角色不存在");
            }

            return userRoleMappingMapper.deleteUserRoleMappingByRoleId(roleId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 查询用户角色列表
    @Override
    public List<UserRoleMapping> queryUserRoleMapping(Integer userId) {
        return userRoleMappingMapper.queryUserRoleMapping(userId);
    }

    // 修改用户授权角色
    @Override
    @UserLog(operation = ConstantValue.LOG_UPDATE, operationInfo = "管理员修改了该用户的授权")
    public int updateUserRoleMapping(UserRoleMapping initial, UserRoleMapping ultimately) throws Exception {
       // 校验用户和角色是否存在
        try {
            User user = userService.queryUserById(ultimately.getUserId());
            Role roleName = roleService.getRoleName(ultimately.getRoleId());
            if (user == null || roleName == null) {
                throw new Exception("用户或角色不存在");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userRoleMappingMapper.updateUserRoleMapping(initial, ultimately);
    }
}
