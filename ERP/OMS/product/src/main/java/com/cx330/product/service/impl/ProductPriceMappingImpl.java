package com.cx330.product.service.impl;

import com.cx330.entity.Product;
import com.cx330.entity.ProductPrice;
import com.cx330.entity.ProductPriceMapping;
import com.cx330.product.mapper.ProductPriceMappingDao;
import com.cx330.product.service.ProductPriceMappingService;
import com.cx330.product.service.ProductPriceService;
import com.cx330.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ProductPriceMappingImpl implements ProductPriceMappingService {
    @Autowired
    private ProductPriceMappingDao productPriceMappingDao;
    @Lazy
    @Autowired
    private ProductPriceService productPriceService;
    @Lazy
    @Autowired
    private ProductService productService;
    @Override
    public int addMapping(ProductPriceMapping mapping) {
        Product productById = productService.getProductById(mapping.getProductId());
        ProductPrice priceById = productPriceService.getPriceById(mapping.getPriceId());
        if (priceById == null || productById == null)
            throw new RuntimeException("Price or product not exist");
        return productPriceMappingDao.addMapping(mapping);
    }

    @Override
    public int updateMapping(ProductPriceMapping mapping) {
        Product productById = productService.getProductById(mapping.getProductId());
        ProductPrice priceById = productPriceService.getPriceById(mapping.getPriceId());
        if (priceById == null || productById == null)
            throw new RuntimeException("Price or product not exist");
        return productPriceMappingDao.updateMapping(mapping);
    }

    @Override
    public int deleteMappingByProductId(Integer productId) {

        return productPriceMappingDao.deleteMappingByProductId(productId);
    }

    @Override
    public int deleteMappingById(Integer mappingId) {
        return productPriceMappingDao.deleteMappingById(mappingId);
    }

    @Override
    public ProductPriceMapping getMappingByProductId(Integer productId) {
        return productPriceMappingDao.getMappingByProductId(productId);
    }

    @Override
    public ProductPriceMapping getMappingById(Integer mappingId) {
        return productPriceMappingDao.getMappingById(mappingId);
    }

    @Override
    public List<ProductPriceMapping> getAllMappings() {
        return productPriceMappingDao.getAllMappings();
    }
}
