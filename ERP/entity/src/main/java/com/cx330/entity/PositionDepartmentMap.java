package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PositionDepartmentMap implements Serializable {
    private Integer mapId;
    private Integer positionId;
    private Integer departmentId;
}
