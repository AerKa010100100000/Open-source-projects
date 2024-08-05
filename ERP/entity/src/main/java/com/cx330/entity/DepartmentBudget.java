package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DepartmentBudget implements Serializable {
    // 预算ID
    private Integer budgetId;
    // 部门ID
    private Integer departmentId;
    // 财政年度
    private String financialYear;
    // 预算总额
    private Double totalBudget;
    // 已用预算
    private Double spentBudget;
    // 剩余预算
    private Double remainingBudget;
}
