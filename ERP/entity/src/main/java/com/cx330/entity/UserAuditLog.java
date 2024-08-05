package com.cx330.entity;

import lombok.Data;

@Data
public class UserAuditLog {
    // 日志ID
    private Integer logId;
    // 用户ID
    private Integer userId;
    // 操作
    private String operation;
    // 操作时间
    private String operationTime;
    // 操作信息
    private String operationInfo;
}
