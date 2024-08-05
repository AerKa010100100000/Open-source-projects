package com.cx330.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeePositionMap implements Serializable {
    private Integer mapId;
    private Integer employeeId;
    private Integer positionId;
}
