package com.cx330.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Skill implements Serializable {
    // 技能ID
    private Integer skillId;
    // 技能名称
    private String skillName;
    // 技能描述
    private String skillDesc;
}
