package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.Result;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Result<Product> save(@RequestBody Product product) {
        return Result.success(productService.save(product));
    }

    @GetMapping("/{id}")
    public Result<Product> findById(@PathVariable Long id) {
        return Result.success(productService.findById(id));
    }

    @GetMapping
    public Result<List<Product>> findAll() {
        return Result.success(productService.findAll());
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return Result.success();
    }
}
