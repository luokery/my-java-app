package com.example.demo.model.vo;

import java.util.Objects;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public class RequestVO<T> {
	
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
	 * 请求的数据: 对应的DTO
	 */
	// @NotEmpty(message = "请求数据不能为空") // 不能为null, 集合size不能为0
	@Valid
	@NotNull(message = "请求数据不能为空")
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return String.format("RequestVO [serialNumber=%s, token=%s, source=%s, version=%s, data=%s]", serialNumber,
				token, source, version, data);
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, serialNumber, source, token, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestVO other = (RequestVO) obj;
		return Objects.equals(data, other.data) && Objects.equals(serialNumber, other.serialNumber)
				&& Objects.equals(source, other.source) && Objects.equals(token, other.token)
				&& Objects.equals(version, other.version);
	}
}
