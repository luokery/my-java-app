package com.example.demo.exception;

import java.util.Map;

import com.example.demo.cosnst.ResultEnum;
import com.example.demo.cosnst.ResultFace;

import lombok.Getter;

/**
 * A custom base exception for business logic errors.
 * It carries an ErrorCode enum to standardize error responses.
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer code;

	private Object data;
	
	public BusinessException(Integer code, String message) {
		super(message);
		this.code = code;
	}
	
    public BusinessException(ResultFace resultFace) {
        this(resultFace.code(), resultFace.message());
    }
    
	public BusinessException(ResultEnum resultFace, Object obj) {
		this(resultFace.code(), resultFace.message());
		this.data = obj;
	}

	public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
