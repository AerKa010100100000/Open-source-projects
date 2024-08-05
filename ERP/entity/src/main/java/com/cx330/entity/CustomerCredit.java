package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerCredit implements Serializable {
    // 信用编号
    private int creditId;
    // 信用额度
    private Double creditAmount;
    // 信用期限
    private int creditTerm;
    // 支付方式
    private String paymentMethod;
}
