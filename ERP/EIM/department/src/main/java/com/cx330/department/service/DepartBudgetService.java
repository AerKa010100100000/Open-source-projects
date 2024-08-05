package com.cx330.department.service;

import com.cx330.entity.DepartmentBudget;

import java.util.List;

public interface DepartBudgetService {
    // 创建部门预算表
    int addDepartBudget(DepartmentBudget departmentBudget);
    // 更新部门预算表
    int updateDepartBudget(DepartmentBudget departmentBudget);
    // 删除部门预算表
    int deleteDepartBudget(Integer budgetId);
    // 根据部门ID查询部门预算表
    DepartmentBudget getDepartBudgetByDeptId(Integer departmentId);
    // 根据预算ID查询部门预算表
    DepartmentBudget getDepartBudgetByDeptName(Integer budgetId);
    // 查询部门预算表列表
    List<DepartmentBudget> getAllDepartBudget();
}
