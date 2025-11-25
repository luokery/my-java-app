package com.example.demo.exception.handler;

import org.springframework.stereotype.Component;

import com.example.demo.design.policymodel.ExceptionHandlerPolicyModel;
import com.example.demo.exception.BusinessException;
import com.example.demo.model.vo.ResponseVO;
import com.example.demo.model.vo.Result;

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
	public ResponseVO<BusinessException> handler(BusinessException exception) {
		return Result.build(exception.getResultFace(), null);
	}
}
