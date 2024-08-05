package com.cx330.position.controller;

import com.cx330.entity.EmployeePositionMap;
import com.cx330.position.service.EmployeePositionService;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeePosition")
public class EmployeePositionController {
    @Autowired
    private EmployeePositionService employeePositionService;


    @PostMapping("/add")
    public Result<String> addMapping(EmployeePositionMap employeePositionMap) {
        int addMapping = employeePositionService.addMapping(employeePositionMap);
        if (addMapping > 0)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @PutMapping("/update")
    public Result<String> updateMapping(EmployeePositionMap employeePositionMap) {
        int updateMapping = employeePositionService.updateMapping(employeePositionMap);
        if (updateMapping > 0)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @DeleteMapping("/deleteMappingByEmployeeId")
    public Result<String> deleteMappingByEmployeeId(@RequestParam("employeeId") Integer employeeId) {
        int deleteMappingByEmployeeId = employeePositionService.deleteMappingByEmployeeId(employeeId);
        if (deleteMappingByEmployeeId > 0)
            return new Result<String>().ok();
        return new Result<String>().error();
    }


    @DeleteMapping("/deleteMappingByPositionId")
    public Result<String> deleteMappingByPositionId(@RequestParam("positionId") Integer positionId) {
        int deleteMappingByPositionId = employeePositionService.deleteMappingByPositionId(positionId);
        if (deleteMappingByPositionId > 0)
            return new Result<String>().ok();
        return new Result<String>().error();

    }

    @GetMapping("/getMappingByEmployeeId")
    public Result<List<EmployeePositionMap>> getMappingByEmployeeId(@RequestParam("employeeId") Integer employeeId) {
        List<EmployeePositionMap> mappingByEmployeeId = employeePositionService.getMappingByEmployeeId(employeeId);
        if (mappingByEmployeeId.isEmpty())
            return new Result<List<EmployeePositionMap>>().ok().data(mappingByEmployeeId);
        return new Result<List<EmployeePositionMap>>().error();
    }

    @GetMapping("/getMappingByPositionId")
    public Result<List<EmployeePositionMap>> getMappingByPositionId(@RequestParam("positionId") Integer positionId) {
        List<EmployeePositionMap> mappingByPositionId = employeePositionService.getMappingByPositionId(positionId);
        if (mappingByPositionId.isEmpty())
            return new Result<List<EmployeePositionMap>>().ok().data(mappingByPositionId);
        return new Result<List<EmployeePositionMap>>().error();
    }

    @GetMapping("/getAllMapping")
    public Result<List<EmployeePositionMap>> getAllMapping() {
        List<EmployeePositionMap> allMapping = employeePositionService.getAllMapping();
        if (allMapping.isEmpty())
            return new Result<List<EmployeePositionMap>>().ok().data(allMapping);
        return new Result<List<EmployeePositionMap>>().error();
    }

}
