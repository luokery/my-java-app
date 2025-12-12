package com.example.demo.model.entity;

import java.util.Date;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 公共字段域
 * 所有数据库的公共字段
 * @author 
 * @date 2018/1/28
 */
public class CommonEntity {
	
	/**
	 * 记录状态
	 */
	private String recStatus;
	
	/**
	 * 创建时间
	 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date crtTime;

	/**
	 * 创建人
	 */
	private String crtUser;
	
	/**
	 * 最后更新时间
	 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lstUpdTime;
	
	/**
	 * 最后更新用户
	 */
	private String lstUpdUser;

	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public Date getLstUpdTime() {
		return lstUpdTime;
	}

	public void setLstUpdTime(Date lstUpdTime) {
		this.lstUpdTime = lstUpdTime;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	@Override
	public int hashCode() {
		return Objects.hash(crtTime, crtUser, lstUpdTime, lstUpdUser, recStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommonEntity other = (CommonEntity) obj;
		return Objects.equals(crtTime, other.crtTime) && Objects.equals(crtUser, other.crtUser)
				&& Objects.equals(lstUpdTime, other.lstUpdTime) && Objects.equals(lstUpdUser, other.lstUpdUser)
				&& Objects.equals(recStatus, other.recStatus);
	}

	@Override
	public String toString() {
		return String.format("CommonEntity [recStatus=%s, crtTime=%s, crtUser=%s, lstUpdTime=%s, lstUpdUser=%s]",
				recStatus, crtTime, crtUser, lstUpdTime, lstUpdUser);
	}
}

