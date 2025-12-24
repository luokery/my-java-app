package com.example.demo.service.api;

import com.example.demo.model.dto.ProductDTO;
import com.example.demo.validation.Groups;

import java.util.List;

import org.springframework.validation.annotation.Validated;

@Validated
public interface ProductService {
    ProductDTO save(@Validated(Groups.Create.class) ProductDTO productDto);
    ProductDTO findById(String id);
    List<ProductDTO> findAll();
    void deleteById(String id);
}
