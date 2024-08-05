package com.cx330.supplier.controller;


import com.cx330.entity.Supplier;
import com.cx330.supplier.service.SupplierService;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @PostMapping("/add")
    public Result<String> addSupplier(Supplier supplier) {
        int addSupplier = supplierService.addSupplier(supplier);
        if (addSupplier == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @PutMapping("/update")
    public Result<String> updateSupplier(Supplier supplier) {
        int updateSupplier = supplierService.updateSupplier(supplier);
        if (updateSupplier == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }


    @DeleteMapping("/delete")
    public Result<String> deleteSupplier(@RequestParam("supplerId") int supplierId) {
        int deleteSupplier = supplierService.deleteSupplier(supplierId);
        if (deleteSupplier == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @GetMapping("/getSupplierById")
    public Result<Supplier> getSupplierById(@RequestParam("supplerId") int supplierId) {
        Supplier supplier = supplierService.getSupplierById(supplierId);
        if (supplier != null)
            return new Result<Supplier>().ok().data(supplier);
        return new Result<Supplier>().error();
    }

    @GetMapping("/getSupplierList")
    public Result<List<Supplier>> getSupplierList() {
        List<Supplier> supplierList = supplierService.getSupplierList();
        if (supplierList != null)
            return new Result<List<Supplier>>().ok().data(supplierList);
        return new Result<List<Supplier>>().error();
    }

    @GetMapping("/getSupplierListBySupplierName")
    public Result<List<Supplier>> getSupplierListBySupplierName(@RequestParam("supplierName") String supplierName) {
        List<Supplier> supplierList = supplierService.getSupplierListBySupplierName(supplierName);
        if (supplierList != null)
            return new Result<List<Supplier>>().ok().data(supplierList);
        return new Result<List<Supplier>>().error();
    }
}
