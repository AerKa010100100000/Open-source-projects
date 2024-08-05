package com.cx330.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class PositionChange implements Serializable {
    // 岗位变动ID
    private int positionChangeId;
    // 岗位ID
    private int positionId;
    // 员工ID
    private int employeeId;
    // 变动时间
    private String changeTime;
    // 变动原因
    private String changeReason;
    // 变动前岗位
    private String beforePosition;
    // 变动后岗位
    private String afterPosition;
    // 变动前薪资
    private Double beforeSalary;
    // 变动后薪资
    private Double afterSalary;
}
