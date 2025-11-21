package com.example.demo.controller;

import com.example.demo.converter.ProductConverter;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.model.vo.Result;
import com.example.demo.service.api.ProductService;
import com.example.demo.validation.Groups;
import com.example.demo.model.vo.ProductVo;

import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Result<ProductVo> save(@Validated(Groups.Create.class) @RequestBody ProductVo productVo) {
        log.info("Saving product: {}", productVo);
        
        ProductDto productDto = ProductConverter.INSTANCE.toDto(productVo);
        ProductDto savedProductDto = productService.save(productDto);
        return Result.success(ProductConverter.INSTANCE.toVo(savedProductDto));
    }

    @PutMapping("/{id}")
    public Result<ProductVo> update(@PathVariable String id, @Validated(Groups.Update.class) @RequestBody ProductVo productVo) {
        log.info("Updating product with id: {}, data: {}", id, productVo);
        
        ProductDto productDto = ProductConverter.INSTANCE.toDto(productVo);
        productDto.setId(id);
        
        ProductDto updatedProductDto = productService.save(productDto);
        return Result.success(ProductConverter.INSTANCE.toVo(updatedProductDto));
    }

    @GetMapping("/{id}")
    public Result<ProductVo> findById(@Validated(Groups.Query.class) @PathVariable String id) {
        log.info("Finding product by id: {}", id);
        
        ProductDto productDto = productService.findById(id);
        return Result.success(ProductConverter.INSTANCE.toVo(productDto));
    }

    @GetMapping
    public Result<List<ProductVo>> findAll() {
        log.info("Finding all products");
        List<ProductDto> productDtos = productService.findAll();
        List<ProductVo> productVos = productDtos.stream()
                .map(ProductConverter.INSTANCE::toVo)
                .collect(Collectors.toList());
        return Result.success(productVos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Validated(Groups.Delete.class) @NotBlank String id) {
        log.info("Deleting product by id: {}", id);
        productService.deleteById(id);
    }
}
