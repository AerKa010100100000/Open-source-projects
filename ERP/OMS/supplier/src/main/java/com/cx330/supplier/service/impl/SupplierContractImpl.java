package com.cx330.supplier.service.impl;


import com.cx330.entity.SupplierContract;
import com.cx330.supplier.mapper.SupplierContractDao;
import com.cx330.supplier.service.SupplierContractService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierContractImpl implements SupplierContractService {
    @Autowired
    private SupplierContractDao supplierContractDao;

    @Override
    public int addContract(SupplierContract supplierContract) {
        return supplierContractDao.addContract(supplierContract);
    }

    @Override
    public int updateContract(SupplierContract supplierContract) {
        return supplierContractDao.updateContract(supplierContract);
    }

    @Override
    public int deleteContract(Integer contractId) {
        return supplierContractDao.deleteContract(contractId);
    }

    @Override
    public SupplierContract getContract(Integer contractId) {
        return supplierContractDao.getContract(contractId);
    }

    @Override
    public List<SupplierContract> getContractList() {
        return supplierContractDao.getContractList();
    }
}
