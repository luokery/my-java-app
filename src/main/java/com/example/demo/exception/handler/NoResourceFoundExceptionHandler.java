package com.example.demo.exception.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.demo.cosnst.ResultEnum;
import com.example.demo.design.policymodel.ExceptionHandlerPolicyModel;
import com.example.demo.model.vo.ResponseVO;
import com.example.demo.model.vo.Result;

/**
 * 策略模型: 资源未找到异常处理
 * @author 
 * FIXME: 没有用?
 */
@Component
public class NoResourceFoundExceptionHandler implements ExceptionHandlerPolicyModel<NoResourceFoundException> {

	@Override
	public Class<NoResourceFoundException> sequence() {
		return NoResourceFoundException.class;
	}

	@Override
	public ResponseVO handler(NoResourceFoundException exception) {
		return Result.build(ResultEnum.SPRING_RESOURCE_NOT_FOUND, null);
	}
}
