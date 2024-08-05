package com.cx330.department.mapper;

import com.cx330.entity.PositionDepartmentMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PositionDepartmentMapper {
    // 添加映射
    int insert(PositionDepartmentMap positionDepartmentMap);
    // 修改映射
    int update(PositionDepartmentMap positionDepartmentMap);
    // 根据映射id删除映射
    int deleteByMapId(Integer mapId);
    // 根据部门id删除映射
    int deleteByDeptId(Integer departmentId);
    // 根据职位id删除映射
    int deleteByPosId(Integer positionId);
    // 根据映射id查询映射
    PositionDepartmentMap selectByMapId(Integer mapId);
    // 根据部门id查询映射
    List<PositionDepartmentMap> selectByDeptId(Integer deptId);
    // 根据职位id查询映射
    List<PositionDepartmentMap> selectByPosId(Integer posId);
    // 查询所有映射
    List<PositionDepartmentMap> selectAll();
}
