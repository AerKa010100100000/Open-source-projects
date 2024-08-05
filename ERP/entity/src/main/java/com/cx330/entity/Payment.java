package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 收款表实体类
@Data
public class Payment implements Serializable {
    private int paymentId; // 收款编号
    private int invoiceId; // 发票编号
    private String paymentDate; // 收款日期
    private Double amount; // 收款金额
}
