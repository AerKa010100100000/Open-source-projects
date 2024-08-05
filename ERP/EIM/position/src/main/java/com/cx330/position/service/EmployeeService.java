package com.cx330.position.service;

import com.cx330.entity.Employee;

import java.util.List;

public interface EmployeeService {
    // 添加员工信息
    int addEmployee(Employee employee);
    // 删除员工信息
    int deleteEmployee(int employeeId);
    // 修改员工信息
    int updateEmployee(Employee employee);
    // 查询员工信息
    Employee getEmployee(int employeeId);
    // 查询员工列表
    List<Employee> getEmployeeList();
}
