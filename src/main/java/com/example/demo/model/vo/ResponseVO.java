package com.example.demo.model.vo;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "响应VO", description = "响应VO")
public class ResponseVO<T> extends RequestVO<T> {
	
	/**
	 * 响应代码: 0是成功, 非0失败, 为具体错误代码
	 */
	@Schema(title = "响应代码", description = "响应代码: 0是成功, 非0失败, 为具体错误代码")
	private String code;
	
	/**
	 * 响应消息: 消息
	 */
	@Schema(title = "响应消息", description = "响应消息: 消息")
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(code, message);
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
		ResponseVO<?> other = (ResponseVO<?>) obj;
		return Objects.equals(code, other.code) && Objects.equals(message, other.message);
	}

	@Override
	public String toString() {
		return String.format("ResponseVO [code=%s, message=%s, toString()=%s]", code, message, super.toString());
	}
}
