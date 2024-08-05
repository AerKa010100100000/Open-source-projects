package com.cx330.client.service;

import com.cx330.entity.Customer;

import java.util.List;

public interface ClientService {
    // 添加客户基本信息
    int addClient(Customer customer );
    // 修改客户基本信息
    int updateClient(Customer customer );
    // 删除客户基本信息
    int deleteClient(Integer customerId );
    // 根据id查询客户基本信息
    Customer getClientById(Integer customerId );
    // 根据客户名称查询客户信息
    List<Customer> getClientByName(String customerName );
    // 查询客户列表
    List<Customer> getClientList();
}
