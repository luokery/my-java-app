package com.example.demo.model.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Data
@Schema(description = "产品实体类")
public class Product {
	@Schema(description = "产品ID", example = "1001", required = false, requiredMode = RequiredMode.REQUIRED)
    private String id;
	@Schema(description = "产品名称", example = "coco手机", required = true, requiredMode = RequiredMode.REQUIRED)
    private String name;
	@Schema(description = "价格", example = "10.05", required = true, requiredMode = RequiredMode.REQUIRED)
    private BigDecimal price;
    @Schema(description = "库存", example = "0")
    private Integer stock;
    @Schema(description = "创建时间", example = "1993年06月16日 19:18:25")
    private LocalDateTime createTime;
}
