package com.cx330.user.mapper;

import com.cx330.entity.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermissionsMapper {
    // 添加权限
    @Insert("INSERT INTO permission_table (permission_name, permission_desc) VALUES (#{permissionName}, #{permissionDesc})")
    int addPermission(Permission permission);
    // 删除权限
    @Delete("DELETE FROM permission_table WHERE permission_id = #{permissionId}")
    int deletePermission(int permissionId);
    // 修改权限
    @Update("UPDATE permission_table SET permission_name = #{permissionName}, permission_desc = #{permissionDesc} WHERE permission_id = #{permissionId}")
    int updatePermission(Permission permission);
    // 查询权限
    @Select("SELECT * FROM permission_table WHERE permission_id = #{permissionId}")
    Permission getPermission(int permissionId);
    // 查询所有权限
    @Select("SELECT * FROM permission_table")
    List<Permission> getAllPermissions();
}
