package com.cx330.client.service;

import com.cx330.entity.CustomerCredit;

import java.util.List;

public interface CustomerCreditService {
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
