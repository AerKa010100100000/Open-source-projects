package com.cx330.service.impl;

import com.cx330.constant.ConstantValue;
import com.cx330.entity.CompanyInfo;
import com.cx330.mapper.CompanyDetailedMapper;
import com.cx330.mapper.CompanyInfoMapper;
import com.cx330.service.AddressInfoService;
import com.cx330.service.CompanyInfoService;
import com.cx330.service.ContactInfoService;
import com.cx330.service.ServiceInfoService;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
public class CompanyInfoImpl implements CompanyInfoService {
    @Autowired
    private CompanyInfoMapper companyInfoMapper;
    @Autowired
    private AddressInfoService addressInfoService;
    @Autowired
    private ContactInfoService contactInfoService;
    @Autowired
    private ServiceInfoService serviceInfoService;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public CompanyInfo  selectOne(Integer companyId) {
        CompanyInfo companyInfo = companyInfoMapper.selectOne(companyId);
        return companyInfo;
    }

    @Override
    public List<CompanyInfo> selectAll() {
        List<CompanyInfo> companyInfos = companyInfoMapper.selectAll();
        return companyInfos;
    }

    @Override
    public int insert(CompanyInfo companyInfo) {
        int insert = companyInfoMapper.insert(companyInfo);
        return insert;
    }

    @Override
    public int update(CompanyInfo companyInfo) {
        int update = companyInfoMapper.update(companyInfo);
        return update;
    }

    @Override
    public int delete(Integer companyId) {
        int deleteAddress = addressInfoService.deleteAddress(companyId);
        int deleteContactInfo = contactInfoService.deleteContactInfo(companyId);
        int deleteServiceInfo = serviceInfoService.deleteServiceInfoByCompanyId(companyId);
        Result result = restTemplate.getForObject(ConstantValue.DEPARTMENT_URL + "departCompany/deleteByCompanyId?companyId=" + companyId, Result.class);
        if (result.getData().equals("删除失败"))
            if (deleteAddress == 0 || deleteContactInfo == 0 || deleteServiceInfo == 0)
                throw new RuntimeException("删除失败");
        return companyInfoMapper.delete(companyId);
    }


}
