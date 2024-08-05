package com.cx330.controller;


import com.cx330.entity.CompanyInfo;
import com.cx330.utility.Result;
import com.cx330.utility.ResultCodeEnum;
import com.cx330.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firm")
public class CompanyInformation {

    @Autowired
    private CompanyInfoService companyInfoService;

    // 根据公司ID查询公司基本信息
    @GetMapping("selectOne")
    public Result<CompanyInfo> selectOne(@RequestParam(value = "companyId") Integer companyId) {
        CompanyInfo companyInfo = companyInfoService.selectOne(companyId);
        if (companyInfo == null) {
            return new Result<CompanyInfo>()
                    .error()
                    .code(ResultCodeEnum.DATA_NOT_EXIST.getCode())
                    .message(ResultCodeEnum.DATA_NOT_EXIST.getMessage());
        }
        return new Result<CompanyInfo>().ok().data(companyInfo);
    }

    // 查询所有公司基本信息
    @GetMapping("selectAll")
    public Result<List<CompanyInfo>> selectAll() {
        List<CompanyInfo> companyInfos = companyInfoService.selectAll();

        return new Result<List<CompanyInfo>>().ok().data(companyInfos);
    }

    // 新增公司基本信息
    @PostMapping("insert")
    public Result<String> insert(CompanyInfo companyInfo) {
        int insert = companyInfoService.insert(companyInfo);
        if (insert == 0) {
            return new Result<String>()
                    .error()
                    .code(ResultCodeEnum.SQL_INSERT_ERROR.getCode())
                    .message(ResultCodeEnum.SQL_INSERT_ERROR.getMessage());
        }
        return new Result<String>().ok().message("插入成功");
    }


    // 修改公司基本信息
    @PutMapping("update")
    public Result<String> update(CompanyInfo companyInfo) {
        int update = companyInfoService.update(companyInfo);
        if (update == 0) {
            return new Result<String>()
                    .error()
                    .code(ResultCodeEnum.SQL_UPDATE_ERROR.getCode())
                    .message(ResultCodeEnum.SQL_UPDATE_ERROR.getMessage());
        }
        return new Result<String>().ok().message("更新成功");
    }

    // 删除公司基本信息
    @DeleteMapping("delete")
    public Result<String> delete(@RequestParam(value = "companyId") Integer companyId) {
        int delete = companyInfoService.delete(companyId);
        if (delete == 0) {
            return new Result<String>()
                    .error()
                    .code(ResultCodeEnum.SQL_DELETE_ERROR.getCode())
                    .message(ResultCodeEnum.SQL_DELETE_ERROR.getMessage());
        }
        return new Result<String>().ok().message("删除成功");
    }

}
