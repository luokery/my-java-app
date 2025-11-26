package com.example.demo.controller;

import com.example.demo.converter.ProductConverter;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.service.api.ProductService;
import com.example.demo.validation.Groups;
import com.example.demo.model.vo.ProductVO;
import com.example.demo.model.vo.RequestVO;
import com.example.demo.model.vo.ResponseVO;
import com.example.demo.model.vo.Result;

import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(rollbackFor = Exception.class)
    public ResponseVO<ProductVO> save(@Validated(Groups.Create.class) @RequestBody RequestVO<ProductVO> requestVO) {
    	log.info("业务处理: 开始; UserController: save data={}", requestVO);
        
    	ProductVO dataVO = null;

		/** 参数处理 */
    	dataVO = requestVO.getData();
		
    	/** 业务处理 */
        ProductDTO productDto = ProductConverter.INSTANCE.toDTO(dataVO);
        ProductDTO savedProductDto = productService.save(productDto);
        return Result.success(ProductConverter.INSTANCE.toVO(savedProductDto));
    }

    @PutMapping("/{id}")
    public ResponseVO<ProductVO> update(@PathVariable String id, @Validated(Groups.Update.class) @RequestBody ProductVO productVo) {
        log.info("Updating product with id: {}, data: {}", id, productVo);
        
        ProductDTO productDto = ProductConverter.INSTANCE.toDTO(productVo);
        productDto.setId(id);
        
        ProductDTO updatedProductDto = productService.save(productDto);
        return Result.success(ProductConverter.INSTANCE.toVO(updatedProductDto));
    }

    @GetMapping("/{id}")
    public ResponseVO<ProductVO> findById(@Validated(Groups.Query.class) @PathVariable String id) {
        log.info("Finding product by id: {}", id);
        
        ProductDTO productDto = productService.findById(id);
        return Result.success(ProductConverter.INSTANCE.toVO(productDto));
    }

    @GetMapping
    public ResponseVO<List<ProductVO>> findAll() {
        log.info("Finding all products");
        List<ProductDTO> productDtos = productService.findAll();
        List<ProductVO> productVos = productDtos.stream()
                .map(ProductConverter.INSTANCE::toVO)
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
