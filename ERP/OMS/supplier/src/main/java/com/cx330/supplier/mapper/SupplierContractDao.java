package com.cx330.supplier.mapper;


import com.cx330.entity.SupplierContract;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SupplierContractDao {
    // 添加合同
    int addContract(SupplierContract supplierContract);
    // 修改合同
    int updateContract(SupplierContract supplierContract);
    // 删除合同
    int deleteContract(Integer contractId);
    // 查询合同
    SupplierContract getContract(Integer contractId);
    // 查询合同列表
    List<SupplierContract> getContractList();
}
