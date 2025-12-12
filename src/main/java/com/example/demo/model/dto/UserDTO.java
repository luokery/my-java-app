package com.example.demo.model.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends CommonDTO {
	/**
	 * 用户编号
	 */
	private String  userNo;
	/**
	 * 用户名称
	 */
	private String  userName;
	/**
	 * 用户昵称
	 */
	private String  userNickName;
	/**
	 * 用户密码
	 */
	private String  userPassword;
	/**
	 * 盐巴
	 */
	private String  salt;
}
