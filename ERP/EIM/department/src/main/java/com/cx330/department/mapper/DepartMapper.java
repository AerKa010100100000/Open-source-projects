package com.cx330.department.mapper;


import com.cx330.entity.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartMapper {
    // 添加部门信息
    @Insert("INSERT INTO department_table(department_name, parent_department_id, department_desc) VALUES(#{departmentName}, #{parentDepartmentId}, #{departmentDesc})")
    int addDepart(Department department);
    // 修改部门信息
    @Update("UPDATE department_table SET department_name = #{departmentName}, parent_department_id = #{parentDepartmentId}, department_desc = #{departmentDesc} WHERE department_id = #{departmentId}")
    int updateDepart(Department department);
    // 删除部门信息
    @Delete("DELETE FROM department_table WHERE department_id = #{departmentId}")
    int deleteDepart(int departmentId);
    // 根据id查询部门信息
    @Select("SELECT * FROM department_table WHERE department_id = #{departmentId}")
    Department getDepartById(int departmentId);
    // 根据部门名称查询部门信息
    @Select("SELECT * FROM department_table WHERE department_name = #{departmentName}")
    Department getDepartByName(String departmentName);
    // 查询部门信息列表
    @Select("SELECT * FROM department_table")
    List<Department> getAllDepart();
}
