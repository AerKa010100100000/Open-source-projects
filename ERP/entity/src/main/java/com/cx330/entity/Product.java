package com.cx330.entity;

import lombok.Data;

@Data
// 商品基本信息表实体类
public class Product {
    private int productId; // 商品编号
    private String productName; // 商品名称
    private String brand; // 品牌
    private String spec; // 规格
    private String model; // 型号
    private String packingUnit; // 包装单位
    private int shelfLife; // 商品保质期
    private String createdAt; // 创建时间
    private String updatedAt; // 修改时间
}
