package com.example.demo.service;

import com.example.demo.converter.ProductConverter;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;
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
        long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        ProductDto productDto = new ProductDto();
        productDto.setId(productId);

        when(productMapper.findById(productId)).thenReturn(product);
        when(productConverter.toDto(product)).thenReturn(productDto);

        // Act
        ProductDto foundProductDto = productService.findById(productId);

        // Assert
        assertNotNull(foundProductDto);
        assertEquals(productId, foundProductDto.getId());
    }

    @Test
    void findById_whenProductNotExists_shouldThrowBusinessException() {
        // Arrange
        long productId = 1L;
        when(productMapper.findById(productId)).thenReturn(null);

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productService.findById(productId);
        });

        assertEquals(ErrorCode.PRODUCT_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    void save_whenCreatingNewProduct_shouldSaveAndReturnDto() {
        // Arrange
        ProductDto dtoToSave = new ProductDto();
        dtoToSave.setName("New Product");

        Product entityToSave = new Product();

        Product savedEntity = new Product();
        savedEntity.setId(1L);

        ProductDto expectedDto = new ProductDto();
        expectedDto.setId(1L);

        when(productConverter.toEntity(dtoToSave)).thenReturn(entityToSave);
        // Note: mybatis save method might not return a value, but the id is set on the object.
        // We'll mock the converter to simulate this.
        when(productConverter.toDto(entityToSave)).thenReturn(expectedDto);

        // Act
        ProductDto result = productService.save(dtoToSave);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(productMapper, times(1)).save(entityToSave);
    }

    @Test
    void save_whenUpdatingNonExistentProduct_shouldThrowBusinessException() {
        // Arrange
        long productId = 1L;
        ProductDto dtoToUpdate = new ProductDto();
        dtoToUpdate.setId(productId);
        dtoToUpdate.setName("Updated Name");

        when(productMapper.findById(productId)).thenReturn(null);

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productService.save(dtoToUpdate);
        });

        assertEquals(ErrorCode.PRODUCT_NOT_FOUND, exception.getErrorCode());
        verify(productMapper, never()).save(any());
    }

    @Test
    void deleteById_whenProductExists_shouldDeleteProduct() {
        // Arrange
        long productId = 1L;
        Product existingProduct = new Product();
        existingProduct.setId(productId);
        when(productMapper.findById(productId)).thenReturn(existingProduct);

        // Act
        productService.deleteById(productId);

        // Assert
        verify(productMapper, times(1)).deleteById(productId);
    }

    @Test
    void deleteById_whenProductNotExists_shouldThrowBusinessException() {
        // Arrange
        long productId = 1L;
        when(productMapper.findById(productId)).thenReturn(null);

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productService.deleteById(productId);
        });

        assertEquals(ErrorCode.PRODUCT_NOT_FOUND, exception.getErrorCode());
        verify(productMapper, never()).deleteById(anyLong());
    }
}
