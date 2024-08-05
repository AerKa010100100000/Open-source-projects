package com.cx330.department.controller;


import com.cx330.department.service.PositionDepartmentService;
import com.cx330.entity.PositionDepartmentMap;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/positionDepartment")
public class PositionDepartmentController {
    @Autowired
    private PositionDepartmentService positionDepartmentService;
    // 添加映射
    public Result<String> insert(PositionDepartmentMap positionDepartmentMap){
        int insert = positionDepartmentService.insert(positionDepartmentMap);
        if(insert == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }
    // 修改映射
    public Result<String> update(PositionDepartmentMap positionDepartmentMap){
        int update = positionDepartmentService.update(positionDepartmentMap);
        if(update == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }
    // 根据映射id删除映射
    public Result<String> deleteByMapId(@RequestParam("mapId") Integer mapId){
        int deleteByMapId = positionDepartmentService.deleteByMapId(mapId);
        if(deleteByMapId == 1)
            return new Result<String>().ok().data("删除成功");
        return new Result<String>().error().data("删除失败");
    }
    // 根据部门id删除映射
    public Result<String> deleteByDeptId(@RequestParam("departmentId") Integer departmentId){
        int deleteByDeptId = positionDepartmentService.deleteByDeptId(departmentId);
        if(deleteByDeptId == 1)
            return new Result<String>().ok().data("删除成功");
        return new Result<String>().error().data("删除失败");
    }
    // 根据职位id删除映射
    public Result<String> deleteByPosId(@RequestParam("positionId") Integer positionId){
        int deleteByPosId = positionDepartmentService.deleteByPosId(positionId);
        if(deleteByPosId == 1)
            return new Result<String>().ok().data("删除成功");
        return new Result<String>().error().data("删除失败");
    }
    // 根据映射id查询映射
    public Result<PositionDepartmentMap> selectByMapId(@RequestParam("mapId") Integer mapId){
        PositionDepartmentMap positionDepartmentMap = positionDepartmentService.selectByMapId(mapId);
        if(positionDepartmentMap != null)
            return new Result<PositionDepartmentMap>().ok().data(positionDepartmentMap);
        return new Result<PositionDepartmentMap>().error();
    }
    // 根据部门id查询映射
    public Result<List<PositionDepartmentMap>> selectByDeptId(@RequestParam("departmentId") Integer departmentId){
        List<PositionDepartmentMap> positionDepartmentMaps = positionDepartmentService.selectByDeptId(departmentId);
        if(positionDepartmentMaps != null)
            return new Result<List<PositionDepartmentMap>>().ok().data(positionDepartmentMaps);
        return new Result<List<PositionDepartmentMap>>().error();
    }
    // 根据职位id查询映射
    public Result<List<PositionDepartmentMap>> selectByPosId(@RequestParam("positionId") Integer positionId){
        List<PositionDepartmentMap> positionDepartmentMaps = positionDepartmentService.selectByPosId(positionId);
        if(positionDepartmentMaps != null)
            return new Result<List<PositionDepartmentMap>>().ok().data(positionDepartmentMaps);
        return new Result<List<PositionDepartmentMap>>().error();
    }
    // 查询所有映射
    public Result<List<PositionDepartmentMap>> selectAll(){
        List<PositionDepartmentMap> positionDepartmentMaps = positionDepartmentService.selectAll();
        if(positionDepartmentMaps != null)
            return new Result<List<PositionDepartmentMap>>().ok().data(positionDepartmentMaps);
        return new Result<List<PositionDepartmentMap>>().error();
    }
}
