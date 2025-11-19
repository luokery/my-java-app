package com.example.demo.controller;

import com.example.demo.controller.dto.Groups;
import com.example.demo.converter.ProductConverter;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.model.vo.Result;
import com.example.demo.service.api.ProductService;
import com.example.demo.model.vo.ProductVo;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final ProductConverter productConverter;

    public ProductController(ProductService productService, ProductConverter productConverter) {
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Result<ProductVo> save(@Validated(Groups.Create.class) @RequestBody ProductDto productDto) {
        log.info("Saving product: {}", productDto);
        ProductDto savedProductDto = productService.save(productDto);
        return Result.success(productConverter.toVo(savedProductDto));
    }

    @PutMapping("/{id}")
    public Result<ProductVo> update(@PathVariable Long id, @Validated(Groups.Update.class) @RequestBody ProductDto productDto) {
        log.info("Updating product with id: {}, data: {}", id, productDto);
        productDto.setId(id);
        ProductDto updatedProductDto = productService.save(productDto);
        return Result.success(productConverter.toVo(updatedProductDto));
    }

    @GetMapping("/{id}")
    public Result<ProductVo> findById(@PathVariable @Positive(message = "ID must be a positive number") Long id) {
        log.info("Finding product by id: {}", id);
        ProductDto productDto = productService.findById(id);
        return Result.success(productConverter.toVo(productDto));
    }

    @GetMapping
    public Result<List<ProductVo>> findAll() {
        log.info("Finding all products");
        List<ProductDto> productDtos = productService.findAll();
        List<ProductVo> productVos = productDtos.stream()
                .map(productConverter::toVo)
                .collect(Collectors.toList());
        return Result.success(productVos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable @Positive(message = "ID must be a positive number") Long id) {
        log.info("Deleting product by id: {}", id);
        productService.deleteById(id);
    }
}
