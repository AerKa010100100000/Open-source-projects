package com.cx330.position.service.impl;


import com.cx330.constant.ConstantValue;
import com.cx330.entity.Position;
import com.cx330.position.mapper.PositionMapper;
import com.cx330.position.service.EmployeePositionService;
import com.cx330.position.service.PositionService;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
public class PositionImpl implements PositionService {
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private EmployeePositionService employeePositionService;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public int insertPosition(Position position) {
        return positionMapper.insertPosition(position);
    }
    @Override
    public int deletePosition(Integer positionId) {
        int deleteMappingByPositionId = employeePositionService.deleteMappingByPositionId(positionId);
        Result result = restTemplate.getForObject(ConstantValue.DEPARTMENT_URL + "positionDepartment/selectByPosId?positionId=" + positionId, Result.class);
        if (result.getData() == null ||  deleteMappingByPositionId == 0)
            throw new RuntimeException("删除岗位与部门关系失败或删除岗位与员工关系失败");
        return positionMapper.deletePosition(positionId);
    }

    @Override
    public int updatePosition(Position position) {
        return positionMapper.updatePosition(position);
    }

    @Override
    public List<Position> getPositionList() {
        return positionMapper.getPositionList();
    }

    @Override
    public Position getPositionById(Integer positionId) {
        return positionMapper.getPositionById(positionId);
    }
}
