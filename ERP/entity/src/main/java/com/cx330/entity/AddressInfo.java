package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

// 地址信息实体类
@Data
public class AddressInfo implements Serializable {
    private Integer addressId; // 地址ID
    private Integer companyId; // 公司ID
    private String addressLine; // 地址
    private String city; // 城市
    private String stateProvince; // 州/省
    private String country; // 国家
    private String postalCode; // 邮政编码

    public AddressInfo() {
    }
}
