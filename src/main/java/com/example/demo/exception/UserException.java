package com.example.demo.exception;

import com.example.demo.cosnst.UserEnum;

/**
 * 用户异常
 * @author 
 *
 */
public class UserException extends BusinessException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    public UserException(Integer code, String msg) {
        super(code, msg);
    }
    
    public UserException(UserEnum UserEnum) {
        super(UserEnum.getCode(), UserEnum.getMsg());
    }
}
