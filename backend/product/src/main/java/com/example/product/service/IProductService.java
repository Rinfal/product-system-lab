package com.example.product.service;

import com.example.product.pojo.dto.ProductDto;
import com.example.product.pojo.vo.ProductVo;
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

public interface IProductService {
    //Dto转为Entity
    String add(ProductDto productDto);
    //查询
    PageInfo<ProductVo> search(ProductDto productDto, Integer pageNum, Integer pageSize);

    List<ProductVo> getAll();
    PageInfo<ProductVo> getPage(Integer pageNum, Integer pageSize);
    String update(ProductDto ProductDto);
    String delete(String productId);

    ProductDto getById(String productId);

    void reduceStock(String productId, Integer stock);

    @CacheEvict(cacheNames = "productDto", key = "#productId")
    void releaseStock(String productId, Integer numRelease);
}
