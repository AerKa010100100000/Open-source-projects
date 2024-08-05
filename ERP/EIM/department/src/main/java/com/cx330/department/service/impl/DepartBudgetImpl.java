package com.cx330.department.service.impl;

import com.cx330.department.mapper.DepartBudgetMapper;
import com.cx330.department.service.DepartBudgetService;
import com.cx330.entity.DepartmentBudget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartBudgetImpl implements DepartBudgetService {
    @Autowired
    private DepartBudgetMapper departBudgetMapper;
    @Override
    public int addDepartBudget(DepartmentBudget departmentBudget) {

        return departBudgetMapper.addDepartBudget(departmentBudget);
    }

    @Override
    public int updateDepartBudget(DepartmentBudget departmentBudget) {
        return departBudgetMapper.updateDepartBudget(departmentBudget);
    }

    @Override
    public int deleteDepartBudget(Integer budgetId) {
        return departBudgetMapper.deleteDepartBudget(budgetId);
    }

    @Override
    public DepartmentBudget getDepartBudgetByDeptId(Integer departmentId) {
        return departBudgetMapper.getDepartBudgetByDeptId(departmentId);
    }

    @Override
    public DepartmentBudget getDepartBudgetByDeptName(Integer budgetId) {
        return departBudgetMapper.getDepartBudgetByDeptName(budgetId);
    }

    @Override
    public List<DepartmentBudget> getAllDepartBudget() {
        return departBudgetMapper.getAllDepartBudget();
    }
}
