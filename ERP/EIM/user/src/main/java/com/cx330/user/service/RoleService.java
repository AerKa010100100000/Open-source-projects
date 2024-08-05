package com.cx330.user.service;

import com.cx330.entity.Role;

import java.util.List;

public interface RoleService {
    // 添加角色信息
    int addRole(Role role);
    // 删除角色信息
    int deleteRole(Integer roleId);
    // 修改角色信息
    int updateRole(Role role);
    // 查询角色信息
    Role getRoleName(Integer roleId);
    // 查询所有角色信息
    List<Role> getAllRoleNames();
}
