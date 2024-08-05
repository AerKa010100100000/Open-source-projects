package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 销售订单表实体类
@Data
public class SaleOrder implements Serializable {
    private int orderId; // 订单编号
    private int customerId; // 客户编号
    private String orderDate; // 订单日期
    private String deliveryAddress; // 发货地址
    private String orderStatus; // 订单状态（新订单、已发货、已完成）
}
