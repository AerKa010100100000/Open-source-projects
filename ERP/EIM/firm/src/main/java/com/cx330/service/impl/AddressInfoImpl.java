package com.cx330.service.impl;

import com.cx330.entity.AddressInfo;
import com.cx330.mapper.AddressInfoMapper;
import com.cx330.service.AddressInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressInfoImpl implements AddressInfoService {

    @Autowired
    private AddressInfoMapper addressInfoMapper;

    // 添加地址信息
    @Override
    public int addAddress(AddressInfo addressInfo) {

        return addressInfoMapper.addAddress(addressInfo);
    }

    // 修改地址信息
    @Override
    public int updateAdder(AddressInfo addressInfo) {
        return addressInfoMapper.updateAdder(addressInfo);
    }

    // 删除地址信息
    @Override
    public int deleteAddress(int addressId) {
        return addressInfoMapper.deleteAddress(addressId);
    }

    // 查询所有地址信息
    @Override
    public List<AddressInfo> selectAllAddress() {
        return addressInfoMapper.selectAllAddress();
    }
}
