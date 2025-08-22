package com.example.product.controller;

import com.example.product.feignClients.UserFeignClient;
import com.example.product.pojo.PojoConverter;
import com.example.product.pojo.dto.ProductDto;
import com.example.common.pojo.ResponseMessage;
import com.example.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.common.security.JwtUtil.getUserIdByToken;

@RestController//接口方法返回对象，对象转换成json文本
@RequestMapping("/product")// localhost:8088/user/**
public class ProductController {
    @Autowired
    IProductService productService;

    @Autowired
    private PojoConverter pojoConverter;

    @Autowired
    private UserFeignClient userFeignClient;
    //增加
    @PostMapping("/add") //URL：locolhost:8088/user 前端请求 method:post
    public ResponseMessage add(@RequestBody ProductDto productDto) {
        return ResponseMessage.success(
                productService.add(productDto));
    }

    @PostMapping("/search")
    public ResponseMessage search(@RequestBody ProductDto productDto,@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize) {
        return ResponseMessage.success(productService.search(productDto,pageNum,pageSize));
    }

    @GetMapping("getById/{id}")
    public ResponseMessage getById(@PathVariable("id") String productId) {

        //在controller层再转为Vo
        return ResponseMessage.success(pojoConverter.DtotoVo(productService.getById(productId)));
    }

    @PutMapping("/update")
    public ResponseMessage update(@RequestBody ProductDto productDto ,@RequestHeader("Authorization") String token){
        productDto.setUpdatedBy(userFeignClient.getUserNameById(getUserIdByToken(token)));

        //用户名应该在网关层得到最好,不然还要写feign
        return ResponseMessage.success( productService.update(productDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage delete(@PathVariable("id") String productId) {
        return ResponseMessage.success(productService.delete(productId));
    }

    @GetMapping("/getAll")
    public ResponseMessage getAll() {
        return ResponseMessage.success(productService.getAll());
    }

    @GetMapping("/getPage")
    public ResponseMessage getPage(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize) {
        return ResponseMessage.success(productService.getPage(pageNum,pageSize));
    }




//    @DeleteMapping("/delete")
//    public ResponseMessage delete


    //@GetMapping
    //public ResponseMessage


    //查询
    //修改
    //删除
}
