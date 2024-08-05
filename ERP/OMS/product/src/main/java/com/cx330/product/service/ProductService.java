package com.cx330.product.service;

import com.cx330.entity.Product;

import java.util.List;

public interface ProductService {
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
