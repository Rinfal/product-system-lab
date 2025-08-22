package com.example.product.pojo;

import com.example.product.pojo.dto.ProductDto;
import com.example.product.pojo.vo.ProductVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-07T18:37:50+0800",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class PojoConverterImpl implements PojoConverter {

    @Override
    public ProductVo DtotoVo(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        ProductVo productVo = new ProductVo();

        productVo.setUpdateTime( formatDateTime( productDto.getUpdateTime() ) );
        productVo.setProductId( productDto.getProductId() );
        productVo.setProductName( productDto.getProductName() );
        productVo.setCategoryName( productDto.getCategoryName() );
        productVo.setCategoryId( productDto.getCategoryId() );
        productVo.setPrice( productDto.getPrice() );
        productVo.setMemberPrice( productDto.getMemberPrice() );
        productVo.setStock( productDto.getStock() );
        productVo.setOriginPlace( productDto.getOriginPlace() );
        productVo.setShippingPlace( productDto.getShippingPlace() );
        productVo.setUpdatedBy( productDto.getUpdatedBy() );
        productVo.setProductNote( productDto.getProductNote() );

        return productVo;
    }

    @Override
    public List<ProductVo> DtotoVoList(List<ProductDto> productDtoList) {
        if ( productDtoList == null ) {
            return null;
        }

        List<ProductVo> list = new ArrayList<ProductVo>( productDtoList.size() );
        for ( ProductDto productDto : productDtoList ) {
            list.add( DtotoVo( productDto ) );
        }

        return list;
    }
}
