package com.cx330.supplier.service;

import com.cx330.entity.Supplier;

import java.util.List;

public interface SupplierService {
    // 添加供应商
    int addSupplier(Supplier supplier);
    // 修改供应商
    int updateSupplier(Supplier supplier);
    // 删除供应商
    int deleteSupplier(int supplierId);
    // 根据id查询供应商信息
    Supplier getSupplierById(int supplierId);
    // 查询供应商列表
    List<Supplier> getSupplierList();
    // 根据供应商名称模糊查询供应商列表
    List<Supplier> getSupplierListBySupplierName(String supplierName);
}
