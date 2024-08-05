package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 供应商合同表实体类
@Data
public class SupplierContract implements Serializable {
    private int contractId; // 合同ID
    private String contractNo; // 合同编号
    private int supplierId; // 供应商基本表
    private String startDate; // 合同开始日期
    private String endDate; // 合同结束日期
    private double contractValue; // 合同价值
}
