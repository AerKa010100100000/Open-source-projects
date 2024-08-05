package com.cx330.department.mapper;


import com.cx330.entity.DepartmentBudget;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartBudgetMapper {
    // 创建部门预算表
    @Insert("INSERT INTO department_budget_table (budget_id, department_id, financial_year, total_budget, spent_budget, remaining_budget) VALUES(#{budgetId}, #{departmentId}, #{financialYear}, #{totalBudget}, #{spentBudget}, #{remainingBudget})")
    int addDepartBudget(DepartmentBudget departmentBudget);
    // 更新部门预算表
    @Update("UPDATE department_budget_table SET total_budget = #{totalBudget}, spent_budget = #{spentBudget}, remaining_budget = #{remainingBudget} WHERE budget_id = #{budgetId}")
    int updateDepartBudget(DepartmentBudget departmentBudget);
    // 删除部门预算表
    @Delete("DELETE FROM department_budget_table WHERE budget_id = #{budgetId}")
    int deleteDepartBudget(Integer budgetId);
    // 根据部门ID查询部门预算表
    @Select("SELECT * FROM department_budget_table WHERE department_id = #{departmentId}")
    DepartmentBudget getDepartBudgetByDeptId(@Param("departmentId") Integer departmentId);
    // 根据预算ID查询部门预算表
    @Select("SELECT * FROM department_budget_table WHERE budget_id = #{budgetId}")
    DepartmentBudget getDepartBudgetByDeptName(Integer budgetId);
    // 查询部门预算表列表
    @Select("SELECT * FROM department_budget_table")
    List<DepartmentBudget> getAllDepartBudget();
}
