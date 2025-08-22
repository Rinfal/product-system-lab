package com.example.product.pojo.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
@Data
public class CategoryDto {
    @NotNull
    private String categoryCode;
    private String categoryName;
    private String categoryDescription;
    private LocalDateTime createTime;
    private String createdBy;
    private LocalDateTime updateTime;
    private String updatedBy;
    private Boolean deletedFlag;
    private String reservedField1;
    private String reservedField2;

}
