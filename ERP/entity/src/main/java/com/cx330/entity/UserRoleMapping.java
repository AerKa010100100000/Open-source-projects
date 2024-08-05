package com.cx330.entity;

import lombok.Data;

@Data
public class UserRoleMapping {
    // 用户ID
    private Integer userId;
    // 角色ID
    private Integer roleId;
    private Integer mappingId;
}
