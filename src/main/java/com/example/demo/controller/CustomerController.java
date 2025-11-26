package com.example.demo.controller;

import com.example.demo.converter.CustomerConverter;
import com.example.demo.model.dto.CustomerDTO;
import com.example.demo.model.vo.ResponseVO;
import com.example.demo.model.vo.Result;
import com.example.demo.model.vo.customer.CustomerVO;
import com.example.demo.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerConverter customerConverter;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerConverter customerConverter) {
        this.customerService = customerService;
        this.customerConverter = customerConverter;
    }

    @GetMapping
    public ResponseVO<List<CustomerVO>> getAllCustomers() {
        List<CustomerDTO> customerDtos = customerService.findAll();
        List<CustomerVO> customerVos = customerDtos.stream()
                .map(customerConverter::toVO)
                .collect(Collectors.toList());
        return Result.success(customerVos);
    }

    @GetMapping("/{id}")
    public ResponseVO<CustomerVO> getCustomerById(@PathVariable String id) {
        CustomerDTO customerDto = customerService.findById(id);
        CustomerVO customerVo = customerConverter.toVO(customerDto);
        return Result.success(customerVo);
    }

    @PostMapping
    public ResponseVO<CustomerVO> createCustomer(@Valid @RequestBody CustomerVO customerVo, BindingResult bindingResult) {
        CustomerDTO customerDto = customerConverter.toDTO(customerVo);
        CustomerDTO savedCustomer = customerService.save(customerDto);
        CustomerVO savedCustomerVo = customerConverter.toVO(savedCustomer);
        return Result.success(savedCustomerVo);
    }

    @PutMapping("/{id}")
    public ResponseVO<CustomerVO> updateCustomer(@PathVariable String id, @Valid @RequestBody CustomerVO customerVo, BindingResult bindingResult) {
        CustomerDTO customerDto = customerConverter.toDTO(customerVo);
        customerDto.setId(id);
        CustomerDTO updatedCustomer = customerService.save(customerDto);
        CustomerVO updatedCustomerVo = customerConverter.toVO(updatedCustomer);
        return Result.success(updatedCustomerVo);
    }

    @DeleteMapping("/{id}")
    public ResponseVO<Void> deleteCustomer(@PathVariable String id) {
        customerService.deleteById(id);
        return Result.success();
    }
}
