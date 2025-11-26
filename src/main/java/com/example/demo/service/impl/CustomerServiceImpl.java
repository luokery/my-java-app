package com.example.demo.service.impl;

import com.example.demo.converter.CustomerConverter;
import com.example.demo.cosnst.CustomerEnum;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.model.dto.CustomerDTO;
import com.example.demo.model.entity.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerMapper.findAll().stream()
                .map(CustomerConverter.INSTANCE::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findById(String id) {
        Customer customer = customerMapper.findById(id);
        if (customer == null) {
            throw new BusinessException(CustomerEnum.CUSTOMER_NOT_FOUND, "Customer with id " + id + " not found");
        }
        return CustomerConverter.INSTANCE.entityToDTO(customer);
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDto) {
        Customer customer = CustomerConverter.INSTANCE.toEntity(customerDto);
        if (customer.getId() != null) { // Update
            Customer existingCustomer = customerMapper.findById(customer.getId());
            if (existingCustomer == null) {
                throw new BusinessException(CustomerEnum.CUSTOMER_NOT_FOUND, "Customer with id " + customer.getId() + " not found");
            }
            customerMapper.update(customer);
        } else { // Create
            customerMapper.insert(customer);
        }
        return CustomerConverter.INSTANCE.entityToDTO(customer);
    }

    @Override
    public void deleteById(String id) {
        Customer existingCustomer = customerMapper.findById(id);
        if (existingCustomer == null) {
            throw new BusinessException(CustomerEnum.CUSTOMER_NOT_FOUND, "Customer with id " + id + " not found");
        }
        customerMapper.deleteById(id);
    }
}
