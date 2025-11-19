package com.example.demo.model.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVo {
    private Long id;
    private String name;
    private BigDecimal price;
}
