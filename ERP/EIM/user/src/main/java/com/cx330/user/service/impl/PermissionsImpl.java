package com.cx330.user.service.impl;

import com.cx330.constant.ConstantValue;
import com.cx330.entity.Permission;
import com.cx330.user.aop.UserLog;
import com.cx330.user.mapper.PermissionsMapper;
import com.cx330.user.service.PermissionsService;
import com.cx330.user.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionsImpl implements PermissionsService {

    @Autowired
    private PermissionsMapper permissionsMapper;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Override
    @UserLog(operation = ConstantValue.LOG_INSERT, operationInfo = "修改权限信息")
    public int addPermission(Permission permission) {
        return permissionsMapper.addPermission(permission);
    }

    @Override
    @UserLog(operation = ConstantValue.LOG_DELETE, operationInfo = "删除权限信息")
    @Transactional
    public int deletePermission(int permissionId) {
        int deleteRolePermissionMappingByPermissionId = rolePermissionService.deleteRolePermissionMappingByPermissionId(permissionId);
        if (deleteRolePermissionMappingByPermissionId > 0)
            return permissionsMapper.deletePermission(permissionId);
        return 0;
    }

    @Override
    @UserLog(operation = ConstantValue.LOG_UPDATE, operationInfo = "修改权限信息")
    public int updatePermission(Permission permission) {
        return permissionsMapper.updatePermission(permission);
    }

    @Override
    public Permission getPermission(int permissionId) {
        return permissionsMapper.getPermission(permissionId);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionsMapper.getAllPermissions();
    }
}
