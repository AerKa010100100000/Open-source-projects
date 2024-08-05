package com.cx330.client.mapper;

import com.cx330.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientDao {
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
