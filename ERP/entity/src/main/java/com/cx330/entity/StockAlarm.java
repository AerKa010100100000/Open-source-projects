package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 库存报警信息实体类
@Data
public class StockAlarm implements Serializable {
    private Integer alarmId; // 报警ID
    private Integer productId; // 商品编号
    private Integer stockId; // 库存编号
    private Integer quantity; // 报警数量
    private String alarmTime; // 报警时间
    private String reason; // 报警原因
    private String handleStatus; // 处理状态（未处理、已处理）
    private String handleTime; // 处理时间
    private String handlePerson; // 处理人
    private String handleRemark; // 处理备注
}
