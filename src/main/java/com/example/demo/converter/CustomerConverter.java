package com.example.demo.converter;

import com.example.demo.model.dto.CustomerDTO;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.vo.customer.CustomerVO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerConverter extends IMapstruct<CustomerVO, CustomerDTO, Customer> {

    CustomerConverter INSTANCE = Mappers.getMapper(CustomerConverter.class);

}
