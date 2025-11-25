package com.example.demo.model.vo;

import java.util.Objects;

public class ResponseVO<T> {
	
	/**
	 * 流水号: 请求发送的流水号, 与返回的流水号对应
	 */
	private String serialNumber;
	
	/**
	 * 令牌: 身份令牌
	 */
	private String token;
	
	/**
	 * 来源: 公司, 应用(配合密钥处理)
	 */
	private String source;
	
	/**
	 * 请求版本: 匹配服务版本
	 */
	private String version;
	
	/**
	 * 响应代码: 0是成功, 非0失败, 为具体错误代码
	 */
	private String code;
	
	/**
	 * 响应消息: 消息
	 */
	private String message;
	
	/**
	 * 响应的数据: 对应的VO
	 */
	private T data;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCode(Integer code) {
		this.code = String.valueOf( code);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return String.format(
				"ResponseVO [serialNumber=%s, token=%s, source=%s, version=%s, code=%s, message=%s, data=%s]",
				serialNumber, token, source, version, code, message, data);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, data, message, serialNumber, source, token, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseVO other = (ResponseVO) obj;
		return Objects.equals(code, other.code) && Objects.equals(data, other.data)
				&& Objects.equals(message, other.message) && Objects.equals(serialNumber, other.serialNumber)
				&& Objects.equals(source, other.source) && Objects.equals(token, other.token)
				&& Objects.equals(version, other.version);
	}
}
