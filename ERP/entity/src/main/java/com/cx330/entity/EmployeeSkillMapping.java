package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeSkillMapping implements Serializable {
    // 员工ID
    private Integer employeeId;
    // 技能ID
    private Integer skillId;
    // 技能等级
    private Integer skillLevel;
}
