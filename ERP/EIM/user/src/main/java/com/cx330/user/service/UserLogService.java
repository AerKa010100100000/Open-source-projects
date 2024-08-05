package com.cx330.user.service;

import com.cx330.entity.UserAuditLog;

import java.util.List;

public interface UserLogService {
    // 添加日志
    int addUserLog(Integer userId, String operation, String operationInfo);

    // 删除日志
    int deleteUserLog(List<Integer> id);
    // 根据用户id查询日志
    List<UserAuditLog> getUserLogByUserId(Integer userId);
    // 查询所有日志
    List<UserAuditLog> getAllUserLog();

}
