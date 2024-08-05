package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 盘点详情信息实体类
@Data
public class InventoryCheckDetail implements Serializable {
    private Integer detailId; // 详情ID
    private Integer checkId; // 盘点编号
    private Integer productId; // 商品编号
    private Integer stockQuantity; // 库存数量
    private Integer actualStockQuantity; // 实际库存数量
    private Integer stockDifference; // 库存差异
    private String differenceReason; // 差异原因
}
