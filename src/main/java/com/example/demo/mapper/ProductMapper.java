package com.example.demo.mapper;

import com.example.demo.model.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    int save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    int deleteById(Long id);
}
