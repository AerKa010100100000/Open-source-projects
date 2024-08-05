package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DepartmentCompanyMap implements Serializable {
    private Integer mapId;
    private Integer departmentId;
    private Integer companyId;
}
