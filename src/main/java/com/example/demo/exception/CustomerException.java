package com.example.demo.exception;

import com.example.demo.cosnst.ResultFace;

import lombok.Getter;

/**
 * A custom base exception for business logic errors.
 * It carries an ErrorCode enum to standardize error responses.
 */
@Getter
public class CustomerException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final ResultFace resultFace;
    private final transient Object data; // Optional data payload

    public CustomerException(ResultFace resultFace) {
        super(resultFace.message());
        this.resultFace = resultFace;
        this.data = null;
    }

    public CustomerException(ResultFace resultFace, String message) {
        super(message);
        this.resultFace = resultFace;
        this.data = null;
    }

    public CustomerException(ResultFace resultFace, Object data) {
        super(resultFace.message());
        this.resultFace = resultFace;
        this.data = data;
    }
}
