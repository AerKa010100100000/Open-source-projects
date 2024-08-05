package com.cx330.client.mapper;


import com.cx330.entity.CustomerTrade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerTradeDao {
    // 添加客户交易记录
    int addCustomerTrade(CustomerTrade customerTrade);
    // 修改客户交易记录
    int updateCustomerTrade(CustomerTrade customerTrade);
    // 删除客户交易记录
    int deleteCustomerTrade(Integer tradeId);
    // 根据交易id查询客户交易记录
    CustomerTrade getCustomerTradeById(Integer tradeId);
}
