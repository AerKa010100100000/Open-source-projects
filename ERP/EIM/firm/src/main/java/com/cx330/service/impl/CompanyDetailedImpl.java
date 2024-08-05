package com.cx330.service.impl;

import com.cx330.entity.CompanyInfo;
import com.cx330.mapper.CompanyDetailedMapper;
import com.cx330.service.CompanyDetailedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyDetailedImpl implements CompanyDetailedService {

    @Autowired
    private CompanyDetailedMapper companyDetailedMapper;


    // 重新定义一个类实现公司和地址的关系
    @Override
    public List<CompanyInfo> selectCompanyAdders() {
        List<CompanyInfo> selectCompanyAdders = companyDetailedMapper.selectAll();
        return selectCompanyAdders;
    }
}
