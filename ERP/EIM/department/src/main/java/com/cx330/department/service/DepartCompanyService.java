package com.cx330.department.service;

import com.cx330.entity.DepartmentCompanyMap;

import java.util.List;

public interface DepartCompanyService {
    // 添加公司与部门的关联关系
    int addDepartCompany(DepartmentCompanyMap departmentCompanyMap);
    // 根据公司ID删除公司与部门的关联关系
    int deleteDepartCompanyByCompanyId(Integer companyId);
    // 根据部门ID删除公司与部门的关联关系
    int deleteDepartCompanyByDepartmentId(Integer departmentId);
    // 根据公司ID查询部门列表
    List<DepartmentCompanyMap> getDepartmentsByCompanyId(Integer companyId);
    // 更新公司与部门的关联关系
    int updateDepartCompany(DepartmentCompanyMap departmentCompanyMap);
}
