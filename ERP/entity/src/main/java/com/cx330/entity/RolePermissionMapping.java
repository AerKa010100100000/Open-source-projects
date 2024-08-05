package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RolePermissionMapping implements Serializable {
    private Integer roleId;
    private Integer permissionId;
    private Integer mappingId;
}
