package com.cx330.user.mapper;

import com.cx330.entity.RolePermissionMapping;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RolePermissionMappingMapper {
    // 添加角色权限映射
    @Insert("INSERT INTO role_permission_mapping_table(role_id, permission_id) VALUES(#{roleId}, #{permissionId})")
    int addRolePermissionMapping(RolePermissionMapping rolePermissionMapping);
    // 根据角色id删除角色权限映射
    @Delete("DELETE FROM role_permission_mapping_table WHERE role_id = #{roleId}")
    int deleteRolePermissionMappingByRoleId(Integer roleId);
    // 根据权限id删除角色权限映射
    @Delete("DELETE FROM role_permission_mapping_table WHERE permission_id = #{permissionId}")
    int deleteRolePermissionMappingByPermissionId(Integer permissionId);
    // 修改角色权限映射
    @Update("UPDATE role_permission_mapping_table SET permission_id = #{ultimately.permissionId} WHERE role_id = #{initial.roleId} AND permission_id = #{initial.permissionId}")
    int updateRolePermissionMapping(@Param("initial") RolePermissionMapping initial, @Param("ultimately") RolePermissionMapping ultimately);
    // 查询角色权限映射
    @Select("SELECT * FROM role_permission_mapping_table WHERE role_id = #{roleId}")
    List<RolePermissionMapping> getRolePermissionMapping(Insert roleId);
    // 查询角色权限映射列表
    @Select("SELECT * FROM role_permission_mapping_table")
    List<RolePermissionMapping> getRolePermissionMappingList();
}
