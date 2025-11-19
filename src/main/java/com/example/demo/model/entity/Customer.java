package com.example.demo.model.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Customer {

    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String password;
    private String gender;
    private LocalDate birthDate;
    private String status;
    private LocalDateTime createdAt;
}
