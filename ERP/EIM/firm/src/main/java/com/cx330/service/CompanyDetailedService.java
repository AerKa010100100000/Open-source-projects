package com.cx330.service;

import com.cx330.entity.CompanyInfo;

import java.util.List;

public interface CompanyDetailedService {
    // 查询公司地址及信息
    public List<CompanyInfo> selectCompanyAdders();

}
