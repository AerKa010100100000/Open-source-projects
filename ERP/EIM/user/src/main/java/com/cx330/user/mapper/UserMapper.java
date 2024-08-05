package com.cx330.user.mapper;


import com.cx330.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    // 编辑用户
    @Update("UPDATE user SET username = #{username}, password = #{password}, email = #{email}, phone = #{phone}, first_name = #{firstName}, last_name = #{lastName}, company_id = #{companyId} WHERE user_id = #{userID}")
    int updateUser(User user);
    // 删除用户
    @Delete("DELETE FROM user WHERE user_id = #{userID}")
    int deleteUser(@Param("userID") Integer userID);
    // 查询用户-- 根据ID查询
    @Select("SELECT * FROM user WHERE user_id = #{userID}")
    User queryUserById(@Param("userID") Integer userID);
    // 查询用户列表
    @Select("SELECT * FROM user")
    List<User> queryUserList();
    // 重置密码
    @Update("UPDATE user SET password = #{password} WHERE user_id = #{userID}")
    int resetPassword(@Param("userID") Integer userID, @Param("password") String password);
    // 设置状态
    @Update("UPDATE user SET is_active = #{status} WHERE user_id = #{userID}")
    int setStatus(@Param("userID") Integer userID, @Param("status") Integer status);
    // 根据用户名查询用户信息
    @Select("SELECT * FROM user WHERE username = #{username}")
    User queryUserByUsername(@Param("username") String username);

    // 注册用户
    @Insert("INSERT INTO user(username, password, email, phone, first_name, last_name, company_id) VALUES(#{username}, #{password}, #{email}, #{phone}, #{firstName}, #{lastName}, #{companyId})")
    int addUser(User user);

}
