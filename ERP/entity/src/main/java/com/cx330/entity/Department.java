package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Department implements Serializable {
    // 部门ID
    private Integer departmentId;
    // 部门名称
    private String departmentName;
    // 上级部门
    private Integer parentDepartmentId;
    // 部门描述
    private String departmentDesc;
}
