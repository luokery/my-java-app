package com.example.demo.model.vo.user;

import com.example.demo.validation.Groups;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 用户视图
 * @author 
 *
 */
@Data
public class UserVO {
	
	/**
	 * 用户编号
	 */
	@NotBlank(groups = {Groups.Delete.class, Groups.Update.class}, message = "编号不能为空")
	private String  userNo;
	
	/**
	 * 用户名称
	 */
	@NotBlank(groups = {Groups.Create.class, Groups.Update.class}, message = "{user.vo.userName.notBlank}")
	@Pattern(groups = {Groups.Create.class, Groups.Update.class}, regexp="^[a-zA-Z0-9]+$", message="{user.vo.userName.space}")
	@Size(min=2, max=50, message="{user.vo.userName.size}")
	private String  userName;
	/**
	 * 用户昵称
	 */
	@NotBlank(groups = {Groups.Create.class, Groups.Update.class}, message = "用户昵称不能为空")
	private String  userNickName;
	
	/**
	 * 用户密码
	 */
	@NotBlank(groups = {Groups.Create.class}, message = "用户密码不能为空")
	private String  userPassword;
	
	/**
	 * 盐巴
	 */
	private String salt;
	
}
