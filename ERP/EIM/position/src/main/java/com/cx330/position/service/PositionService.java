package com.cx330.position.service;

import com.cx330.entity.Position;

import java.util.List;

public interface PositionService {
    // 新增岗位
    int insertPosition(Position position);
    // 删除岗位
    int deletePosition(Integer positionId);
    // 修改岗位
    int updatePosition(Position position);
    // 查询岗位列表
    List<Position> getPositionList();
    // 根据id查询岗位信息
    Position getPositionById(Integer positionId);
}
