package com.example.demo.service.api;

import com.example.demo.model.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    ProductDTO save(ProductDTO productDto);
    ProductDTO findById(String id);
    List<ProductDTO> findAll();
    void deleteById(String id);
}
