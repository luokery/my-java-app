package com.example.demo.service.api;

import com.example.demo.model.dto.ProductDto;
import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto productDto);
    ProductDto findById(String id);
    List<ProductDto> findAll();
    void deleteById(String id);
}
