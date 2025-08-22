package com.example.product.pojo.po;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name ="tb_category")
@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_description")
    private String categoryDescription;

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
