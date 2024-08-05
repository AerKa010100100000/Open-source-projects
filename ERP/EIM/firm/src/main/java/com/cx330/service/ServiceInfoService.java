package com.cx330.service;

import com.cx330.entity.ServiceInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ServiceInfoService {
    // 查询所有服务信息
    public List<ServiceInfo> getAllServiceInfo();
    // 根据服务名称和代码查询
    public List<ServiceInfo> queryServiceInfo(String serviceName, String serviceCode);
    // 注册服务
    public int registerService(ServiceInfo serviceInfo);

    // 更新服务信息
    public int updateServiceInfo(ServiceInfo serviceInfo);

    // 删除服务信息
    public int deleteServiceInfo(Integer serviceId);

    // 根据公司ID删除服务信息
    public int deleteServiceInfoByCompanyId(Integer companyId);
}
