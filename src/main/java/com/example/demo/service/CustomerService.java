package com.example.demo.service;

import com.example.demo.model.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> findAll();

    CustomerDto findById(Long id);

    CustomerDto save(CustomerDto customerDto);

    void deleteById(Long id);
}
