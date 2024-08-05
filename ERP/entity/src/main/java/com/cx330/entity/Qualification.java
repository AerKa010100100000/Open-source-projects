package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Qualification implements Serializable {
    // 资格ID
    private Integer qualificationId;
    // 资格名称
    private String qualificationName;
    // 资格描述
    private String qualificationDesc;
}
