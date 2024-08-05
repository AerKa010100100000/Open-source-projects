package com.cx330.supplier.service.impl;

import com.cx330.entity.Supplier;
import com.cx330.supplier.mapper.SupplierDao;
import com.cx330.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SupplierImpl implements SupplierService {
    @Autowired
    private SupplierDao supplierDao;
    @Override
    public int addSupplier(Supplier supplier) {
        return supplierDao.addSupplier(supplier);
    }

    @Override
    public int updateSupplier(Supplier supplier) {
        return supplierDao.updateSupplier(supplier);
    }

    @Override
    public int deleteSupplier(int supplierId) {
        return supplierDao.deleteSupplier(supplierId);
    }

    @Override
    public Supplier getSupplierById(int supplierId) {
        return supplierDao.getSupplierById(supplierId);
    }

    @Override
    public List<Supplier> getSupplierList() {
        return supplierDao.getSupplierList();
    }

    @Override
    public List<Supplier> getSupplierListBySupplierName(String supplierName) {
        return supplierDao.getSupplierListBySupplierName(supplierName);
    }
}
