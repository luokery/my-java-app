package com.example.demo.service.api;

import com.example.demo.model.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> findAll();

    CustomerDTO findById(String id);

    CustomerDTO save(CustomerDTO customerDto);

    void deleteById(String id);
}
