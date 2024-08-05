package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 盘点基本信息实体类
@Data
public class InventoryCheck implements Serializable {
    private Integer checkId; // 盘点编号
    private String checkTime; // 盘点时间
    private Integer warehouseId; // 仓库ID
    private String checkType; // 盘点类型（周期盘点、全盘点）
    private Integer employeeId; // 员工ID
    private String checkRemark; // 盘点备注
}
