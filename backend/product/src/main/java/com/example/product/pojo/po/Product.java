package com.example.product.pojo.po;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name="tb_product")
@Entity
@Data
public class Product {
    @Id
    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "product_note")
    private String productNote;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "member_price")
    private BigDecimal memberPrice;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "origin_place")
    private String originPlace;

    @Column(name = "shipping_place")
    private String shippingPlace;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "deleted_flag")
    private Boolean deletedFlag;

    @Column(name = "reserved_field1")
    private String reservedField1;

    @Column(name = "reserved_field2")
    private String reservedField2;
}
