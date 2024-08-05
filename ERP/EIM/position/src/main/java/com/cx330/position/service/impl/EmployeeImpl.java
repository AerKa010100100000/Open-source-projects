package com.cx330.position.service.impl;

import com.cx330.entity.Employee;
import com.cx330.position.mapper.EmployeeMapper;
import com.cx330.position.service.EmployeePositionService;
import com.cx330.position.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeePositionService employeePositionService;
    @Override
    public int addEmployee(Employee employee) {

        return employeeMapper.addEmployee(employee);
    }

    @Override
    public int deleteEmployee(int employeeId) {
        int deleteMappingByEmployeeId = employeePositionService.deleteMappingByEmployeeId(employeeId);
        if (deleteMappingByEmployeeId == 0)
            throw new RuntimeException("EmployeeService:employeePositionService调用映射关系删除失败");
        return employeeMapper.deleteEmployee(employeeId);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    public Employee getEmployee(int employeeId) {
        return employeeMapper.getEmployee(employeeId);
    }

    @Override
    public List<Employee> getEmployeeList() {
        return employeeMapper.getEmployeeList();
    }
}
