package com.cx330.department.mapper;

import com.cx330.entity.DepartmentCompanyMap;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartCompanyMapper {
    // 添加公司与部门的关联关系
    @Insert("INSERT INTO department_company_map (department_id, company_id) VALUES (#{departmentId}, #{companyId})")
    int addDepartCompany(DepartmentCompanyMap departmentCompanyMap);
    // 根据公司ID删除公司与部门的关联关系
    @Delete("DELETE FROM department_company_map WHERE company_id = #{companyId}")
    int deleteDepartCompanyByCompanyId(Integer companyId);
    // 根据部门ID删除公司与部门的关联关系
    @Delete("DELETE FROM department_company_map WHERE department_id = #{departmentId}")
    int deleteDepartCompanyByDepartmentId(Integer departmentId);
    // 根据公司ID查询部门列表
    @Select("SELECT department_id FROM department_company_map WHERE company_id = #{companyId}")
    List<DepartmentCompanyMap> getDepartmentsByCompanyId(Integer companyId);
    // 更新公司与部门的关联关系
    @Insert("UPDATE department_company_map SET department_id = #{departmentId}, company_id = #{companyId} WHERE map_id = #{mapId}")
    int updateDepartCompany(DepartmentCompanyMap departmentCompanyMap);

}
