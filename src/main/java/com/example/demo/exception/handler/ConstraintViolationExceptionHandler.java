package com.example.demo.exception.handler;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import com.example.demo.cosnst.ResultEnum;
import com.example.demo.design.policymodel.ExceptionHandlerPolicyModel;
import com.example.demo.model.vo.ErrorVO;
import com.example.demo.model.vo.ResponseVO;
import com.example.demo.model.vo.Result;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

/**
 * 策略模型: 验证异常处理
 * @author 
 *
 */
@Component
public class ConstraintViolationExceptionHandler implements ExceptionHandlerPolicyModel<ConstraintViolationException> {

	@Override
	public Class<ConstraintViolationException> sequence() {
		return ConstraintViolationException.class;
	}

	@Override
	public ResponseVO<?> handler(ConstraintViolationException exception) {
		
		List<ErrorVO> resultErrors = new ArrayList<ErrorVO>();
        StringBuilder stringBuilder = new StringBuilder();
        for (ConstraintViolation<?> error : exception.getConstraintViolations()) {
        	
            PathImpl pathImpl = (PathImpl) error.getPropertyPath();
            String paramName = pathImpl.getLeafNode().getName();
            stringBuilder.append("[");
            stringBuilder.append(paramName);
            stringBuilder.append(" ");
            stringBuilder.append(error.getMessage());
            stringBuilder.append("]");
            
			ErrorVO errorItem = new ErrorVO( paramName, error.getMessage());
			resultErrors.add( errorItem);
        }
        return Result.build(ResultEnum.PARAM_VERIFY_ERROR, resultErrors);
	}
}
