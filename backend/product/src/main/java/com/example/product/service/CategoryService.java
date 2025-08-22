package com.example.product.service;

import com.example.product.mapper.CategoryMapper;
import com.example.product.pojo.PojoConverter;
import com.example.product.pojo.vo.CategoryVo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {


    @Autowired
    private PojoConverter pojoConverter;




    @Resource
    private CategoryMapper categoryMapper;

//    @Override
//    public Category add(CategoryDto categoryDto){
//        Category categoryPojo = new Category();
//        System.out.println(categoryPojo);
//        BeanUtils.copyProperties(categoryDto, categoryPojo);//实现对象类型的转换(复制)userDto->user
//        System.out.println(categoryDto);
//        System.out.println(categoryPojo);
//        return categoryRepository.save(categoryPojo);//可以自动判断，无ID就新增，有ID就修改
//    }




    @Override
   public List<CategoryVo> getAll() {

      return categoryMapper.getAll();


    }


//    @Override
//    public List<Category> getAllName(){
//        categoryRepository.findByDeletedFlagFalse();
//        return categoryRepository.findByDeletedFlagFalse();




}
