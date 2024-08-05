package com.cx330.controller;


import com.cx330.entity.ServiceInfo;
import com.cx330.service.ServiceInfoService;
import com.cx330.utility.Result;
import com.cx330.utility.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serviceInfo")
public class ServiceInfoController {

    @Autowired
    private ServiceInfoService serviceInfoService;

    // 根据服务名称或服务代码查询服务信息
    @GetMapping("/queryServiceInfo")
    public Result<List<ServiceInfo>> queryServiceInfo(@RequestParam(value = "serviceName", required = false) String serviceName, @RequestParam(value = "serviceCode", required = false) String serviceCode) {
        List<ServiceInfo> serviceInfoList = serviceInfoService.queryServiceInfo(serviceName, serviceCode);
        if (serviceInfoList != null && !serviceInfoList.isEmpty())
            return new Result<List<ServiceInfo>>()
                    .ok()
                    .data(serviceInfoList);
        return new Result<List<ServiceInfo>>()
                    .error()
                    .code(ResultCodeEnum.DATA_NOT_EXIST.getCode())
                    .message(ResultCodeEnum.DATA_NOT_EXIST.getMessage());
    }

    // 查询所有服务信息
    @GetMapping("/getAllServiceInfo")
    public Result<List<ServiceInfo>> getAllServiceInfo() {
        List<ServiceInfo> serviceInfoList = serviceInfoService.getAllServiceInfo();
        if (serviceInfoList != null && !serviceInfoList.isEmpty())
            return new Result<List<ServiceInfo>>()
                    .ok()
                    .data(serviceInfoList);
        return new Result<List<ServiceInfo>>()
                    .error()
                    .code(ResultCodeEnum.SQL_SELECT_ERROR.getCode())
                    .message(ResultCodeEnum.SQL_SELECT_ERROR.getMessage());
    }

    // 注册服务信息
    @PostMapping("/registerService")
    public Result<ServiceInfo> registerService(ServiceInfo serviceInfo) {
        int result = serviceInfoService.registerService(serviceInfo);
        if (result > 0)
            return new Result<ServiceInfo>()
                    .ok()
                    .data(serviceInfo);
        return new Result<ServiceInfo>()
                    .error()
                    .code(ResultCodeEnum.SQL_INSERT_ERROR.getCode())
                    .message(ResultCodeEnum.SQL_INSERT_ERROR.getMessage());
    }

    // 更新服务信息
    @PutMapping("/updateService")
    public Result<ServiceInfo> updateService(ServiceInfo serviceInfo) {
        int result = serviceInfoService.updateServiceInfo(serviceInfo);
        if (result > 0)
            return new Result<ServiceInfo>()
                    .ok()
                    .data(serviceInfo);
        return new Result<ServiceInfo>()
                    .error()
                    .code(ResultCodeEnum.SQL_UPDATE_ERROR.getCode())
                    .message(ResultCodeEnum.SQL_UPDATE_ERROR.getMessage());
    }

    // 删除服务信息
    @DeleteMapping("/deleteService")
    public Result<String> deleteService(@RequestParam(value = "serviceId") Integer serviceId) {
        int result = serviceInfoService.deleteServiceInfo(serviceId);
        if (result > 0)
            return new Result<String>()
                    .ok()
                    .data("删除成功");
        return new Result<String>()
                    .error()
                    .code(ResultCodeEnum.SQL_DELETE_ERROR.getCode())
                    .message(ResultCodeEnum.SQL_DELETE_ERROR.getMessage());
    }
}
