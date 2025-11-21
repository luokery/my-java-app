package com.example.demo.converter;

import com.example.demo.model.dto.CustomerDto;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.vo.CustomerVo;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerConverter extends IMapstruct<CustomerVo, CustomerDto, Customer> {

    CustomerConverter INSTANCE = Mappers.getMapper(CustomerConverter.class);

}
