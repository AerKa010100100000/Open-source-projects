package com.cx330.department.controller;


import com.cx330.department.mapper.DepartCompanyMapper;
import com.cx330.department.service.DepartCompanyService;
import com.cx330.entity.DepartmentCompanyMap;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departCompany")
public class DepartCompanyController {
    @Autowired
    private DepartCompanyService departCompanyService;

    @PostMapping("/add")
    public Result<String> addDepartCompany(DepartmentCompanyMap departmentCompanyMap) {
        int addDepartCompany = departCompanyService.addDepartCompany(departmentCompanyMap);
        if (addDepartCompany > 0)
            return new Result<String>().ok().data("添加成功");
        return new Result<String>().error().data("添加失败");
    }

    @DeleteMapping("/deleteByCompanyId")
    public Result<String> deleteDepartCompanyByCompanyId(@RequestParam("companyId") Integer companyId) {
        int deleteDepartCompanyByCompanyId = departCompanyService.deleteDepartCompanyByCompanyId(companyId);
        if (deleteDepartCompanyByCompanyId > 0)
            return new Result<String>().ok().data("删除成功");
        return new Result<String>().error().data("删除失败");
    }


    @DeleteMapping("/deleteByDepartmentId")
    public Result<String> deleteDepartCompanyByDepartmentId(Integer departmentId) {
        int deleteDepartCompanyByDepartmentId = departCompanyService.deleteDepartCompanyByDepartmentId(departmentId);
        if (deleteDepartCompanyByDepartmentId > 0)
            return new Result<String>().ok().data("删除成功");
        return new Result<String>().error().data("删除失败");
    }

    @GetMapping("/getByCompanyId")
    public Result<List<DepartmentCompanyMap>> getDepartmentsByCompanyId(@RequestParam("companyId") Integer companyId) {
        List<DepartmentCompanyMap> departmentsByCompanyId = departCompanyService.getDepartmentsByCompanyId(companyId);
        if (departmentsByCompanyId!= null)
            return new Result<List<DepartmentCompanyMap>>().ok().data(departmentsByCompanyId);
        return new Result<List<DepartmentCompanyMap>>().error();
    }

    @PutMapping("/update")
    public Result<String> updateDepartCompany(DepartmentCompanyMap departmentCompanyMap) {
        int updateDepartCompany = departCompanyService.updateDepartCompany(departmentCompanyMap);
        if (updateDepartCompany > 0)
            return new Result<String>().ok().data("更新成功");
        return new Result<String>().error().data("更新失败");
    }
}
