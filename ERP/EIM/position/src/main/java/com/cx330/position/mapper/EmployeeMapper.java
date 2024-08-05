package com.cx330.position.mapper;

import com.cx330.entity.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    // 添加员工信息
    @Insert("INSERT INTO employee_table (first_name, last_name, email, phone, hire_date, current_salary, employee_status) VALUES (#{firstName}, #{lastName}, #{email}, #{phone}, #{hireDate}, #{currentSalary}, #{employeeStatus})")
    int addEmployee(Employee employee);
    // 删除员工信息
    @Delete("DELETE FROM employee_table WHERE employee_id = #{employeeId}")
    int deleteEmployee(int employeeId);
    // 修改员工信息
    @Update("UPDATE employee_table SET first_name = #{firstName}, last_name = #{lastName}, email = #{email}, phone = #{phone}, hire_date = #{hireDate}, current_salary = #{currentSalary}, employee_status = #{employeeStatus} WHERE employee_id = #{employeeId}")
    int updateEmployee(Employee employee);
    // 查询员工信息
    @Select("SELECT * FROM employee_table WHERE employee_id = #{employeeId}")
    Employee getEmployee(int employeeId);
    // 查询员工列表
    @Select("SELECT * FROM employee_table")
    List<Employee> getEmployeeList();
}
