package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Position implements Serializable {
    // 岗位ID
    private Integer positionId;
    // 岗位名称
    private String positionName;
    // 岗位编码
    private String positionCode;
    // 岗位职责描述
    private String responsibilityDesc;
    // 薪资范围
    private String salaryRange;
    // 学历要求
    private String educationBackgroundRequirement;
    // 工作经验要求
    private String workExperienceRequirement;
    // 岗位状态（开放、关闭、招聘中）
    private String positionStatus;
}
