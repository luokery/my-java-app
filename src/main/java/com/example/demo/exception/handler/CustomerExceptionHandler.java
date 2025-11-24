package com.example.demo.exception.handler;

import org.springframework.stereotype.Component;

import com.example.demo.design.policymodel.ExceptionHandlerPolicyModel;
import com.example.demo.exception.CustomerException;
import com.example.demo.model.vo.ResultVo;


/**
 * 策略模型: 用户异常处理
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
	public ResultVo handler(CustomerException exception) {
		return ResultVo.error(exception.getResultFace().code(), exception.getMessage());
	}
}
