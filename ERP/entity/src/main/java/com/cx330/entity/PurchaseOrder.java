package com.cx330.entity;


import lombok.Data;

import java.io.Serializable;

// 采购订单实体类
@Data
public class PurchaseOrder implements Serializable {
    private int orderId; // 订单ID
    private int supplierId; // 供应商编号
    private int productId; // 商品编号
    private String orderDate; // 订单日期
    private String deliveryDate; // 预计交付日期
    private String receiveDate; // 实际接收日期
    private int quantity; // 订单数量
    private Double unitPrice; // 单价
    private Double totalPrice; // 总价格
    private String orderStatus; // 订单状态（待发货、已发货、已接收）
}
