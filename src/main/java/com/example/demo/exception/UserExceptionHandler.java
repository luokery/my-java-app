package com.example.demo.exception;

import org.springframework.stereotype.Component;

import com.example.demo.design.policymodel.ExceptionHandlerPolicyModel;
import com.example.demo.model.vo.ResponseVO;
import com.example.demo.model.vo.Result;

/**
 * 策略模型: 用户异常处理
 * @author 
 *
 */
@Component
public class UserExceptionHandler implements ExceptionHandlerPolicyModel<UserException>{

	@Override
	public Class<UserException> sequence() {
		return UserException.class;
	}

	@Override
	public ResponseVO<?> handler(UserException exception) {
		return Result.build(exception.getCode(), exception.getMessage(), null);
	}
}
