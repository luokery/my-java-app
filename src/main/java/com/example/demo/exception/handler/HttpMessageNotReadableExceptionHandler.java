package com.example.demo.exception.handler;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;

import com.example.demo.cosnst.ResultEnum;
import com.example.demo.design.policymodel.ExceptionHandlerPolicyModel;
import com.example.demo.model.vo.ResponseVO;
import com.example.demo.model.vo.Result;

/**
 * 策略模型: 消息结构异常处理
 * @author 
 *
 */
@Component
public class HttpMessageNotReadableExceptionHandler implements ExceptionHandlerPolicyModel<HttpMessageNotReadableException> {

	@Override
	public Class<HttpMessageNotReadableException> sequence() {
		return HttpMessageNotReadableException.class;
	}

	@Override
	public ResponseVO handler(HttpMessageNotReadableException exception) {
		return Result.build(ResultEnum.SPRING_MASSAGE_ERROR, null);
	}
}
