package com.example.demo.model.vo.product;

import lombok.Data;

import java.math.BigDecimal;


import com.example.demo.validation.Groups;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Data
public class ProductVO {
	
	@NotBlank(groups = {Groups.Delete.class, Groups.Update.class}, message = "编号不能为空")
    private String id;
	
	@NotBlank(groups = {Groups.Create.class, Groups.Update.class}, message = "{product.name.notBlank}")
	@Pattern(groups = {Groups.Create.class, Groups.Update.class}, regexp="^[a-zA-Z0-9\u4e00-\u9fa5.]+$", message="{product.name.pattern}")
	@Size(min=2, max=50, message="{product.name.size}")
    private String name;
    
    @Positive(message = "price must be a positive number")
    private BigDecimal price;
}
