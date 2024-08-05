package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class Customer implements Serializable {
    // 客户ID
    private Integer customerId;
    // 客户名称
    private String customerName;
    // 地址
    private String address;
    // 联系方式
    private String contactInfo;
    // 税务信息
    private String taxInfo;
    // 银行账号
    private String bankAccount;
    // 信用编号
    private String creditCode;
    // 分类编号
    private String categoryCode;
    // 交易编号
    private String tradeCode;
    // 创建时间
    private String createdAt;
    // 更新时间
    private String updatedAt;
}
