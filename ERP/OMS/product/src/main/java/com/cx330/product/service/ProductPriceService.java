package com.cx330.product.service;

import com.cx330.entity.ProductPrice;

import java.util.List;

public interface ProductPriceService {
    // 添加价格
    int addPrice(ProductPrice productPrice);
    // 更新价格
    int updatePrice(ProductPrice productPrice);
    // 删除价格
    int deletePrice(Integer priceId);
    // 根据id查询价格
    ProductPrice getPriceById(Integer priceId);
    // 查询所有价格
    List<ProductPrice> getAllPrices();
}
