package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 质量检测实体类
@Data
public class QualityCheck implements Serializable {
    private int checkId; // 质检ID
    private int orderId; // 订单ID
    private String checkDate; // 检查日期
    private String checkResult; // 检查结果（合格、不合格）
    private String checkRemark; // 检查备注
}
