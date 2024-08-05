package com.cx330.user.mapper;


import com.cx330.entity.UserSession;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SessionMapper {
    // 新建会话表
    @Insert("INSERT INTO user_session_table (user_id,start_time, end_time, login_ip )VALUES (#{userId},#{startTime},#{endTime}, #{loginIp})")
    int insertSession(UserSession userSession);
    // 删除会话表
    @Insert("DELETE FROM user_session_table WHERE user_id = #{userId}")
    int deleteSession(@Param("userId") String userId);
    // 查询会话列表
    @Insert("SELECT * FROM user_session_table")
    List<UserSession> selectAllSession();
}
