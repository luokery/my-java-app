
package com.example.demo.controller;

import com.example.demo.converter.ProductConverter;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.model.vo.ProductVO;
import com.example.demo.service.api.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisLanguageDriverAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(value = ProductController.class, 
        excludeAutoConfiguration = {
                MybatisAutoConfiguration.class,
                MybatisLanguageDriverAutoConfiguration.class
        })
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

//    @MockBean
//    private ProductService productService;
//
//    @MockBean
//    private ProductConverter productConverter;
//
//    @Test
//    void getProductById_whenProductExists_shouldReturnProduct() throws Exception {
//        String productId = "pd0tt0001";
//        ProductDto dto = new ProductDto();
//        dto.setId(productId);
//        dto.setName("Test Product");
//        ProductVo vo = new ProductVo();
//        vo.setId(productId);
//        vo.setName("Test Product");
//
//        when(productService.findById(productId)).thenReturn(dto);
//        when(productConverter.toVo(dto)).thenReturn(vo);
//
//        mockMvc.perform(get("/products/{id}", productId))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.code", is(ErrorCode.SUCCESS.getHttpStatus())))
//                .andExpect(jsonPath("$.data.id", is(1)))
//                .andExpect(jsonPath("$.data.name", is("Test Product")));
//    }
//
//    @Test
//    void getProductById_whenProductNotFound_shouldReturnNotFound() throws Exception {
//    	String productId = "pd0tt0001";
//        when(productService.findById(productId)).thenThrow(new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
//
//        mockMvc.perform(get("/products/{id}", productId))
//                .andExpect(status().isNotFound()) // 404 is a special case
//                .andExpect(jsonPath("$.code", is(ErrorCode.PRODUCT_NOT_FOUND.getHttpStatus())));
//    }
//    
//    @Test
//    void getProductById_whenIdIsInvalid_shouldReturnBadRequest() throws Exception {
//        // This tests javax.validation.constraints.Min on the path variable, 
//        // which is handled by Spring's default ConstraintViolationException handler (returns HTTP 400)
//        // and not our custom GlobalExceptionHandler's BusinessException handler.
//        mockMvc.perform(get("/products/{id}", -1L))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void getAllProducts_whenProductsExist_shouldReturnProductList() throws Exception {
//        ProductDto dto = new ProductDto();
//        String productId = "pd0tt0001";
//        dto.setId(productId);
//        ProductVo vo = new ProductVo();
//        vo.setId(productId);
//        List<ProductDto> dtoList = Collections.singletonList(dto);
//
//        when(productService.findAll()).thenReturn(dtoList);
//        when(productConverter.toVo(any(ProductDto.class))).thenReturn(vo);
//
//        mockMvc.perform(get("/products"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data", hasSize(1)))
//                .andExpect(jsonPath("$.data[0].id", is(1)));
//    }
//
//    @Test
//    void getAllProducts_whenNoProducts_shouldReturnEmptyList() throws Exception {
//        when(productService.findAll()).thenReturn(Collections.emptyList());
//
//        mockMvc.perform(get("/products"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data", is(empty())));
//    }
//
//    @Test
//    void createProduct_whenValidInput_shouldReturnCreated() throws Exception {
//        ProductDto inputDto = new ProductDto();
//        inputDto.setName("New Product");
//        inputDto.setPrice(BigDecimal.valueOf(100.0));
//        String productId = "pd0tt0001";
//        ProductDto savedDto = new ProductDto();
//        savedDto.setId(productId);
//
//        ProductVo vo = new ProductVo();
//        vo.setId(productId);
//
//        when(productService.save(any(ProductDto.class))).thenReturn(savedDto);
//        when(productConverter.toVo(savedDto)).thenReturn(vo);
//
//        mockMvc.perform(post("/products")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(inputDto)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.data.id", is(1)));
//    }
//
//    @Test
//    void createProduct_whenNameIsNull_shouldReturnOkWithErrorCode() throws Exception {
//        ProductDto inputDto = new ProductDto();
//        inputDto.setPrice(BigDecimal.valueOf(10.0));
//
//        // The exception handler now returns HTTP 200 OK for validation errors
//        mockMvc.perform(post("/products")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(inputDto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code", is(ErrorCode.VALIDATION_ERROR.getHttpStatus())));
//    }
//    
//    @Test
//    void createProduct_whenPriceIsNegative_shouldReturnOkWithErrorCode() throws Exception {
//        ProductDto inputDto = new ProductDto();
//        inputDto.setName("Invalid Product");
//        inputDto.setPrice(BigDecimal.valueOf(-1.0));
//
//        // The exception handler now returns HTTP 200 OK for validation errors
//        mockMvc.perform(post("/products")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(inputDto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code", is(ErrorCode.VALIDATION_ERROR.getHttpStatus())));
//    }
//
//    @Test
//    void updateProduct_whenProductExists_shouldReturnOk() throws Exception {
//    	String productId = "pd0tt0001";
//        ProductDto inputDto = new ProductDto();
//        inputDto.setId(productId);
//        inputDto.setName("Updated Name");
//        inputDto.setPrice(BigDecimal.valueOf(120.0));
//        
//        ProductDto updatedDto = new ProductDto();
//        updatedDto.setId(productId);
//        
//        ProductVo vo = new ProductVo();
//        vo.setId(productId);
//
//        when(productService.save(any(ProductDto.class))).thenReturn(updatedDto);
//        when(productConverter.toVo(updatedDto)).thenReturn(vo);
//
//        mockMvc.perform(put("/products/{id}", productId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(inputDto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.id", is(1)));
//    }
//    
//    @Test
//    void updateProduct_whenIdMismatch_shouldReturnOkWithErrorCode() throws Exception {
//        ProductDto dto = new ProductDto();
//        String productId = "pd0tt0001";
//        dto.setId(productId); // Mismatch ID
//
//        // The exception handler now returns HTTP 200 OK for validation errors
//        mockMvc.perform(put("/products/{id}", 1L)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(dto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code", is(ErrorCode.VALIDATION_ERROR.getHttpStatus())));
//    }
//
//    @Test
//    void updateProduct_whenProductNotFound_shouldReturnNotFound() throws Exception {
//    	String productId = "pd0tt0001";
//        ProductDto inputDto = new ProductDto();
//        inputDto.setId(productId);
//        inputDto.setName("Non-existent Product");
//
//        when(productService.save(any(ProductDto.class))).thenThrow(new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
//
//        mockMvc.perform(put("/products/{id}", productId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(inputDto)))
//                .andExpect(status().isNotFound()); // 404 is a special case
//    }
//
//    @Test
//    void deleteProduct_whenProductExists_shouldReturnNoContent() throws Exception {
//    	String productId = "pd0tt0001";
//        doNothing().when(productService).deleteById(productId);
//
//        mockMvc.perform(delete("/products/{id}", productId))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    void deleteProduct_whenProductNotFound_shouldReturnNotFound() throws Exception {
//    	String productId = "pd0tt0001";
//        doThrow(new BusinessException(ErrorCode.PRODUCT_NOT_FOUND)).when(productService).deleteById(productId);
//
//        mockMvc.perform(delete("/products/{id}", productId))
//                .andExpect(status().isNotFound()); // 404 is a special case
//    }
}
