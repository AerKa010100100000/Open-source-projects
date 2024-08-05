package com.cx330.service.impl;

import com.cx330.entity.ContactInfo;
import com.cx330.mapper.ContactInfoMapper;
import com.cx330.service.ContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContactInfoImpl implements ContactInfoService {
    @Autowired
    private ContactInfoMapper contactInfoMapper;

    @Override
    public int addContactInfo(ContactInfo contactInfo) {

        return contactInfoMapper.addContactInfo(contactInfo);
    }

    @Override
    public int updateContactInfo(ContactInfo contactInfo) {
        return contactInfoMapper.updateContactInfo(contactInfo);
    }

    @Override
    public List<ContactInfo> queryContactInfo(String contactType) {
        return contactInfoMapper.queryContactInfo(contactType);
    }

    @Override
    public int deleteContactInfo(Integer companyId) {
        return contactInfoMapper.deleteContactInfo(companyId);
    }
}
