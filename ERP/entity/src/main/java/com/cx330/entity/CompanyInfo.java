package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

// 公司信息实体类
@Data
public class CompanyInfo implements Serializable {
    private Integer companyId; // 公司ID
    private String name; // 名称
    private String legalName; // 法律名称
    private String taxRegistrationNumber; // 税务登记号
    private Date registrationDate; // 注册日期
    private String type; // 类型（如有限责任公司、股份公司等）
    private String industryType; // 行业类型
    private String website; // 网站
    private String logo; // Logo
    private Date updatedAt; // 更新时间
    private ContactInfo contactInfo; // 联系方式信息
    private List<AddressInfo> addressList; // 地址列表
    private List<ServiceInfo> serviceInfo; // 服务信息
    public CompanyInfo() {
    }
}
