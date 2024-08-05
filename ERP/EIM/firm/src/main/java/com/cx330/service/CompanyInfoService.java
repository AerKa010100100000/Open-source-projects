package com.cx330.service;

import com.cx330.entity.CompanyInfo;

import java.util.List;

public interface CompanyInfoService {
    // 根据公司ID查询公司信息
    public CompanyInfo selectOne(Integer companyId);
    // 查询所有公司信息
    public List<CompanyInfo> selectAll();
    // 添加公司信息
    public int insert(CompanyInfo companyInfo);
    // 修改公司信息

    public int update(CompanyInfo companyInfo);
    // 删除公司信息
    public int delete(Integer companyId);


}
