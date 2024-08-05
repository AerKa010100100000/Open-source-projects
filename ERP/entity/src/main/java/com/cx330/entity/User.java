package com.cx330.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
public class User implements Serializable {
    // 用户ID
    private Integer userID;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 邮箱
    private String email;
    // 手机号
    private String phone;
    // 用户名字
    private String firstName;
    // 用户姓氏
    private String lastName;
    // 激活状态
    private int isActive;
    // 创建时间
    private String createdAt;
    // 最后登录时间
    private String lastLoginAt;
    // 公司ID
    @Getter
    private Integer companyId;

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static User fromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, User.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
