package com.cx330.mapper;


import com.cx330.entity.ContactInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ContactInfoMapper {

    // 添加公司联系
    @Insert("INSERT INTO contact_info (company_id, contact_type, phone_number, fax_number, email) VALUES (#{companyId}, #{contactType}, #{phoneNumber}, #{faxNumber}, #{email})")
    int addContactInfo( ContactInfo contactInfo);
    // 更新公司联系
    @Update("UPDATE contact_info SET contact_type = #{contactType}, phone_number = #{phoneNumber}, fax_number = #{faxNumber}, email = #{email} WHERE company_id = #{companyId} ")
    int updateContactInfo( ContactInfo contactInfo);
    // 根据公司类型查询公司联系
    List<ContactInfo> queryContactInfo(@Param("contactType")String contactType);
    // 删除公司联系
    @Delete("DELETE FROM contact_info WHERE company_id = #{companyId}")
    int deleteContactInfo( @Param("companyId")Integer companyId);
}
