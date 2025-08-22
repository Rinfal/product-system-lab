package com.example.product.controller;

import com.example.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController//接口方法返回对象，对象转换成json文本
@RequestMapping("/internal/product")// localhost:8088/user/**
public class InternalProductController {

    @Autowired
    IProductService productService;

    @GetMapping("getStockById/{id}")
    public Integer getStockById(@PathVariable("id") String productId) {
        return  productService.getById(productId).getStock();
    }

    @PutMapping ("/reduceStock")
    public void reduceStock(@RequestParam("productId") String productId, @RequestParam("stock") Integer stock){
        productService.reduceStock(productId,stock);
    }

    @GetMapping("/getProductNameById/{id}")
    String getProductNameById(@PathVariable(value = "id") String productId){
        System.out.println(productService.getById(productId).getProductName());
        return  productService.getById(productId).getProductName();
    }

    @GetMapping("/getProductPriceById/{id}")
    BigDecimal getProductPriceById(@PathVariable(value = "id") String productId){
        System.out.println(productService.getById(productId).getPrice());
        return  productService.getById(productId).getPrice();
    }

    @PutMapping ("/releaseStock")
    public void releaseStock(@RequestParam("productId") String productId, @RequestParam("numRelease") Integer numRelease){
        productService.releaseStock(productId,numRelease);
    }

}
