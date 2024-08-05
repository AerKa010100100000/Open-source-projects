package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
// 商品价格信息表实体类
public class ProductPrice implements Serializable {
    private Integer priceId; // 商品价格编号
    private BigDecimal marketPrice; // 市场价格
    private BigDecimal purchaseCost; // 采购成本
    private BigDecimal salePrice; // 销售价格
    private BigDecimal profit; // 利润
}
