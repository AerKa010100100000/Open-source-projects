package com.cx330.entity;


import lombok.Data;

import java.io.Serializable;

// 供应商绩效表实体类
@Data
public class SupplierPerformance implements Serializable {
    private int performanceId; // 绩效ID
    private int supplierId; // 供应商基本表
    private String startDate; // 绩效评估开始日期
    private String endDate; // 绩效评估结束日期
    private Double onTimeDeliveryRate; // 准时交货率
    private Double qualityFailureRate; // 质量失败率
    private Double costPerformanceIndex; // 成本绩效指数
}
