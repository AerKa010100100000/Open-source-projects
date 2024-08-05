package com.cx330.department.service.impl;

import com.cx330.department.mapper.DepartMapper;
import com.cx330.department.service.DepartService;
import com.cx330.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartImpl implements DepartService {
    @Autowired
    private DepartMapper departMapper;
    @Override
    public int addDepart(Department department) {
        return departMapper.addDepart(department);
    }

    @Override
    public int updateDepart(Department department) {
        return departMapper.updateDepart(department);
    }

    @Override
    public int deleteDepart(int departmentId) {
        return departMapper.deleteDepart(departmentId);
    }

    @Override
    public Department getDepartById(int departmentId) {
        return departMapper.getDepartById(departmentId);
    }

    @Override
    public Department getDepartByName(String departmentName) {
        return departMapper.getDepartByName(departmentName);
    }

    @Override
    public List<Department> getAllDepart() {
        return departMapper.getAllDepart();
    }
}
