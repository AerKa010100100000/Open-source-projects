package com.cx330.product.mapper;


import com.cx330.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDao {
    // 添加商品
    int addProduct(Product product);
    // 修改商品
    int updateProduct(Product product);
    // 删除商品
    int deleteProduct(Integer productId);
    // 根据商品ID查询商品信息
    Product getProductById(Integer productId);
    // 根据商品名称模糊查询商品信息
    List<Product> getProductByName(String productName);
    // 查询商品列表
    List<Product> getProductList();
}
