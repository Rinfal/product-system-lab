package com.example.product.pojo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class ProductDto {
    private String productId;
    private String productName;
    private String categoryName;
    private Integer categoryId;
    private String productNote;
    private BigDecimal price;
    private BigDecimal memberPrice;
    private Integer stock;
    private String originPlace;
    private String shippingPlace;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;
    private String createdBy;
    //可以解决LocalDateTime类型无法被序列化存入redis的问题
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;
    private String updatedBy;
    private Boolean deletedFlag;
    private String reservedField1;
    private String reservedField2;

}


