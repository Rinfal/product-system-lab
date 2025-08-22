package com.example.product.mapper;

import com.example.product.pojo.dto.ProductDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    void insert(ProductDto productDto);
    List<ProductDto> search(ProductDto productDto);
    List<ProductDto> getAll();
    void delete(String productId);
    void update(ProductDto productDto);
    ProductDto getById(@Param("productId") String productId);

}
