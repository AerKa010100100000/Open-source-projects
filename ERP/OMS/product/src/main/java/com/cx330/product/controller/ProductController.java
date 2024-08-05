package com.cx330.product.controller;

import com.cx330.entity.Product;
import com.cx330.product.service.ProductService;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/add")
    public Result<String> addProduct(Product product) {
        int addProduct = productService.addProduct(product);
        if (addProduct == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @PutMapping("/update")
    public Result<String> updateProduct(Product product) {
        int updateProduct = productService.updateProduct(product);
        if (updateProduct == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @DeleteMapping("/delete")
    public Result<String> deleteProduct(@RequestParam("productId") Integer productId) {
        int deleteProduct = productService.deleteProduct(productId);
        if (deleteProduct == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @GetMapping("/getProductById")
    public Result<Product> getProductById(@RequestParam("productId") Integer productId) {
        Product productById = productService.getProductById(productId);
        if (productById!= null)
            return new Result<Product>().ok().data(productById);
        return new Result<Product>().error();
    }

    @GetMapping("/getProductByName")
    public Result<List<Product>> getProductByName(@RequestParam("productName") String productName) {
        List<Product> productByName = productService.getProductByName(productName);
        if (productByName!= null && !productByName.isEmpty())
            return new Result<List<Product>>().ok().data(productByName);
        return new Result<List<Product>>().error();
    }

    @GetMapping("/getProductList")
    public Result<List<Product>> getProductList() {
        List<Product> productList = productService.getProductList();
        if (productList!= null && !productList.isEmpty())
            return new Result<List<Product>>().ok().data(productList);
        return new Result<List<Product>>().error();
    }
}
