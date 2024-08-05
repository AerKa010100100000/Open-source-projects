package com.cx330.client.service.impl;

import com.cx330.client.mapper.CustomerTradeDao;
import com.cx330.client.service.CustomerTradeService;
import com.cx330.entity.CustomerTrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerTradeImpl implements CustomerTradeService {
    @Autowired
    private CustomerTradeDao customerTradeDao;
    @Override
    public int addCustomerTrade(CustomerTrade customerTrade) {
        return customerTradeDao.addCustomerTrade(customerTrade);
    }

    @Override
    public int updateCustomerTrade(CustomerTrade customerTrade) {
        return customerTradeDao.updateCustomerTrade(customerTrade);
    }

    @Override
    public int deleteCustomerTrade(Integer tradeId) {
        return customerTradeDao.deleteCustomerTrade(tradeId);
    }

    @Override
    public CustomerTrade getCustomerTradeById(Integer tradeId) {
        return customerTradeDao.getCustomerTradeById(tradeId);
    }
}
