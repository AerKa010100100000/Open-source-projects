package com.cx330.controller;



import com.cx330.entity.CompanyInfo;
import com.cx330.service.CompanyDetailedService;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/CompanyDetailed")
public class CompanyDetailedController {

    @Autowired
    private CompanyDetailedService companyDetailedService;

    // 公司详细信息查询
    @GetMapping("CompanyDetailedAll")
    public Result<List<CompanyInfo>> CompanyDetailedAll() {
        List<CompanyInfo> companyInfos = companyDetailedService.selectCompanyAdders();

        return new Result<List<CompanyInfo>>()
                .ok()
                .data(companyInfos);
    }
}

