package com.cx330.client.controller;


import com.cx330.client.service.CustomerCreditService;
import com.cx330.entity.CustomerCredit;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/credit")
public class CustomerCreditController {

    @Autowired
    private CustomerCreditService customerCreditService;

    @PostMapping("/add")
    public Result<String> addCustomerCredit(CustomerCredit customerCredit) {
        int addCustomerCredit = customerCreditService.addCustomerCredit(customerCredit);
        if (addCustomerCredit == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @PutMapping("/update")
    public Result<String> updateCustomerCredit(CustomerCredit customerCredit) {
        int updateCustomerCredit = customerCreditService.updateCustomerCredit(customerCredit);
        if (updateCustomerCredit == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @DeleteMapping("/delete")
    public Result<String> deleteCustomerCredit(@RequestParam("customerId") Integer customerId) {
        int deleteCustomerCredit = customerCreditService.deleteCustomerCredit(customerId);
        if (deleteCustomerCredit == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }


    @GetMapping("/getCustomerCreditById")
    public Result<CustomerCredit> getCustomerCreditById(@RequestParam("customerId") Integer customerId) {
        CustomerCredit customerCreditById = customerCreditService.getCustomerCreditById(customerId);
        if (customerCreditById!= null)
            return new Result<CustomerCredit>().ok().data(customerCreditById);
        return new Result<CustomerCredit>().error();
    }


    @GetMapping("/getCustomerCreditList")
    public Result<List<CustomerCredit>> getCustomerCreditList() {
        List<CustomerCredit> customerCreditList = customerCreditService.getCustomerCreditList();
        if (customerCreditList!= null)
            return new Result<List<CustomerCredit>>().ok().data(customerCreditList);
        return new Result<List<CustomerCredit>>().error();
    }
}
