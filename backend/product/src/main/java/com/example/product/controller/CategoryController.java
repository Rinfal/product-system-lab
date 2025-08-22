package com.example.product.controller;


import com.example.common.pojo.ResponseMessage;
import com.example.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

//    @PostMapping
//    public ResponseMessage add(@RequestBody CategoryDto categoryDto){
//        Category categoryNew = categoryService.add(categoryDto);
//        return ResponseMessage.success(categoryNew);
//    }

   @GetMapping
    public ResponseMessage getAll() {
       return ResponseMessage.success(categoryService.getAll());
    }

}
