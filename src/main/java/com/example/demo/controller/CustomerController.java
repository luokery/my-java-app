package com.example.demo.controller;

import com.example.demo.converter.CustomerConverter;
import com.example.demo.model.dto.CustomerDto;
import com.example.demo.model.vo.CustomerVo;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CustomerVo>> getAllCustomers() {
        List<CustomerDto> customerDtos = customerService.findAll();
        List<CustomerVo> customerVos = customerDtos.stream()
                .map(customerConverter::toVo)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerVos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerVo> getCustomerById(@PathVariable Long id) {
        CustomerDto customerDto = customerService.findById(id);
        CustomerVo customerVo = customerConverter.toVo(customerDto);
        return ResponseEntity.ok(customerVo);
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerVo customerVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        CustomerDto customerDto = customerConverter.toDto(customerVo);
        CustomerDto savedCustomer = customerService.save(customerDto);
        CustomerVo savedCustomerVo = customerConverter.toVo(savedCustomer);
        return new ResponseEntity<>(savedCustomerVo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerVo customerVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        CustomerDto customerDto = customerConverter.toDto(customerVo);
        customerDto.setId(id);
        CustomerDto updatedCustomer = customerService.save(customerDto);
        CustomerVo updatedCustomerVo = customerConverter.toVo(updatedCustomer);
        return ResponseEntity.ok(updatedCustomerVo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
