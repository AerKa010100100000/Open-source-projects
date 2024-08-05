package com.cx330.product.controller;

import com.cx330.entity.ProductPrice;
import com.cx330.product.mapper.ProductPriceDao;
import com.cx330.product.service.ProductPriceService;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/productPrice")
public class ProductPriceController {
    @Autowired
    private ProductPriceService productPriceService;


    @PostMapping("/addPrice")
    public Result<String> addPrice(ProductPrice productPrice) {
        int addPrice = productPriceService.addPrice(productPrice);
        if (addPrice == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @PutMapping("/updatePrice")
    public Result<String> updatePrice(ProductPrice productPrice) {
        int updatePrice = productPriceService.updatePrice(productPrice);
        if (updatePrice == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @DeleteMapping("/deletePrice")
    public Result<String> deletePrice(@RequestParam("priceId") Integer priceId) {
        int deletePrice = productPriceService.deletePrice(priceId);
        if (deletePrice == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @GetMapping("/getPriceById")
    public Result<ProductPrice> getPriceById(@RequestParam("priceId") Integer priceId) {
        ProductPrice priceById = productPriceService.getPriceById(priceId);
        if (priceById!= null)
            return new Result<ProductPrice>().ok().data(priceById);
        return new Result<ProductPrice>().error();
    }

    @GetMapping("/getAllPrices")
    public Result<List<ProductPrice>> getAllPrices() {
        List<ProductPrice> allPrices = productPriceService.getAllPrices();
        if (allPrices!= null)
            return new Result<List<ProductPrice>>().ok().data(allPrices);
        return new Result<List<ProductPrice>>().error();
    }
}
