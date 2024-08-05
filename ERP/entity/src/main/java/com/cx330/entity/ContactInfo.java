package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 联系信息实体类
@Data
public class ContactInfo implements Serializable {
    private Integer contactId; // 公司联系ID
    private Integer companyId; // 公司ID
    private String contactType; // 联系类型（如总部、分部等）
    private String phoneNumber; // 电话号码
    private String faxNumber; // 传真号码
    private String email; // 电子邮件
}
