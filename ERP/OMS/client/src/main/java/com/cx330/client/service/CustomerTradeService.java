package com.cx330.client.service;

import com.cx330.entity.CustomerTrade;

public interface CustomerTradeService {
    // 添加客户交易记录
    int addCustomerTrade(CustomerTrade customerTrade);
    // 修改客户交易记录
    int updateCustomerTrade(CustomerTrade customerTrade);
    // 删除客户交易记录
    int deleteCustomerTrade(Integer tradeId);
    // 根据交易id查询客户交易记录
    CustomerTrade getCustomerTradeById(Integer tradeId);
}
