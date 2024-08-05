package com.cx330.controller;


import com.cx330.entity.AddressInfo;
import com.cx330.service.AddressInfoService;
import com.cx330.utility.Result;
import com.cx330.utility.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AddressInfo")
public class AddressInfoController {
    @Autowired
    private AddressInfoService addressInfoService;


    // 新增地址
    @PostMapping("/addAddress")
    public Result<Object> addAddress(AddressInfo addressInfo){
        int flog = addressInfoService.addAddress(addressInfo);
        if (flog == 1){
            return new Result<>().ok();
        }
        return new Result<>()
                .error()
                .code(ResultCodeEnum.SQL_INSERT_ERROR.getCode())
                .message(ResultCodeEnum.SQL_INSERT_ERROR.getMessage());
    }
    // 修改地址
    @PutMapping("updateAdder")
    public Result<Object> updateAdder(AddressInfo addressInfo){
        int flog = addressInfoService.updateAdder(addressInfo);
        if (flog == 1){
            return new Result<>().ok();
        }
        return new Result<>()
                .error()
                .code(ResultCodeEnum.SQL_UPDATE_ERROR.getCode())
                .message(ResultCodeEnum.SQL_UPDATE_ERROR.getMessage());
    }
    // 删除地址
    @DeleteMapping("deleteAddress")
    public Result<Object> deleteAddress(int id){
        int flog = addressInfoService.deleteAddress(id);
        if (flog == 1){
            return new Result<>().ok();
        }
        return new Result<>()
                .error()
                .code(ResultCodeEnum.SQL_DELETE_ERROR.getCode())
                .message(ResultCodeEnum.SQL_DELETE_ERROR.getMessage());
    }
    // 查询出所有地址
    @GetMapping("selectAllAddress")
    public Result<List<AddressInfo>> selectAllAddress(){
        List<AddressInfo> addressInfos = addressInfoService.selectAllAddress();
        if (addressInfos != null && !addressInfos.isEmpty()){
            return new Result<List<AddressInfo>>().ok().data(addressInfos);
        }
        return new Result<List<AddressInfo>>()
                .error()
                .code(ResultCodeEnum.SQL_SELECT_ERROR.getCode())
                .message(ResultCodeEnum.SQL_SELECT_ERROR.getMessage());
    }
}
