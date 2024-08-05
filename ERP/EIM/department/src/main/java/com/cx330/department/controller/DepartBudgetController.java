package com.cx330.department.controller;


import com.cx330.department.mapper.DepartBudgetMapper;
import com.cx330.department.service.DepartBudgetService;
import com.cx330.entity.DepartmentBudget;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departBudget")
public class DepartBudgetController {
    @Autowired
    private DepartBudgetService departBudgetService;

    @PostMapping("/add")
    public Result<String> addDepartBudget(DepartmentBudget departmentBudget) {
        int addDepartBudget = departBudgetService.addDepartBudget(departmentBudget);
        if (addDepartBudget > 0)
            return new Result<String>().ok().message("添加成功");
        return new Result<String>().error().message("添加失败");
    }

    @PutMapping("/update")
    public Result<String> updateDepartBudget(DepartmentBudget departmentBudget) {
        int updateDepartBudget = departBudgetService.updateDepartBudget(departmentBudget);
        if (updateDepartBudget > 0)
            return new Result<String>().ok().message("更新成功");
        return new Result<String>().error().message("更新失败");
    }


    @DeleteMapping("/delete")
    public Result<String> deleteDepartBudget(@RequestParam("budgetId") Integer budgetId) {
        int deleteDepartBudget = departBudgetService.deleteDepartBudget(budgetId);
        if (deleteDepartBudget > 0)
            return new Result<String>().ok().message("删除成功");
        return new Result<String>().error().message("删除失败");
    }

    @DeleteMapping("getDepartBudgetByDeptId")
    public Result<DepartmentBudget> getDepartBudgetByDeptId(@RequestParam("deptId") Integer deptId) {
        DepartmentBudget departmentBudget = departBudgetService.getDepartBudgetByDeptId(deptId);
        if (departmentBudget != null)
            return new Result<DepartmentBudget>().ok().data(departmentBudget);
        return new Result<DepartmentBudget>().error().message("查询失败");
    }


    public Result<DepartmentBudget> getDepartBudgetByDeptName(@RequestParam("departmentId") Integer departmentId) {
        DepartmentBudget departBudgetByDeptName = departBudgetService.getDepartBudgetByDeptName(departmentId);
        if (departBudgetByDeptName != null)
            return new Result<DepartmentBudget>().ok().data(departBudgetByDeptName);
        return new Result<DepartmentBudget>().error().message("查询失败");
    }


    public Result<List<DepartmentBudget>> getAllDepartBudget() {
        List<DepartmentBudget> allDepartBudget = departBudgetService.getAllDepartBudget();
        if (allDepartBudget != null)
            return new Result<List<DepartmentBudget>>().ok().data(allDepartBudget);
        return new Result<List<DepartmentBudget>>().error().message("查询失败");
    }
}
