package com.cx330.entity;

import lombok.Data;

@Data
public class Permission {
    // 权限ID
    private Integer permissionId;
    // 权限名称
    private String permissionName;
    // 权限描述
    private String permissionDesc;
}
