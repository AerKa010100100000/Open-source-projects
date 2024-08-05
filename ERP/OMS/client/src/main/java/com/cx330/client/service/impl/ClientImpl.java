package com.cx330.client.service.impl;

import com.cx330.client.mapper.ClientDao;
import com.cx330.client.service.ClientService;
import com.cx330.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientImpl implements ClientService {
    @Autowired
    private ClientDao clientDao;
    @Override
    public int addClient(Customer customer) {
        return clientDao.addClient(customer);
    }

    @Override
    public int updateClient(Customer customer) {
        return clientDao.updateClient(customer);
    }

    @Override
    public int deleteClient(Integer customerId) {
        return clientDao.deleteClient(customerId);
    }

    @Override
    public Customer getClientById(Integer customerId) {
        return clientDao.getClientById(customerId);
    }

    @Override
    public List<Customer> getClientByName(String customerName) {
        return clientDao.getClientByName(customerName);
    }

    @Override
    public List<Customer> getClientList() {
        return clientDao.getClientList();
    }
}
