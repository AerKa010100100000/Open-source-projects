package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 销售订单详细表实体类
@Data
public class SaleOrderDetail implements Serializable {
    private int detailId; // 订单详细编号
    private int orderId; // 订单编号
    private int productId; // 商品编号
    private int quantity; // 销售数量
    private Double unitPrice; // 销售单价
    private double totalAmount; // 销售总金额
}
