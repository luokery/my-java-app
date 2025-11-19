package com.example.demo.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDto {

    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String password;
    private String gender;
    private Date birthDate;
    private String status;
    private Date createdAt;
}
