package com.example.demo.service;

import com.example.demo.model.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> findAll();

    CustomerDto findById(String id);

    CustomerDto save(CustomerDto customerDto);

    void deleteById(String id);
}
