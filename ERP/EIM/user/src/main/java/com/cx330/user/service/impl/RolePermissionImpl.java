package com.cx330.user.service.impl;

import com.cx330.constant.ConstantValue;
import com.cx330.entity.RolePermissionMapping;
import com.cx330.user.aop.UserLog;
import com.cx330.user.mapper.RolePermissionMappingMapper;
import com.cx330.user.service.RolePermissionService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RolePermissionImpl implements RolePermissionService {
    @Autowired
    private RolePermissionMappingMapper rolePermissionMappingMapper;

    @Override
    @UserLog(operation = ConstantValue.LOG_INSERT, operationInfo = "管理员添加了角色与权限关系")
    public int addRolePermissionMapping(RolePermissionMapping rolePermissionMapping) {
        return rolePermissionMappingMapper.addRolePermissionMapping(rolePermissionMapping);
    }

    @Override
    @UserLog(operation = ConstantValue.LOG_DELETE, operationInfo = "管理员删除了角色与权限关系")
    public int deleteRolePermissionMappingByRoleId(Integer roleId) {
        return rolePermissionMappingMapper.deleteRolePermissionMappingByRoleId(roleId);
    }

    @Override
    @UserLog(operation = ConstantValue.LOG_DELETE, operationInfo = "管理员删除了角色与权限关系")
    public int deleteRolePermissionMappingByPermissionId(Integer permissionId) {
        return rolePermissionMappingMapper.deleteRolePermissionMappingByPermissionId(permissionId);
    }

    @Override
    @UserLog(operation = ConstantValue.LOG_UPDATE, operationInfo = "管理员修改用户角色与权限关系")
    public int updateRolePermissionMapping(RolePermissionMapping initial, RolePermissionMapping ultimately) {
        return rolePermissionMappingMapper.updateRolePermissionMapping(initial, ultimately);
    }

    @Override
    public List<RolePermissionMapping> getRolePermissionMapping(Insert roleId) {
        return rolePermissionMappingMapper.getRolePermissionMapping(roleId);
    }

    @Override
    public List<RolePermissionMapping> getRolePermissionMappingList() {
        return rolePermissionMappingMapper.getRolePermissionMappingList();
    }
}
