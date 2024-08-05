package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Project implements Serializable {
    // 项目ID
    private Integer projectId;
    // 项目名称
    private String projectName;
    // 项目开始日期
    private String startDate;
    //项目预计结束日期
    private String endDate;
    // 项目描述
    private String projectDesc;
}
