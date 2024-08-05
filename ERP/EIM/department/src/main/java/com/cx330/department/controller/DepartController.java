package com.cx330.department.controller;


import com.cx330.department.service.DepartService;
import com.cx330.entity.Department;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depart")
public class DepartController {
    @Autowired
    private DepartService departService;

    @PostMapping("/add")
    public Result<String> addDepart(Department department) {
        int addDepart = departService.addDepart(department);
        if (addDepart > 0)
            return new Result<String>().ok().data("添加成功");
        return new Result<String>().error().data("添加失败");
    }

    @PutMapping("/update")
    public Result<String> updateDepart(Department department) {
        int updateDepart = departService.updateDepart(department);
        if (updateDepart > 0)
            return new Result<String>().ok().data("更新成功");
        return new Result<String>().error().data("更新失败");
    }

    @DeleteMapping("/delete")
    public Result<String> deleteDepart(@RequestParam("departmentId") int departmentId) {
        int deleteDepart = departService.deleteDepart(departmentId);
        if (deleteDepart > 0)
            new Result<String>().ok().data("删除成功");
        return new Result<String>().error().data("删除失败");
    }

    @GetMapping("/getDepartById")
    public Result<Department> getDepartById(@RequestParam("departmentId") int departmentId) {
        Department department = departService.getDepartById(departmentId);
        if (department!= null)
            return new Result<Department>().ok().data(department);
        return new Result<Department>().error();
    }

    @GetMapping("/getDepartByName")
    public Result<Department> getDepartByName(@RequestParam("departmentName") String departmentName) {
        Department depart = departService.getDepartByName(departmentName);
        if (depart!= null)
            return new Result<Department>().ok().data(depart);
        return new Result<Department>().error();
    }

    @GetMapping("/getAllDepart")
    public Result<List<Department>> getAllDepart() {
        List<Department> allDepart = departService.getAllDepart();
        if (allDepart!= null)
            return new Result<List<Department>>().ok().data(allDepart);
        return new Result<List<Department>>().error();
    }
}
