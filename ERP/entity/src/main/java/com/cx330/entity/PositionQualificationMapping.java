package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PositionQualificationMapping implements Serializable {
    // 岗位ID
    private Integer positionId;
    // 资格ID
    private Integer qualificationId;
}
