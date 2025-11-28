package com.example.demo.exception.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.demo.cosnst.ResultEnum;
import com.example.demo.design.policymodel.ExceptionHandlerPolicyModel;
import com.example.demo.model.vo.ErrorVO;
import com.example.demo.model.vo.ResponseVO;
import com.example.demo.model.vo.Result;

/**
 * 策略模型: 参数验证异常处理
 * @author 
 *
 */
@Component
public class MethodArgumentNotValidExceptionHandler implements ExceptionHandlerPolicyModel<MethodArgumentNotValidException>{

	@Override
	public Class<MethodArgumentNotValidException> sequence() {
		return MethodArgumentNotValidException.class;
	}

	@Override
	public ResponseVO<List<ErrorVO>> handler(MethodArgumentNotValidException exception) {
		
		List<FieldError> errors = exception.getBindingResult().getFieldErrors();
		List<ErrorVO> resultErrors = new ArrayList<ErrorVO>(errors.size());
		for(int nA = 0; nA < errors.size(); nA ++) {
			FieldError error = errors.get(nA);
			ErrorVO errorItem = null;
			String field = StringUtils.substringAfterLast(error.getField(), ".");
			if(StringUtils.isNotBlank(field)) {
				errorItem = new ErrorVO( field, error.getDefaultMessage());
			}else {
				errorItem = new ErrorVO( error.getField(), error.getDefaultMessage());
			}
			resultErrors.add( errorItem);
		}
		return Result.build(ResultEnum.PARAM_VERIFY_ERROR, resultErrors);
	}
}
