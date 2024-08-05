package com.cx330.user.service.impl;

import com.cx330.entity.User;
import com.cx330.entity.UserAuditLog;
import com.cx330.user.mapper.UserLogMapper;
import com.cx330.user.service.UserLogService;
import com.cx330.utility.ThreadLocalBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserLogImpl implements UserLogService {
    @Autowired
    private UserLogMapper userLogMapper;

    private static final UserAuditLog userAuditLog = new UserAuditLog();
    @Override
    public int addUserLog(Integer userId, String operation, String operationInfo) {
        userAuditLog.setUserId(userId);
        userAuditLog.setOperation(operation);
        userAuditLog.setOperationInfo(operationInfo);
        int addLog = userLogMapper.addLog(userAuditLog);
        if (addLog > 0)
            return addLog;
        return 0;
    }
    @Override
    public int deleteUserLog(List<Integer> logId) {
        return userLogMapper.deleteLog(logId);
    }

    @Override
    public List<UserAuditLog> getUserLogByUserId(Integer userId) {
        return userLogMapper.queryLogByUser(userId);
    }

    @Override
    public List<UserAuditLog> getAllUserLog() {
        return userLogMapper.queryAllLog();
    }
}
