package com.example.demo.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.model.dto.ProductDto;
import com.example.demo.model.entity.Product;
import com.example.demo.model.vo.ProductVo;


@Mapper(componentModel = "spring")
public interface ProductConverter extends IMapstruct<ProductVo, ProductDto, Product> {

    public ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);
    
//    ProductDto toDto(Product product);
//    
//    ProductDto toDto(ProductVo productVo);
//    
//    Product toEntity(ProductDto productDto);
//
//    ProductVo toVo(ProductDto productDto);
}
