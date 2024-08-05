package com.cx330.user.controller;


import com.cx330.entity.User;
import com.cx330.user.service.UserService;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    // 编辑用户
    @PutMapping("/updateUser")
    @Secured(value = {"ROLE_ADMINISTRATE", "ROLE_REGULAR_USER"})
    public Result<Object> updateUser(User user) throws Exception {
        int updateUser = userService.updateUser(user);
        if (updateUser == 1) {
            return new Result<>().ok().message("更新成功");
        }
        return new Result<>().error().message("更新失败");
    }


    // 删除用户
    @Secured("ROLE_ADMINISTRATE")
    @DeleteMapping("/deleteUser")
    public Result<Object> deleteUser(Integer userID) {

        int deleteUser = userService.deleteUser(userID);
        if (deleteUser == 1) {
            return new Result<>().ok().message("删除成功");
        }
        return new Result<>().error().message("删除失败");
    }


    // 查询用户信息
    @GetMapping("/queryUserById")
    @Secured(value = {"ROLE_ADMINISTRATE", "ROLE_REGULAR_USER"})
    public Result<User> queryUserById(Integer userId) throws Exception {
        User user = userService.queryUserById(userId);
        if (user != null){
            return new Result<User>().ok().data(user);
        }
        return new Result<User>().error().message("系统错误");
    }


    // 查询用户列表
    @GetMapping("/queryUserList")
    @Secured("ROLE_ADMINISTRATE")
    public Result<List<User>> queryUserList() throws Exception {
        List<User> userList = userService.queryUserList();
        if (userList != null){
            return new Result<List<User>>().ok().data(userList);
        }
        return new Result<List<User>>().error().message("系统错误");
    }



    // 管理员重置密码
    @PutMapping("/resetPassword")
    @Secured("ROLE_ADMINISTRATE")
    public Result<Object> resetPassword(Integer userId) throws Exception {
        int resetPassword = userService.resetPassword(userId);
        if (resetPassword == 1) {
            return new Result<>().ok().message("重置密码成功");
        }
        return new Result<>().error().message("重置密码失败");
    }

    // 用户修改密码
    @PutMapping("/updatePassword")
    @Secured(value = {"ROLE_ADMINISTRATE", "ROLE_REGULAR_USER"})
    public Result<Object> updatePassword(Integer userId, String oldPassword, String newPassword) {
        try {
            int updatePassword = userService.updatePassword(userId, oldPassword, newPassword);
            if (updatePassword == 1) {
                return new Result<>().ok().message("修改密码成功");
            }
            return new Result<>().error().message("修改密码失败");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // 注销用户
    @PutMapping("/cancelUser")
    @Secured(value = {"ROLE_ADMINISTRATE", "ROLE_REGULAR_USER"})
    public Result<Object> cancelUser(Integer userId) throws Exception {
        int cancelUser = userService.cancelUser(userId);
        if (cancelUser == 1) {
            return new Result<>().ok().message("注销成功");
        }
        return new Result<>().error().message("注销失败");
    }

    // 激活用户
    @PutMapping("/activateUser")
    @Secured("ROLE_ADMINISTRATE")
    public Result<Object> activateUser(Integer userId) {
        System.out.println(userId);
        try {
            int activateUser = userService.activateUser(userId);
            if (activateUser == 1) {
                return new Result<>().ok().message("激活成功");
            }
            return new Result<>().error().message("激活失败");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
