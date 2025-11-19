package com.example.demo.service;

import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public Product save(Product product) {
        productMapper.save(product);
        return product;
    }

    @Override
    public Product findById(Long id) {
        return productMapper.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productMapper.deleteById(id);
    }
}
