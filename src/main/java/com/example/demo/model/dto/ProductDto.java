package com.example.demo.model.dto;

import com.example.demo.controller.dto.Groups;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDto {

    @Null(groups = Groups.Create.class, message = "ID must be null during creation")
    @NotNull(groups = Groups.Update.class, message = "ID is required for updates")
    private Long id;

    @NotBlank(groups = {Groups.Create.class, Groups.Update.class}, message = "{product.name.notBlank}")
    @Size(min = 3, max = 50, groups = {Groups.Create.class, Groups.Update.class}, message = "{product.name.size}")
    private String name;

    @NotNull(groups = Groups.Create.class)
    @Positive(groups = {Groups.Create.class, Groups.Update.class}, message = "Price must be a positive value")
    private BigDecimal price;

    @NotNull(groups = Groups.Create.class)
    @Min(value = 0, groups = {Groups.Create.class, Groups.Update.class}, message = "Stock cannot be negative")
    private Integer stock;

    private LocalDateTime createTime;
}
