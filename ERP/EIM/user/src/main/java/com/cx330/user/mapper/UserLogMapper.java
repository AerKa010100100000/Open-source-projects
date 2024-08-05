package com.cx330.user.mapper;

import com.cx330.constant.ConstantValue;
import com.cx330.entity.UserAuditLog;
import com.cx330.user.aop.UserLog;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserLogMapper {
    // 添加日志记录
    @Insert("INSERT INTO user_audit_log_table(user_id, operation, operation_time, operation_info) VALUES(#{userId}, #{operation}, #{operationTime}, #{operationInfo})")
    int addLog(UserAuditLog userAuditLog);
    // 删除日志记录
    @UserLog(operation = ConstantValue.LOG_DELETE, operationInfo = "删除日志记录")
    int deleteLog(@Param("lgoId") List<Integer> lgoIds);
    // 查询指定用户日志记录
    @Select("SELECT * FROM user_audit_log_table WHERE user_id = #{userId}")
    List<UserAuditLog> queryLogByUser(int userId);
    // 查询所有日志记录
    @Select("SELECT * FROM user_audit_log_table")
    List<UserAuditLog> queryAllLog();
}
