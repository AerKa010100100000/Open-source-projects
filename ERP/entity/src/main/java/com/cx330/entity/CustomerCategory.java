package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class CustomerCategory implements Serializable {
    // 分类编号
    private Integer categoryId;
    // 行业
    private String industry;
    // 地区
    private String region;
    // 等级
    private Integer level;
}
