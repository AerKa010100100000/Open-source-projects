package com.cx330.service.impl;

import com.cx330.entity.ServiceInfo;
import com.cx330.mapper.ServiceInfoMapper;
import com.cx330.service.ServiceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ServiceInfoImpl implements ServiceInfoService {
    @Autowired
    private ServiceInfoMapper serviceInfoMapper;

    // 查询所有服务信息
    @Override
    public List<ServiceInfo> getAllServiceInfo() {
        return serviceInfoMapper.getAllServiceInfo();
    }

    // 根据服务名称或服务代码查询服务信息
    @Transactional
    @Override
    public List<ServiceInfo> queryServiceInfo(String serviceName, String serviceCode) {
        return serviceInfoMapper.queryServiceInfo(serviceName, serviceCode);
    }

    // 注册服务信息
    @Override
    public int registerService(ServiceInfo serviceInfo) {
        return serviceInfoMapper.registerService(serviceInfo);
    }

    // 更新服务信息
    @Override
    public int updateServiceInfo(ServiceInfo serviceInfo) {
        return serviceInfoMapper.updateServiceInfo(serviceInfo);
    }

    // 根据服务ID删除服务信息
    @Override
    public int deleteServiceInfo(Integer serviceId) {
        return serviceInfoMapper.deleteServiceInfo(serviceId);
    }

    @Override
    public int deleteServiceInfoByCompanyId(Integer companyId) {
        return serviceInfoMapper.deleteServiceInfoByCompanyId(companyId);
    }
}
