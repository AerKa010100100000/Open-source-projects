package com.cx330.entity;

import lombok.Data;
import java.io.Serializable;

// 商品出库基本信息实体类
@Data
public class OutOrder implements Serializable {
    private Integer outId; // 出库编号
    private Integer productId; // 商品编号
    private String reason; // 出库原因
    private Integer quantity; // 出库数量
    private String transportMethod; // 运输方式
    private String outTime; // 出库时间
    private String createdAt; // 创建时间
    private String updatedAt; // 修改时间
}
