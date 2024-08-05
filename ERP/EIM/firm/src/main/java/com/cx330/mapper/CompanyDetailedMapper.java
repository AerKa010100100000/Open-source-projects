package com.cx330.mapper;


import com.cx330.entity.CompanyInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyDetailedMapper {
    List<CompanyInfo> selectAll();
}
