package com.example.demo.service.impl;

import com.example.demo.converter.ProductConverter;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;
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
        if (productDto.getId() != null) { // This is an update operation
            Product existingProduct = productMapper.findById(productDto.getId());
            if (existingProduct == null) {
                throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND);
            }
        }
        Product product = productConverter.toEntity(productDto);
        productMapper.save(product);
        return productConverter.toDto(product);
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productMapper.findById(id);
        if (product == null) {
            throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND);
        }
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
        Product product = productMapper.findById(id);
        if (product == null) {
            throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        productMapper.deleteById(id);
    }
}
