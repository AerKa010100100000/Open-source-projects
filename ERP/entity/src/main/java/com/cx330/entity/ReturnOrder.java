package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 退货单实体类
@Data
public class ReturnOrder implements Serializable {
    private int returnId; // 退货编号
    private int orderId; // 订单编号
    private Double returnPrice; // 应退价格
    private Double refundAmount; // 退款金额
    private String reason; // 退货原因
    private String returnStatus; // 退货状态（未入库、已入库）
    private String refundStatus; // 退款状态（正在处理、已退款）
    private String refundDate; // 退款日期
    private String createdAt; // 创建时间
    private String updatedAt; // 修改时间
}
