package com.cx330.service;

import com.cx330.entity.AddressInfo;

import java.util.List;

public interface AddressInfoService {

    // 新增地址
    public int addAddress(AddressInfo addressInfo);
    // 修改地址
    public int updateAdder(AddressInfo addressInfo);
    // 删除地址
    public int deleteAddress(int id);
    // 查询出所有地址
    public List<AddressInfo> selectAllAddress();
}
