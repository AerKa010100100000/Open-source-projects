package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

// 服务信息实体类
@Data
public class ServiceInfo implements Serializable {
    private Integer serviceId; // 服务ID
    private String serviceName; // 服务名称
    private String serviceCode; // 服务代码
    private BigDecimal unitPrice; // 单价
    private Integer companyId; // 公司ID

    public ServiceInfo() {
    }
}
