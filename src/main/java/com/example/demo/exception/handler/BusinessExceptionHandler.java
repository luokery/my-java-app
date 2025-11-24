package com.example.demo.exception.handler;

import org.springframework.stereotype.Component;

import com.example.demo.design.policymodel.ExceptionHandlerPolicyModel;
import com.example.demo.exception.BusinessException;
import com.example.demo.model.vo.ResultVo;

/**
 * 策略模型: 业务异常处理
 * @author 
 *
 */
@Component
public class BusinessExceptionHandler implements ExceptionHandlerPolicyModel<BusinessException>{
	
	@Override
	public Class<BusinessException> sequence() {
		return BusinessException.class;
	}

	@Override
	public ResultVo handler(BusinessException exception) {
		return ResultVo.error(exception.getResultFace().code(), exception.getMessage());
	}
}
