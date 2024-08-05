package com.cx330.user.mapper;


import com.cx330.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {
    // 添加角色信息
    @Insert("INSERT INTO role_table (role_name, role_desc) VALUES (#{roleName}, #{roleDesc})")
    int addRole(Role role);
    // 删除角色信息
    @Delete("DELETE FROM role_table WHERE role_id = #{roleId}")
    int deleteRole(Integer roleId);
    // 修改角色信息
    @Insert("UPDATE role_table SET role_name = #{roleName}, role_desc = #{roleDesc} WHERE role_id = #{roleId}")
    int updateRole(Role role);
    // 查询角色信息
    @Select("SELECT * FROM role_table WHERE role_id = #{roleId}")
    Role getRoleName(Integer roleId);
    // 查询角色列表
    @Select("SELECT role_name FROM role_table")
    List<Role> getAllRoleNames();
}
