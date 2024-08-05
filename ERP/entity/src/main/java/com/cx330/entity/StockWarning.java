package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 库存预警信息实体类
@Data
public class StockWarning implements Serializable {
    private Integer warningId; // 预警ID
    private Integer productId; // 商品编号
    private Integer supplierId; // 供应商编号
    private Integer quantity; // 预警数量
    private String warningTime; // 预警时间
    private String reason; // 预警原因
    private String handleStatus; // 处理状态（未处理、已处理）
    private String handleTime; // 处理时间
    private String handlePerson; // 处理人
    private String handleRemark; // 处理备注
}
