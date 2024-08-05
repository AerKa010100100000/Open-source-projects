package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Employee implements Serializable {
    // 员工ID
    private Integer employeeId;
    // 员工姓名
    private String firstName;
    // 员工姓氏
    private String lastName;
    // 职位ID
    private Integer positionID;
    // 邮箱
    private String email;
    // 手机号
    private String phone;
    // 入职日期
    private String hireDate;
    // 当前薪资
    private Double currentSalary;
    // 员工状态（在职、离职、休假）
    private String employeeStatus;
}
