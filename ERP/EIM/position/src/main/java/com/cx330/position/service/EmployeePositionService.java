package com.cx330.position.service;

import com.cx330.entity.EmployeePositionMap;

import java.util.List;

public interface EmployeePositionService {
    // 添加映射关系
    int addMapping(EmployeePositionMap employeePositionMap);
    // 修改映射关系
    int updateMapping(EmployeePositionMap employeePositionMap);
    // 根据映射id删除映射关系
    int deleteMappingById(int mapId);
    // 根据员工id删除映射关系
    int deleteMappingByEmployeeId(int employeeId);
    // 根据职位id删除映射关系
    int deleteMappingByPositionId(int positionId);
    // 根据员工id查询映射关系
    List<EmployeePositionMap> getMappingByEmployeeId(int employeeId);
    // 根据职位id查询映射关系
    List<EmployeePositionMap> getMappingByPositionId(int positionId);
    // 查询所有映射关系
    List<EmployeePositionMap> getAllMapping();
}
