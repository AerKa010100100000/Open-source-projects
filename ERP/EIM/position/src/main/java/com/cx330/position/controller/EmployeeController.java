package com.cx330.position.controller;


import com.cx330.entity.Employee;
import com.cx330.position.service.EmployeeService;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/add")
    public Result<String> addEmployee(Employee employee) {
        int addEmployee = employeeService.addEmployee(employee);
        if (addEmployee > 0)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @DeleteMapping("/delete")
    public Result<String> deleteEmployee(int employeeId) {
        int deleteEmployee = employeeService.deleteEmployee(employeeId);
        if (deleteEmployee > 0)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @PutMapping("/update")
    public Result<String> updateEmployee(Employee employee) {
        int updateEmployee = employeeService.updateEmployee(employee);
        if (updateEmployee > 0)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @GetMapping("/getEmployee")
    public Result<Employee> getEmployee(@RequestParam("employeeId") int employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        if (employee!= null)
            return new Result<Employee>().ok().data(employee);
        return new Result<Employee>().error();
    }

    @GetMapping("/getEmployeeList")
    public Result<List<Employee>> getEmployeeList() {
        List<Employee> employeeList = employeeService.getEmployeeList();
        if (employeeList!= null)
            return new Result<List<Employee>>().ok().data(employeeList);
        return new Result<List<Employee>>().error();
    }

}
