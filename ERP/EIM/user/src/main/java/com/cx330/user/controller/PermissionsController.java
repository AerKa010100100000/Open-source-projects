package com.cx330.user.controller;


import com.cx330.entity.Permission;
import com.cx330.user.service.PermissionsService;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Permissions")
public class PermissionsController {
    @Autowired
    private PermissionsService permissionsService;


    @Secured("ROLE_ADMINISTRATE")
    @GetMapping("/getPermissions")
    public Result<Permission> getPermissions(Integer permissionId) {
        return new Result<Permission>().ok().data(permissionsService.getPermission(permissionId));
    }

    @GetMapping("/getAllPermissions")
    @Secured("ROLE_ADMINISTRATE")
    public Result<List<Permission>> getAllPermissions() {
        return new Result<List<Permission>>()
                .ok()
                .data(permissionsService.getAllPermissions());
    }


    @Secured("ROLE_ADMINISTRATE")
    @PostMapping("/addPermission")
    public Result<Permission> addPermission(Permission permission) {
        int addPermission = permissionsService.addPermission(permission);
        if (addPermission > 0) {
            return new Result<Permission>()
                   .ok();
        } else {
            return new Result<Permission>()
                    .error()
                    .message("权限添加失败");
        }
    }



    @Secured("ROLE_ADMINISTRATE")
    @PutMapping("/updatePermission")
    public Result<Permission> updatePermission(Permission permission) {
        int updatePermission = permissionsService.updatePermission(permission);
        if (updatePermission > 0) {
            return new Result<Permission>()
                   .ok();
        } else {
            return new Result<Permission>()
                    .error()
                    .message("权限更新失败");
        }
    }



    @Secured("ROLE_ADMINISTRATE")
    @DeleteMapping("/deletePermission")
    public Result<String> deletePermission(Integer permissionId) {
        int deletePermission = permissionsService.deletePermission(permissionId);
        if (deletePermission > 0) {
            return new Result<String>()
                   .ok()
                   .message("权限删除成功");
        } else {
            return new Result<String>()
                    .error()
                    .message("权限删除失败");
        }
    }
}
