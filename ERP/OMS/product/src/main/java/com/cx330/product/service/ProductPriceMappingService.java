package com.cx330.product.service;

import com.cx330.entity.ProductPriceMapping;

import java.util.List;

public interface ProductPriceMappingService {
    // 添加映射关系
    int addMapping(ProductPriceMapping mapping);
    // 修改映射关系
    int updateMapping(ProductPriceMapping mapping);
    // 根据商品ID删除映射关系
    int deleteMappingByProductId(Integer productId);
    // 根据映射ID删除映射关系
    int deleteMappingById(Integer mappingId);
    // 根据商品ID查询映射关系
    ProductPriceMapping getMappingByProductId(Integer productId);
    // 根据映射ID查询映射关系
    ProductPriceMapping getMappingById(Integer mappingId);
    // 查询所有映射列表
    List<ProductPriceMapping> getAllMappings();
}
