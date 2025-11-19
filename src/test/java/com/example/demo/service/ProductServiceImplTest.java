package com.example.demo.service;

import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void findById_whenProductExists_shouldReturnProduct() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        when(productMapper.findById(1L)).thenReturn(product);

        // Act
        Product foundProduct = productService.findById(1L);

        // Assert
        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
    }

    @Test
    void findById_whenProductNotExists_shouldReturnNull() {
        // Arrange
        when(productMapper.findById(1L)).thenReturn(null);

        // Act
        Product foundProduct = productService.findById(1L);

        // Assert
        assertNull(foundProduct);
    }

    @Test
    void save_shouldSaveAndReturnProduct() {
        // Arrange
        Product productToSave = new Product();
        productToSave.setName("New Product");
        productToSave.setPrice(new BigDecimal("10.00"));
        productToSave.setStock(100);
        productToSave.setCreateTime(LocalDateTime.now());

        when(productMapper.save(any(Product.class))).thenReturn(1);

        // Act
        Product savedProduct = productService.save(productToSave);

        // Assert
        assertNotNull(savedProduct);
        assertEquals("New Product", savedProduct.getName());
        verify(productMapper, times(1)).save(productToSave);
    }
}
