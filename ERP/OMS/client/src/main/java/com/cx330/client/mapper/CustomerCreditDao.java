package com.cx330.client.mapper;


import com.cx330.entity.CustomerCredit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerCreditDao {
    // 添加客户信用信息
    int addCustomerCredit(CustomerCredit customerCredit);
    // 更新客户信用信息
    int updateCustomerCredit(CustomerCredit customerCredit);
    // 删除客户信用信息
    int deleteCustomerCredit(Integer customerId);
    // 根据id查询客户信用信息
    CustomerCredit getCustomerCreditById(Integer customerId);
    // 查询信用额度列表
    List<CustomerCredit> getCustomerCreditList();


}
