package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 破损基本信息实体类
@Data
public class Damaged implements Serializable {
    private Integer damagedId; // 破损编号
    private Integer productId; // 商品编号
    private Integer supplierId; // 供应商编号
    private Integer stockId; // 库存编号
    private Integer quantity; // 破损数量
    private String reason; // 破损原因
    private String damagedTime; // 破损时间
    private String createdAt; // 创建时间
    private String updatedAt; // 修改时间
}
