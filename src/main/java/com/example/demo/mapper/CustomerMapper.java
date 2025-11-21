package com.example.demo.mapper;

import com.example.demo.model.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {

    List<Customer> findAll();

    Customer findById(String id);

    int insert(Customer customer);

    int update(Customer customer);

    int deleteById(String id);
}
