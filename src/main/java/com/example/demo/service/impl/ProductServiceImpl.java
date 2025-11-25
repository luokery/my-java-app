package com.example.demo.service.impl;

import com.example.demo.converter.ProductConverter;
import com.example.demo.cosnst.ProductEnum;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.model.entity.Product;
import com.example.demo.service.api.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
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
        if (productDto.getId() != null) { // This is an update operation
            Product existingProduct = productMapper.findById(productDto.getId());
            if (existingProduct == null) {
                throw new BusinessException(ProductEnum.PRODUCT_NOT_FOUND);
            }
        }
        Product product = ProductConverter.INSTANCE.toEntity(productDto);
        productMapper.save(product);
        return ProductConverter.INSTANCE.entityToDto(product);
    }

    @Override
    public ProductDTO findById(String id) {
        Product product = productMapper.findById(id);
        if (product == null) {
			try {
				throw BusinessException.class.getDeclaredConstructor().newInstance(ProductEnum.PRODUCT_NOT_FOUND);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            throw new BusinessException(ProductEnum.PRODUCT_NOT_FOUND);
        }
        return ProductConverter.INSTANCE.entityToDto(product);
    }

    @Override
    public List<ProductDTO> findAll() {
        return productMapper.findAll().stream()
                .map(ProductConverter.INSTANCE::entityToDto)
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
