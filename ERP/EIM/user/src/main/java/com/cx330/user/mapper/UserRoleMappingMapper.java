package com.cx330.user.mapper;

import com.cx330.entity.UserRoleMapping;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRoleMappingMapper {
    // 添加映射关系
    @Insert("INSERT INTO user_role_mapping_table (user_id, role_id) VALUES (#{userId}, #{roleId})")
    int addUserRoleMapping(UserRoleMapping userRoleMapping);
    // 根据userId删除映射关系
    @Delete("DELETE FROM user_role_mapping_table WHERE user_id = #{userId}")
    int deleteUserRoleMappingByUserId(Integer userId);
    // 根据角色id删除映射关系
    @Delete("DELETE FROM user_role_mapping_table WHERE role_id = #{roleId}")
    int deleteUserRoleMappingByRoleId(Integer roleId);
    // 查询映射关系
    @Select("SELECT * FROM user_role_mapping_table WHERE user_id = #{userId}")
    List<UserRoleMapping> queryUserRoleMapping(Integer userId);
    // 更新映射关系
    @Update("UPDATE user_role_mapping_table SET role_id = #{ultimately.roleId} WHERE user_id = #{initial.userId} AND role_id = #{initial.roleId}")
    int updateUserRoleMapping(@Param("initial") UserRoleMapping initial, @Param("ultimately") UserRoleMapping ultimately);
}
