package com.cx330.mapper;


import com.cx330.entity.ServiceInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ServiceInfoMapper {
    // 查询所有服务信息
    @Select("SELECT * FROM service_info")
    List<ServiceInfo> getAllServiceInfo();
    // 根据服务名称和代码查询
    List<ServiceInfo> queryServiceInfo(@Param("serviceName") String serviceName, @Param("serviceCode") String serviceCode);
    // 注册服务
    @Insert("INSERT INTO service_info(service_name, service_code, unit_price, company_id) VALUES(#{serviceName}, #{serviceCode}, #{unitPrice}, #{companyId})")
    int registerService(ServiceInfo serviceInfo);

    // 更新服务信息
    @Update("UPDATE service_info SET service_name = #{serviceName}, service_code = #{serviceCode}, unit_price = #{unitPrice}, company_id = #{companyId} WHERE service_id = #{serviceId}")
    int updateServiceInfo(ServiceInfo serviceInfo);

    // 删除服务信息
    @Delete("DELETE FROM service_info WHERE service_id = #{serviceId}")
    int deleteServiceInfo(Integer serviceId);

    // 根据公司id删除服务信息
    @Delete("DELETE FROM service_info WHERE company_id = #{companyId}")
    int deleteServiceInfoByCompanyId(Integer companyId);
}
