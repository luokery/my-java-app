package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void findById_whenProductExists_shouldReturnProduct() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act
        Product foundProduct = productService.findById(1L);

        // Assert
        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
    }

    @Test
    void findById_whenProductNotExists_shouldReturnNull() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

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

        when(productRepository.save(any(Product.class))).thenReturn(productToSave);

        // Act
        Product savedProduct = productService.save(productToSave);

        // Assert
        assertNotNull(savedProduct);
        assertEquals("New Product", savedProduct.getName());
        verify(productRepository, times(1)).save(productToSave);
    }
}
