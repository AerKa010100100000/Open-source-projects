package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 发票表实体类
@Data
public class Invoice implements Serializable {
    private int invoiceId; // 发票编号
    private int orderId; // 订单编号
    private String invoiceDate; // 发票日期
    private String expiryDate; // 到期日期
    private Double totalAmount; // 总金额
}