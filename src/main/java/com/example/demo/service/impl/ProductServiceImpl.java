package com.example.demo.service.impl;

import com.example.demo.converter.ProductConverter;
import com.example.demo.cosnst.ProductEnum;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.model.entity.Product;
import com.example.demo.service.api.ProductService;
import com.example.demo.validation.Groups;

import jakarta.validation.GroupSequence;
import jakarta.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
	
    private ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
		super();
		this.productMapper = productMapper;
	}

	@Override
    @Transactional
    public ProductDTO save(ProductDTO productDto) {
		
        Product existingProduct = productMapper.findById(productDto.getId());
        if (existingProduct != null) {
            throw new BusinessException(ProductEnum.PRODUCT_already_exists);
        }
        Product product = ProductConverter.INSTANCE.toEntity(productDto);
        productMapper.save(product);
        return ProductConverter.INSTANCE.entityToDTO(product);
    }

    @Override
    public ProductDTO findById(String id) {
        Product product = productMapper.findById(id);
        if (product == null) {
            throw new BusinessException(ProductEnum.PRODUCT_NOT_FOUND);
        }
        return ProductConverter.INSTANCE.entityToDTO(product);
    }

    @Override
    public List<ProductDTO> findAll() {
        return productMapper.findAll().stream()
                .map(ProductConverter.INSTANCE::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        Product product = productMapper.findById(id);
        if (product == null) {
            throw new BusinessException(ProductEnum.PRODUCT_NOT_FOUND);
        }
        productMapper.deleteById(id);
    }
}
