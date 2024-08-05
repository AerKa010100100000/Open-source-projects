package com.cx330.position.service.impl;


import com.cx330.entity.Employee;
import com.cx330.entity.EmployeePositionMap;
import com.cx330.entity.Position;
import com.cx330.position.mapper.EmployeePositionMapper;
import com.cx330.position.service.EmployeePositionService;
import com.cx330.position.service.EmployeeService;
import com.cx330.position.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeePositionImpl implements EmployeePositionService {
    @Autowired
    private EmployeePositionMapper employeePositionMapper;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PositionService positionService;
    @Override
    public int addMapping(EmployeePositionMap employeePositionMap) {
        Employee employee = employeeService.getEmployee(employeePositionMap.getEmployeeId());
        Position positionById = positionService.getPositionById(employeePositionMap.getPositionId());
        if (employee == null || positionById == null)
            throw new RuntimeException("员工表或职位表中不存在该员工id或职位id");
        return employeePositionMapper.addMapping(employeePositionMap);
    }

    @Override
    public int updateMapping(EmployeePositionMap employeePositionMap) {
        Employee employee = employeeService.getEmployee(employeePositionMap.getEmployeeId());
        Position positionById = positionService.getPositionById(employeePositionMap.getPositionId());
        if (employee == null || positionById == null)
            throw new RuntimeException("员工表或职位表中不存在该员工id或职位id");
        return employeePositionMapper.updateMapping(employeePositionMap);
    }

    @Override
    public int deleteMappingById(int mapId) {
        return employeePositionMapper.deleteMappingById(mapId);
    }

    @Override
    public int deleteMappingByEmployeeId(int employeeId) {
        return 0;
    }

    @Override
    public int deleteMappingByPositionId(int positionId) {
        return 0;
    }

    @Override
    public List<EmployeePositionMap> getMappingByEmployeeId(int employeeId) {
        return employeePositionMapper.getMappingByEmployeeId(employeeId);
    }

    @Override
    public List<EmployeePositionMap> getMappingByPositionId(int positionId) {
        return employeePositionMapper.getMappingByPositionId(positionId);
    }

    @Override
    public List<EmployeePositionMap> getAllMapping() {
        return employeePositionMapper.getAllMapping();
    }
}
