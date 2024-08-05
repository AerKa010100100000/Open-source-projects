package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class CustomerTrade implements Serializable {
    // 交易编号
    private int tradeId;
    // 购买的产品名称
    private String productName;
    // 购买的产品数量
    private int quantity;
    // 购买的产品单价
    private Double price;
    // 付款情况
    private String paymentStatus;
}
