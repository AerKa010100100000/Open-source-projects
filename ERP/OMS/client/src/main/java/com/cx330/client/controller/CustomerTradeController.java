package com.cx330.client.controller;

import com.cx330.client.mapper.CustomerTradeDao;
import com.cx330.client.service.CustomerTradeService;
import com.cx330.entity.CustomerTrade;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customerTrade")
public class CustomerTradeController {
    @Autowired
    private CustomerTradeService customerTradeService;
    @PostMapping("/add")
    public Result<String> addCustomerTrade(CustomerTrade customerTrade) {
        int addCustomerTrade = customerTradeService.addCustomerTrade(customerTrade);
        if (addCustomerTrade == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @PutMapping("/update")
    public Result<String> updateCustomerTrade(CustomerTrade customerTrade) {
        int updateCustomerTrade = customerTradeService.updateCustomerTrade(customerTrade);
        if (updateCustomerTrade == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @DeleteMapping("/delete")
    public Result<String> deleteCustomerTrade(Integer tradeId) {
        int deleteCustomerTrade = customerTradeService.deleteCustomerTrade(tradeId);
        if (deleteCustomerTrade == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @GetMapping("/getTradeById")
    public Result<CustomerTrade> getCustomerTradeById(Integer tradeId) {
        CustomerTrade customerTradeById = customerTradeService.getCustomerTradeById(tradeId);
        if (customerTradeById!= null)
            return new Result<CustomerTrade>().ok().data(customerTradeById);
        return new Result<CustomerTrade>().error();
    }

}
