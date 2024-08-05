package com.cx330.department.service.impl;

import com.cx330.constant.ConstantValue;
import com.cx330.department.mapper.DepartCompanyMapper;
import com.cx330.department.service.DepartCompanyService;
import com.cx330.entity.Department;
import com.cx330.entity.DepartmentCompanyMap;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@Transactional
public class DepartCompanyImpl implements DepartCompanyService {
    @Autowired
    private DepartCompanyMapper departCompanyMapper;
    @Autowired
    private DepartImpl departImpl;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public int addDepartCompany(DepartmentCompanyMap departmentCompanyMap) {
        Department departById = departImpl.getDepartById(departmentCompanyMap.getDepartmentId());
        Result result = restTemplate.getForObject(ConstantValue.FIRM_URL + "firm/selectOne?companyId=" + departmentCompanyMap.getCompanyId(), Result.class);
        if (result.getData() != null && departById != null)
            departCompanyMapper.addDepartCompany(departmentCompanyMap);
        return 0;
    }

    @Override
    public int deleteDepartCompanyByCompanyId(Integer companyId) {
        return departCompanyMapper.deleteDepartCompanyByCompanyId(companyId);
    }

    @Override
    public int deleteDepartCompanyByDepartmentId(Integer departmentId) {
        return departCompanyMapper.deleteDepartCompanyByDepartmentId(departmentId);
    }

    @Override
    public List<DepartmentCompanyMap> getDepartmentsByCompanyId(Integer companyId) {
        return departCompanyMapper.getDepartmentsByCompanyId(companyId);
    }

    @Override
    public int updateDepartCompany(DepartmentCompanyMap departmentCompanyMap) {
        Department departById = departImpl.getDepartById(departmentCompanyMap.getDepartmentId());
        Result result = restTemplate.getForObject(ConstantValue.FIRM_URL + "firm/selectOne?companyId=" + departmentCompanyMap.getCompanyId(), Result.class);
        if (result.getData() != null && departById != null)
            departCompanyMapper.updateDepartCompany(departmentCompanyMap);
        return 0;
    }
}
