package com.example.demo.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.User;
import com.example.demo.model.vo.user.UserCreateVO;
import com.example.demo.model.vo.user.UserVO;

@Mapper(componentModel = "spring")
public interface UserConverter extends IMapstruct<UserVO, UserDTO, User> {
	public UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);
	
	/**
	 * UserDTO 转换为 UserCreateVO
	 * @param 
	 * @return
	 */
    UserCreateVO toUserCreateVO(UserDTO userDTO);
    
	/**
	 * Dto list 转换为 Vo list
	 * @param List dto
	 * @return
	 */
    List<UserCreateVO> toUserCreateVO(List<UserDTO> userDTO);

	UserDTO toDTO(UserCreateVO dataVO);
	
}
