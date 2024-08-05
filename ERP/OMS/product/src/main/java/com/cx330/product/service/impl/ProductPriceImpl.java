package com.cx330.product.service.impl;

import com.cx330.entity.ProductPrice;
import com.cx330.product.mapper.ProductPriceDao;
import com.cx330.product.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductPriceImpl implements ProductPriceService {
    @Autowired
    private ProductPriceDao productPriceDao;
    @Override
    public int addPrice(ProductPrice productPrice) {
        return productPriceDao.addPrice(productPrice);
    }

    @Override
    public int updatePrice(ProductPrice productPrice) {
        return productPriceDao.updatePrice(productPrice);
    }

    @Override
    public int deletePrice(Integer priceId) {
        return productPriceDao.deletePrice(priceId);
    }

    @Override
    public ProductPrice getPriceById(Integer priceId) {
        return productPriceDao.getPriceById(priceId);
    }

    @Override
    public List<ProductPrice> getAllPrices() {
        return productPriceDao.getAllPrices();
    }
}
