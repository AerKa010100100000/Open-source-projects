package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 商品出库明细实体类
@Data
public class OutOrderDetail implements Serializable {
    private Integer outDetailId; // 出库明细ID
    private Integer outId; // 出库编号
    private Integer stockId; // 库存编号
    private Integer quantity; // 出库数量
    private Double unitPrice; // 出库单价
    private Double totalPrice; // 出库总价
}