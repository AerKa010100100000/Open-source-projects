package com.cx330.supplier.controller;


import com.cx330.entity.SupplierContract;
import com.cx330.supplier.service.SupplierContractService;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplierContract")
public class SupplierContractController {
    @Autowired
    private SupplierContractService supplierContractService;

    @PostMapping("/add")
    public Result<String> addContract(SupplierContract supplierContract) {
        int addContract = supplierContractService.addContract(supplierContract);
        if (addContract == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @PutMapping("/update")
    public Result<String> updateContract(SupplierContract supplierContract) {
        int updateContract = supplierContractService.updateContract(supplierContract);
        if (updateContract == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }


    @DeleteMapping("/delete")
    public Result<String> deleteContract(@RequestParam("contractId") Integer contractId) {
        int deleteContract = supplierContractService.deleteContract(contractId);
        if (deleteContract == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @GetMapping("/getContract")
    public Result<SupplierContract> getContract(@RequestParam("contractId") Integer contractId) {
        SupplierContract contract = supplierContractService.getContract(contractId);
        if (contract != null)
            return new Result<SupplierContract>().ok().data(contract);
        return new Result<SupplierContract>().error();
    }
    @GetMapping("/getContractList")
    public Result<List<SupplierContract>> getContractList() {
        List<SupplierContract> contractList = supplierContractService.getContractList();
        if (contractList != null)
            return new Result<List<SupplierContract>>().ok().data(contractList);
        return new Result<List<SupplierContract>>().error();
    }

}