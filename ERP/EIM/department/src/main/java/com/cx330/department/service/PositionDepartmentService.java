package com.cx330.department.service;

import com.cx330.entity.PositionDepartmentMap;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PositionDepartmentService {
    // 添加映射
    @Insert("INSERT INTO position_department_map (position_id, department_id) VALUES (#{positionId}, #{departmentId})")
    int insert(PositionDepartmentMap positionDepartmentMap);
    // 修改映射
    @Insert("UPDATE position_department_map SET position_id = #{positionId}, department_id = #{departmentId} WHERE map_id = #{mapId}")
    int update(PositionDepartmentMap positionDepartmentMap);
    // 根据映射id删除映射
    @Delete("DELETE FROM position_department_map WHERE map_id = #{mapId}")
    int deleteByMapId(Integer mapId);
    // 根据部门id删除映射
    @Delete("DELETE FROM position_department_map WHERE department_id = #{departmentId}")
    int deleteByDeptId(Integer departmentId);
    // 根据职位id删除映射
    @Delete("DELETE FROM position_department_map WHERE position_id = #{positionId}")
    int deleteByPosId(Integer positionId);
    // 根据映射id查询映射
    @Select("SELECT * FROM position_department_map WHERE map_id = #{mapId}")
    PositionDepartmentMap selectByMapId(Integer mapId);
    // 根据部门id查询映射
    @Select("SELECT * FROM position_department_map WHERE department_id = #{departmentId}")
    List<PositionDepartmentMap> selectByDeptId(Integer deptId);
    // 根据职位id查询映射
    @Select("SELECT * FROM position_department_map WHERE position_id = #{positionId}")
    List<PositionDepartmentMap> selectByPosId(Integer posId);
    // 查询所有映射
    @Select("SELECT * FROM position_department_map")
    List<PositionDepartmentMap> selectAll();
}
