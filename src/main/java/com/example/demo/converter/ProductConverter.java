package com.example.demo.converter;

import com.example.demo.model.dto.ProductDto;
import com.example.demo.model.entity.Product;
import com.example.demo.model.vo.ProductVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductConverter {

    ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    ProductVo toVo(ProductDto productDto);
}
