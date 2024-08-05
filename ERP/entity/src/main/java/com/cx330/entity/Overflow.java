package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 溢出基本信息实体类
@Data
public class Overflow implements Serializable {
    private Integer overflowId; // 溢出编号
    private Integer productId; // 商品编号
    private Integer stockId; // 库存编号
    private Integer quantity; // 溢出数量
    private String reason; // 溢出原因
    private String overflowTime; // 溢出时间
    private String createdAt; // 创建时间
    private String updatedAt; // 修改时间
}
