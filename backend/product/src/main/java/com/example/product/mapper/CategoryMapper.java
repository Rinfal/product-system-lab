package com.example.product.mapper;

import com.example.product.pojo.po.Category;
import com.example.product.pojo.vo.CategoryVo;

import java.util.List;


public interface CategoryMapper {
    List<Category>  findByDeletedFlagFalse();
    List<CategoryVo>  getAll();
}
