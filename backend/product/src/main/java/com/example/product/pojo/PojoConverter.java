package com.example.product.pojo;

import com.example.product.pojo.dto.ProductDto;
import com.example.product.pojo.vo.ProductVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PojoConverter {


    @Mapping(source = "updateTime", target = "updateTime", qualifiedByName = "formatDateTime")
    ProductVo DtotoVo(ProductDto productDto);

    List<ProductVo> DtotoVoList(List<ProductDto> productDtoList);



    @Named("formatDateTime")
    default String formatDateTime(LocalDateTime time) {
        if (time == null) return null;
        time = time.withNano(0);
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}


