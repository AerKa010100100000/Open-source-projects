package com.cx330.client.controller;


import com.cx330.client.service.ClientService;
import com.cx330.entity.Customer;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/add")
    public Result<String> addClient(Customer customer) {
        int addClient = clientService.addClient(customer);
        if (addClient == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @PutMapping("/update")
    public Result<String> updateClient(Customer customer) {
            int updateClient = clientService.updateClient(customer);
            if (updateClient == 1)
                return new Result<String>().ok();
            return new Result<String>().error();
    }

    @DeleteMapping("/delete")
    public Result<String> deleteClient(@RequestParam("customerId") Integer customerId) {
        int deleteClient = clientService.deleteClient(customerId);
        if (deleteClient == 1)
            return new Result<String>().ok();
        return new Result<String>().error();

    }

    @GetMapping("/getClientById")
    public Result<Customer> getClientById(@RequestParam("customerId")Integer customerId) {
        Customer customer = clientService.getClientById(customerId);
        if (customer!= null)
            return new Result<Customer>().ok().data(customer);
        return new Result<Customer>().error();
    }


    @GetMapping("/getClientByName")
    public Result<List<Customer>> getClientByName(String customerName) {
        List<Customer> customerList = clientService.getClientByName(customerName);
        if (customerList!= null)
            return new Result<List<Customer>>().ok().data(customerList);
        return new Result<List<Customer>>().error();
    }

    @GetMapping("/getClientList")
    public Result<List<Customer>> getClientList() {
        List<Customer> clientList = clientService.getClientList();

        if (clientList!= null)
            return new Result<List<Customer>>().ok().data(clientList);
        return new Result<List<Customer>>().error();
    }
}
