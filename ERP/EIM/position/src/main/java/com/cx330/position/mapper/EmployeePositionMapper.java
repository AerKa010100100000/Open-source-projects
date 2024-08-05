package com.cx330.position.mapper;


import com.cx330.entity.EmployeePositionMap;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeePositionMapper {
    // 添加映射关系
    @Insert("INSERT INTO employee_position_map (employee_id, position_id) VALUES (#{employeeId}, #{positionId})")
    int addMapping(EmployeePositionMap employeePositionMap);
    // 修改映射关系
    @Update("UPDATE employee_position_map SET employee_id = #{employeeId}, position_id = #{positionId} WHERE map_id = #{mapId}")
    int updateMapping(EmployeePositionMap employeePositionMap);
    // 根据映射id删除映射关系
    @Delete("DELETE FROM employee_position_map WHERE map_id = #{mapId}")
    int deleteMappingById(int mapId);

    // 根据员工id删除映射关系
    @Delete("DELETE FROM employee_position_map WHERE employee_id = #{employeeId}")
    int deleteMappingByEmployeeId(int employeeId);
    // 根据职位id删除映射关系
    @Delete("DELETE FROM employee_position_map WHERE position_id = #{positionId}")
    int deleteMappingByPositionId(int positionId);
    // 根据员工id查询映射关系
    @Select("SELECT * FROM employee_position_map WHERE employee_id = #{employeeId}")
    List<EmployeePositionMap> getMappingByEmployeeId(int employeeId);
    // 根据职位id查询映射关系
    @Select("SELECT * FROM employee_position_map WHERE position_id = #{positionId}")
    List<EmployeePositionMap> getMappingByPositionId(int positionId);
    // 查询所有映射关系
    @Select("SELECT * FROM employee_position_map")
    List<EmployeePositionMap> getAllMapping();

}
