package com.cx330.client.service.impl;

import com.cx330.client.mapper.CustomerCreditDao;
import com.cx330.client.service.CustomerCreditService;
import com.cx330.entity.CustomerCredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerCreditImpl implements CustomerCreditService {
    @Autowired
    private CustomerCreditDao customerCreditDao;
    @Override
    public int addCustomerCredit(CustomerCredit customerCredit) {
        return customerCreditDao.addCustomerCredit(customerCredit);
    }

    @Override
    public int updateCustomerCredit(CustomerCredit customerCredit) {
        return customerCreditDao.updateCustomerCredit(customerCredit);
    }

    @Override
    public int deleteCustomerCredit(Integer customerId) {
        return customerCreditDao.deleteCustomerCredit(customerId);
    }

    @Override
    public CustomerCredit getCustomerCreditById(Integer customerId) {
        return customerCreditDao.getCustomerCreditById(customerId);
    }

    @Override
    public List<CustomerCredit> getCustomerCreditList() {
        return customerCreditDao.getCustomerCreditList();
    }
}
