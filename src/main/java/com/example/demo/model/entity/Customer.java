package com.example.demo.model.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Data
@Schema(description = "客户实体类")
public class Customer {

	@Schema(description = "客户ID", example = "1001", required = false, requiredMode = RequiredMode.REQUIRED)
    private String id;
	@Schema(description = "客户名", example = "coco", required = true, requiredMode = RequiredMode.REQUIRED)
    private String username;
	@Schema(description = "客户昵称", example = "张三, coco", required = true)
    private String nickname;
	@Schema(description = "邮箱", example = "demo@example.com", hidden = true)
    private String email;
	@Schema(description = "密码", example = "xxx")
    private String password;
	@Schema(description = "性别", example = "男,女")
    private String gender;
	@Schema(description = "生日", example = "1993年06月16日")
    private LocalDate birthDate;
	@Schema(description = "状态", example = "1000,1001,1002")
    private String status;
    @Schema(description = "创建时间", example = "1993年06月16日 19:18:25")
    private LocalDateTime createTime;
}
