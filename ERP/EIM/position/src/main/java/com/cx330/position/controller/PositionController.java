package com.cx330.position.controller;


import com.cx330.entity.Position;
import com.cx330.position.service.PositionService;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionService positionService;


    @PostMapping("/insert")
    public Result<String> insertPosition(Position position) {
        int insertPosition = positionService.insertPosition(position);
        if (insertPosition > 0)
            return new Result<String>().ok();
        return new Result<String>().error().data("添加失败");
    }

    @DeleteMapping("/delete")
    public Result<String> deletePosition(Integer positionId) {
        int deletePosition = positionService.deletePosition(positionId);
        if (deletePosition > 0)
            return new Result<String>().ok();
        return new Result<String>().error().data("删除失败");
    }

    @PutMapping("/update")
    public Result<String> updatePosition(Position position) {
        int updatePosition = positionService.updatePosition(position);
        if (updatePosition > 0)
            return new Result<String>().ok();
        return new Result<String>().error().data("更新失败");
    }


    @GetMapping("/getPositionList")
    public Result<List<Position>> getPositionList() {
        List<Position> positionList = positionService.getPositionList();
        if (positionList!= null && !positionList.isEmpty())
            return new Result<List<Position>>().ok().data(positionList);
        return new Result<List<Position>>().error();
    }


    @GetMapping("/getPositionById")
    public Result<Position> getPositionById(@RequestParam("positionId") Integer positionId) {
        Position position = positionService.getPositionById(positionId);
        if (position!= null)
            return new Result<Position>().ok().data(position);
        return new Result<Position>().error();
    }
}
