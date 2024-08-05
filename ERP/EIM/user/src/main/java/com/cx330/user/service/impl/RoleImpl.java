package com.cx330.user.service.impl;

import com.cx330.constant.ConstantValue;
import com.cx330.entity.Role;
import com.cx330.user.aop.UserLog;
import com.cx330.user.mapper.RoleMapper;
import com.cx330.user.service.RolePermissionService;
import com.cx330.user.service.RoleService;
import com.cx330.user.service.UserRoleMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RoleImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMappingService userRoleMappingService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    @UserLog(operation = ConstantValue.LOG_INSERT, operationInfo = "添加角色信息")
    public int addRole(Role role) {

        return roleMapper.addRole(role);
    }

    @Override
    @Transactional
    @UserLog(operation = ConstantValue.LOG_DELETE, operationInfo = "删除角色信息")
    public int deleteRole(Integer roleId) {
        int deleteUserRoleMappingByRoleId = userRoleMappingService.deleteUserRoleMappingByRoleId(roleId);
        int deleteRolePermissionMappingByRoleId = rolePermissionService.deleteRolePermissionMappingByRoleId(roleId);
        if (deleteUserRoleMappingByRoleId > 0 && deleteRolePermissionMappingByRoleId > 0)
            return roleMapper.deleteRole(roleId);
        return 0;
    }

    @Override
    @UserLog(operation = ConstantValue.LOG_UPDATE, operationInfo = "修改角色信息")
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public Role getRoleName(Integer roleId) {
        return roleMapper.getRoleName(roleId);
    }

    @Override
    public List<Role> getAllRoleNames() {
        return roleMapper.getAllRoleNames();
    }
}
