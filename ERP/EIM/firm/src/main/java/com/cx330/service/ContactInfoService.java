package com.cx330.service;

import com.cx330.entity.ContactInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContactInfoService {
    int addContactInfo(ContactInfo contactInfo);

    int updateContactInfo(ContactInfo contactInfo);

    List<ContactInfo> queryContactInfo(String contactType);
    int deleteContactInfo(Integer companyId);
}
