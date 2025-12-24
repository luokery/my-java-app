package com.example.demo.model.entity;

import java.util.Objects;

/**
 * 用户表
 * 
 * @author 
 *
 */
public class User extends CommonEntity implements ICommonEntity {

	/**
	 * 用户编号
	 */
//	@Column
	private String userNo;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 用户昵称
	 */
	private String userNickName;
	/**
	 * 用户密码
	 */
	private String userPassword;
	/**
	 * 盐巴
	 */
	private String salt;

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(salt, userName, userNickName, userNo, userPassword);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(salt, other.salt) && Objects.equals(userName, other.userName)
				&& Objects.equals(userNickName, other.userNickName) && Objects.equals(userNo, other.userNo)
				&& Objects.equals(userPassword, other.userPassword);
	}

	@Override
	public String toString() {
		return String.format("User [userNo=%s, userName=%s, userNickName=%s, userPassword=%s, salt=%s]", userNo,
				userName, userNickName, userPassword, salt);
	}

	@Override
	public String getNo() {
		return userNo;
	}

	@Override
	public void setNo(String no) {
		userNo = no;
	}
}
