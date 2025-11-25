package com.example.demo.model.vo;

import lombok.Data;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;

@Data
public class ProductVO {
	
    private String id;
    
    private String name;
    
    @Positive(message = "price must be a positive number")
    private BigDecimal price;
}
