package com.cx330.department.service;

import com.cx330.entity.Department;

import java.util.List;

public interface DepartService {
    // 添加部门信息
    int addDepart(Department department);
    // 修改部门信息
    int updateDepart(Department department);
    // 删除部门信息
    int deleteDepart(int departmentId);
    // 根据id查询部门信息
    Department getDepartById(int departmentId);
    // 根据部门名称查询部门信息
    Department getDepartByName(String departmentName);
    // 查询部门信息列表
    List<Department> getAllDepart();
}
