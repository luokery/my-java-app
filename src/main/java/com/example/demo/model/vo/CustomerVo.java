package com.example.demo.model.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerVo {

    private Long id;

    @NotEmpty(message = "{customer.username.notEmpty}")
    @Size(min = 8, max = 25, message = "{customer.username.size}")
    private String username;

    @Size(min = 8, max = 50, message = "{customer.nickname.size}")
    private String nickname;

    @NotEmpty(message = "{customer.email.notEmpty}")
    @Email(message = "{customer.email.format}")
    private String email;

    private String password;

    private String gender;

    private Date birthDate;

    private String status;

    private Date createdAt;
}
