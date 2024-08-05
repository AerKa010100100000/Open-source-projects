package com.cx330.user.service;

import com.cx330.entity.RolePermissionMapping;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;



public interface RolePermissionService {
    // 添加角色权限映射
    int addRolePermissionMapping(RolePermissionMapping rolePermissionMapping);
    int deleteRolePermissionMappingByRoleId(Integer roleId);
    // 根据权限id删除角色权限映射
    int deleteRolePermissionMappingByPermissionId(Integer permissionId);
    // 修改角色权限映射
    int updateRolePermissionMapping(RolePermissionMapping initial, RolePermissionMapping ultimately);
    // 查询角色权限映射
    List<RolePermissionMapping> getRolePermissionMapping(Insert roleId);
    // 查询角色权限映射列表
    List<RolePermissionMapping> getRolePermissionMappingList();
}
