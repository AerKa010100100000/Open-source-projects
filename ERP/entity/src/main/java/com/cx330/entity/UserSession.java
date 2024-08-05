package com.cx330.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserSession {
    // 会话ID
    private Integer sessionId;
    // 用户ID
    private Integer userId;
    // 会话开始时间
    private Date startTime;
    // 会话结束时间
    private Date endTime;
    // 登录IP
    private String loginIp;
}
