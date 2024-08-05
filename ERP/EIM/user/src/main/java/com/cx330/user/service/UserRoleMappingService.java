package com.cx330.user.service;

import com.cx330.entity.UserRoleMapping;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface UserRoleMappingService {
    // 添加映射关系
    int addUserRoleMapping(UserRoleMapping userRoleMapping);
    // 根据userId删除映射关系
    int deleteUserRoleMappingByUserId(Integer userId);
    // 根据角色id删除映射关系
    int deleteUserRoleMappingByRoleId(Integer roleId);
    // 查询映射关系
    List<UserRoleMapping> queryUserRoleMapping(Integer userId);
    // 更新映射关系
    int updateUserRoleMapping(UserRoleMapping initial, UserRoleMapping ultimately) throws Exception;
}
