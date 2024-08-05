package com.cx330.mapper;

import com.cx330.entity.CompanyInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface CompanyInfoMapper {

    // 根据公司ID查询公司信息
    @Select(" SELECT * FROM `company_info` WHERE company_id = #{companyId} ")
    CompanyInfo selectOne(int companyId);
    // 查询所有公司信息
    @Select( " SELECT * FROM `company_info` ")
    List<CompanyInfo> selectAll();
    // 添加公司信息
    @Insert(" INSERT INTO `company_info` (name, legal_name, tax_registration_number, type, industry_type, website, logo)" +
            " VALUES (#{name}, #{legalName}, #{taxRegistrationNumber}, #{type}, #{industryType}, #{website}, #{logo}) ")
    int insert(CompanyInfo companyInfo);
    // 修改公司信息
    @Update(" UPDATE `company_info` SET " +
            "name = #{name}, legal_name = #{legalName}, tax_registration_number = #{taxRegistrationNumber}," +
            "type = #{type}, industry_type = #{industryType}, website = #{website}, logo = #{logo} " +
            "WHERE COMPANY_ID = #{companyId} "
    )
    int update(CompanyInfo companyInfo);
    // 删除公司信息
    @Update(" DELETE FROM `company_info` WHERE COMPANY_ID = #{companyId} ")
    int delete(Integer companyId);
}
