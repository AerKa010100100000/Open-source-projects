package com.cx330.product.mapper;


import com.cx330.entity.ProductPrice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductPriceDao {
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
