package com.example.demo.exception.handler;

import org.springframework.stereotype.Component;

import com.example.demo.design.policymodel.ExceptionHandlerPolicyModel;
import com.example.demo.exception.CustomerException;
import com.example.demo.model.vo.ResponseVO;
import com.example.demo.model.vo.Result;


/**
 * 策略模型: 客户异常处理
 * @author 
 *
 */
@Component
public class CustomerExceptionHandler implements ExceptionHandlerPolicyModel<CustomerException>{

	@Override
	public Class<CustomerException> sequence() {
		return CustomerException.class;
	}
	
	@Override
	public ResponseVO<CustomerException> handler(CustomerException exception) {
		return Result.build(exception.getResultFace(), null);
	}
}
