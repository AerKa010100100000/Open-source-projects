package com.cx330.department.service.impl;

import com.cx330.constant.ConstantValue;
import com.cx330.department.mapper.PositionDepartmentMapper;
import com.cx330.department.service.DepartService;
import com.cx330.department.service.PositionDepartmentService;
import com.cx330.entity.Department;
import com.cx330.entity.PositionDepartmentMap;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PositionDepartmentImpl implements PositionDepartmentService {
    @Autowired
    private PositionDepartmentMapper positionDepartmentMapper;
    @Autowired
    private DepartService departService;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    @Transactional
    public int insert(PositionDepartmentMap positionDepartmentMap) {
        Department departById = departService.getDepartById(positionDepartmentMap.getDepartmentId());
        Result result = restTemplate.getForObject(ConstantValue.POSITION_URL + "position/getPositionById?positionId=" + positionDepartmentMap.getPositionId(), Result.class);
        if (departById == null || result.getData() == null)
            throw new RuntimeException("部门或职位不存在");
        return positionDepartmentMapper.insert(positionDepartmentMap);
    }

    @Override
    public int update(PositionDepartmentMap positionDepartmentMap) {
        Department departById = departService.getDepartById(positionDepartmentMap.getDepartmentId());
        Result result = restTemplate.getForObject(ConstantValue.POSITION_URL + "position/getPositionById?positionId=" + positionDepartmentMap.getPositionId(), Result.class);
        if (departById == null || result.getData() == null)
            throw new RuntimeException("部门或职位不存在");
        return positionDepartmentMapper.update(positionDepartmentMap);
    }

    @Override
    public int deleteByMapId(Integer mapId) {
        return positionDepartmentMapper.deleteByMapId(mapId);
    }

    @Override
    public int deleteByDeptId(Integer deptId) {
        return positionDepartmentMapper.deleteByDeptId(deptId);
    }

    @Override
    public int deleteByPosId(Integer posId) {
        return positionDepartmentMapper.deleteByPosId(posId);
    }

    @Override
    public PositionDepartmentMap selectByMapId(Integer mapId) {
        return positionDepartmentMapper.selectByMapId(mapId);
    }

    @Override
    public List<PositionDepartmentMap> selectByDeptId(Integer deptId) {
        return positionDepartmentMapper.selectByDeptId(deptId);
    }

    @Override
    public List<PositionDepartmentMap> selectByPosId(Integer posId) {
        return positionDepartmentMapper.selectByPosId(posId);
    }

    @Override
    public List<PositionDepartmentMap> selectAll() {
        return positionDepartmentMapper.selectAll();
    }
}
