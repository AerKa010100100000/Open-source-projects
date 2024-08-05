package com.cx330.controller;


import com.cx330.entity.ContactInfo;
import com.cx330.service.ContactInfoService;
import com.cx330.utility.Result;
import com.cx330.utility.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ContactInfo")
public class ContactInfoController {

    @Autowired
    private ContactInfoService contactInfoService;

    // 添加公司联系
    @PostMapping("/addContactInfo")
    public Result<Object> addContactInfo(ContactInfo contactInfo) {
        int flog = contactInfoService.addContactInfo(contactInfo);
        if (flog == 1)
            return new Result<>().ok();

        return new Result<>()
                .error()
                .code(ResultCodeEnum.SQL_INSERT_ERROR.getCode())
                .message(ResultCodeEnum.SQL_INSERT_ERROR.getMessage());
    }

    // 更新公司联系
    @PutMapping("/updateContactInfo")
    public Result<Object> updateContactInfo( ContactInfo contactInfo) {
        int flog = contactInfoService.updateContactInfo(contactInfo);
        if (flog == 1)
            return new Result<>().ok();

        return new Result<>()
                .error()
                .code(ResultCodeEnum.SQL_UPDATE_ERROR.getCode())
                .message(ResultCodeEnum.SQL_UPDATE_ERROR.getMessage());
    }

    // 公司分类查询
    @GetMapping("/queryContactInfo")
    public Result<List<ContactInfo>> queryContactInfo(@RequestParam("contactType") String contactType) {
        List<ContactInfo> contactInfos = contactInfoService.queryContactInfo(contactType);
        return new Result<List<ContactInfo>>()
                .ok()
                .data(contactInfos);
    }
}
