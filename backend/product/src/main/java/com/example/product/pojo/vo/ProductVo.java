package com.example.product.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVo {
    private String productId;
    private String productName;
    private String categoryName;
    private Integer categoryId;
    private BigDecimal price;
    private BigDecimal memberPrice;
    private Integer stock;
    private String originPlace;
    private String shippingPlace;
    private String updateTime;
    private String updatedBy;
    private String productNote;


}
