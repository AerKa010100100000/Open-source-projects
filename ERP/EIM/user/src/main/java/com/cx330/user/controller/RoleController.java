package com.cx330.user.controller;


import com.cx330.entity.Role;
import com.cx330.user.service.RoleService;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Secured("ROLE_ADMINISTRATE")
    @GetMapping("/getAllRoleNames")
    public Result<Object> getAllRoleNames() {
        return new Result<>().ok().data(roleService.getAllRoleNames());
    }


    @Secured("ROLE_ADMINISTRATE")
    @GetMapping("/getRoleName")
    public Result<Role> getRoleName(Integer roleId) {
        return new Result<Role>().ok().data(roleService.getRoleName(roleId));
    }


    @Secured("ROLE_ADMINISTRATE")
    @PostMapping("/addRole")
    public Result<Object> addRole( Role role) {
        int addRole = roleService.addRole(role);
        if (addRole > 0) {
            return new Result<>().ok().data("添加角色成功");
        }
        return new Result<>().error().message("添加角色失败");
    }


    @Secured("ROLE_ADMINISTRATE")
    @PutMapping("/updateRole")
    public Result<Object> updateRole(Role role) {
        int updateRole = roleService.updateRole(role);
        if (updateRole > 0) {
            return new Result<>().ok().data("更新角色成功");
        }
        return new Result<>().error().message("更新角色失败");
    }


    @Secured("ROLE_ADMINISTRATE")
    @DeleteMapping("/deleteRole")
    public Result<Object> deleteRole(Integer roleId) {
        int deleteRole = roleService.deleteRole(roleId);
        if (deleteRole > 0) {
            return new Result<>().ok().data("删除角色成功");
        }
        return new Result<>().error().message("删除角色失败");
    }

}
