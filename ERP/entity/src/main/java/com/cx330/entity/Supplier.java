package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 供应商基本表实体类
@Data
public class Supplier implements Serializable {
    private int supplierId; // 供应商编号
    private String supplierName; // 供应商名称
    private String contactPerson; // 联系人
    private String contactInfo; // 联系方式
    private String email; // 电子邮箱
    private String address; // 地址
    private String status; // 状态（活跃、暂停、黑名单）
    private String contractExpiryDate; // 合同到期日期
    private Double creditAmount; // 信用额度
    private String paymentTerms; // 付款条款
    private String createdAt; // 创建时间
    private String updatedAt; // 修改时间
}
