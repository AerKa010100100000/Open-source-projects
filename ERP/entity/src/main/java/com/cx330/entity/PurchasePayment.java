package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 采购支付实体类
@Data
public class PurchasePayment implements Serializable {
    private int paymentId; // 支付ID
    private int orderId; // 订单ID
    private String paymentDate; // 支付日期
    private Double amount; // 支付金额
    private String paymentMethod; // 支付方式
}
