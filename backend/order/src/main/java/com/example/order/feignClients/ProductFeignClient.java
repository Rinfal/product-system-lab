package com.example.order.feignClients;

import com.example.common.pojo.ResponseMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(name = "product-server",path ="/internal/product")
public interface ProductFeignClient {

    @PutMapping ("/reduceStock")
    //BUG：stock这个名字要改
    void reduceStock(@RequestParam("productId") String productId, @RequestParam("stock") Integer stock);

    //先这么写吧以后再改
    @GetMapping(value = "/getStockById/{id}")
    Integer getStockById(@PathVariable(value = "id") String productId);

    @GetMapping("/getProductNameById/{id}")
    String getProductNameById(@PathVariable(value = "id") String productId);

    @GetMapping("/getProductPriceById/{id}")
    BigDecimal getProductPriceById(@PathVariable(value = "id") String productId);

    @PutMapping ("/releaseStock")
    void releaseStock(@RequestParam("productId")String productId, @RequestParam("numRelease") Integer numRelease);
//    @PostMapping("/api/product/stock/decrease")
//    public Result<Boolean> decreaseStock(@RequestBody List<StockDeductionRequest> requestList);
}
