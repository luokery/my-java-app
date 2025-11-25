package com.example.demo.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CustomerDTO {

    private String id;
    private String username;
    private String nickname;
    private String email;
    private String password;
    private String gender;
    private LocalDate birthDate;
    private String status;
    private LocalDateTime createTime;
}
