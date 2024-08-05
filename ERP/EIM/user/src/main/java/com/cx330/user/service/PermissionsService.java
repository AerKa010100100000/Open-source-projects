package com.cx330.user.service;

import com.cx330.entity.Permission;

import java.util.List;

public interface PermissionsService {
    // 添加权限
    int addPermission(Permission permission);
    // 删除权限
    int deletePermission(int permissionId);
    // 修改权限
    int updatePermission(Permission permission);
    // 查询权限
    Permission getPermission(int permissionId);
    // 查询所有权限
    List<Permission> getAllPermissions();
}
