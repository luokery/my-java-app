package com.example.demo.exception.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.example.demo.cosnst.ResultEnum;
import com.example.demo.design.policymodel.ExceptionHandlerPolicyModel;
import com.example.demo.model.vo.ErrorVO;
import com.example.demo.model.vo.ResponseVO;
import com.example.demo.model.vo.Result;

/**
 * 策略模型: 验证异常处理
 * @author 
 *
 */
@Component
public class BindExceptionHandler implements ExceptionHandlerPolicyModel<BindException>{

	@Override
	public Class<BindException> sequence() {
		return BindException.class;
	}

	@Override
	public ResponseVO<?> handler(BindException exception) {
		
		List<FieldError> errors = exception.getBindingResult().getFieldErrors();
		List<ErrorVO> resultErrors = new ArrayList<ErrorVO>(errors.size());
		for(int nA = 0; nA < errors.size(); nA ++) {
			FieldError error = errors.get(nA);
			
			// modify by jun at 2018-09-16: 当是bean无子bean绑定失败时候, 不需要最后截取错误的字段名称
			String errorField = error.getField();
			
			if(StringUtils.indexOf(errorField, ".") != -1) {
				errorField = StringUtils.substringAfterLast(errorField, ".");
			}
			ErrorVO errorItem = new ErrorVO( errorField, error.getDefaultMessage());
			
			resultErrors.add( errorItem);
		}
		
        StringBuilder stringBuilder = new StringBuilder();
        for (ObjectError error : exception.getAllErrors()) {
            stringBuilder.append("[");
            stringBuilder.append(((FieldError) error).getField());
            stringBuilder.append(" ");
            stringBuilder.append(error.getDefaultMessage());
            stringBuilder.append("]");
        }
		return Result.build(ResultEnum.PARAM_VERIFY_ERROR, resultErrors);
	}
}
