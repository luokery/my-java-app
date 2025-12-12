package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface ICommonEntity {
	
	// 获取NO
	@JsonIgnore
	public String getNo();
	
	@JsonIgnore
	public void setNo(String string);
}
