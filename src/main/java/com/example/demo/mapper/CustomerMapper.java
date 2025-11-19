package com.example.demo.mapper;

import com.example.demo.model.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {

    List<Customer> findAll();

    Customer findById(Long id);

    int insert(Customer customer);

    int update(Customer customer);

    int deleteById(Long id);
}
