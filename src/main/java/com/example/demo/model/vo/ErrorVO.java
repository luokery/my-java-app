package com.example.demo.model.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * 错误响应:
 * @author 
 */
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy)
public class ErrorVO {
	
	/**
	 * 错误发生的字段
	 */
	private String field;
	
	/**
	 * 错误信息
	 */
	private String msg;
	
	public ErrorVO() {
		super();
	}
	public ErrorVO(String field, String msg) {
		super();
		this.field = field;
		this.msg = msg;
	}
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
