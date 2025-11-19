package com.example.demo.service;

import com.example.demo.converter.ProductConverter;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.model.entity.Product;
import com.example.demo.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductConverter productConverter;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void findById_whenProductExists_shouldReturnProductDto() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Test Product");

        when(productMapper.findById(1L)).thenReturn(product);
        when(productConverter.toDto(product)).thenReturn(productDto);

        // Act
        ProductDto foundProductDto = productService.findById(1L);

        // Assert
        assertNotNull(foundProductDto);
        assertEquals(1L, foundProductDto.getId());
    }

    @Test
    void findById_whenProductNotExists_shouldReturnNull() {
        // Arrange
        when(productMapper.findById(1L)).thenReturn(null);

        // Act
        ProductDto foundProductDto = productService.findById(1L);

        // Assert
        assertNull(foundProductDto);
    }

    @Test
    void save_shouldSaveAndReturnProductDto() {
        // Arrange
        ProductDto productDtoToSave = new ProductDto();
        productDtoToSave.setName("New Product");

        Product productToSave = new Product();
        productToSave.setName("New Product");

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName("New Product");

        ProductDto savedProductDto = new ProductDto();
        savedProductDto.setId(1L);
        savedProductDto.setName("New Product");

        when(productConverter.toEntity(productDtoToSave)).thenReturn(productToSave);
        when(productMapper.save(any(Product.class))).thenReturn(1);
        when(productConverter.toDto(any(Product.class))).thenReturn(savedProductDto);

        // Act
        ProductDto result = productService.save(productDtoToSave);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New Product", result.getName());
        verify(productMapper, times(1)).save(productToSave);
    }
}
