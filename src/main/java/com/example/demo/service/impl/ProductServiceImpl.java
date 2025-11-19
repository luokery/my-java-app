package com.example.demo.service.impl;

import com.example.demo.converter.ProductConverter;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.model.entity.Product;
import com.example.demo.service.api.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductConverter productConverter;

    public ProductServiceImpl(ProductMapper productMapper, ProductConverter productConverter) {
        this.productMapper = productMapper;
        this.productConverter = productConverter;
    }

    @Override
    @Transactional
    public ProductDto save(ProductDto productDto) {
        Product product = productConverter.toEntity(productDto);
        productMapper.save(product);
        return productConverter.toDto(product);
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productMapper.findById(id);
        return productConverter.toDto(product);
    }

    @Override
    public List<ProductDto> findAll() {
        return productMapper.findAll().stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productMapper.deleteById(id);
    }
}
