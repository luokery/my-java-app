package com.example.demo.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.model.dto.ProductDTO;
import com.example.demo.model.entity.Product;
import com.example.demo.model.vo.ProductVO;


@Mapper(componentModel = "spring")
public interface ProductConverter extends IMapstruct<ProductVO, ProductDTO, Product> {

    public ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);
    
//    ProductDto toDto(Product product);
//    
//    ProductDto toDto(ProductVo productVo);
//    
//    Product toEntity(ProductDto productDto);
//
//    ProductVo toVo(ProductDto productDto);
}
