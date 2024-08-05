package com.cx330.product.service.impl;

import com.cx330.entity.Product;
import com.cx330.product.mapper.ProductDao;
import com.cx330.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public int addProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Override
    public int updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public int deleteProduct(Integer productId) {
        return productDao.deleteProduct(productId);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public List<Product> getProductByName(String productName) {
        return productDao.getProductByName(productName);
    }

    @Override
    public List<Product> getProductList() {
        return productDao.getProductList();
    }
}
