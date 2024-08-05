package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 仓库信息实体类
@Data
public class Warehouse implements Serializable {
    private Integer warehouseId; // 仓库编号
    private String warehouseName; // 仓库名称
    private String warehouseCode; // 仓库代码
    private String address; // 地址
    private String manager; // 负责人
    private String specialRequest; // 特殊要求
}