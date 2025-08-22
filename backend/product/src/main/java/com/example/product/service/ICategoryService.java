package com.example.product.service;

import com.example.product.pojo.vo.CategoryVo;

import java.util.List;

public interface ICategoryService {
    //Category add(CategoryDto category);
    List<CategoryVo> getAll();

}
