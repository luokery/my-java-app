package com.example.demo.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.User;
import com.example.demo.model.vo.user.UserVO;

@Mapper(componentModel = "spring")
public interface UserConverter extends IMapstruct<UserVO, UserDTO, User> {
	public UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);
	
}
