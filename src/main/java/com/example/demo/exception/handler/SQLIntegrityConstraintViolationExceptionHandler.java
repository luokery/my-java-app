package com.example.demo.exception.handler;

import java.sql.SQLIntegrityConstraintViolationException;

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
public class SQLIntegrityConstraintViolationExceptionHandler implements ExceptionHandlerPolicyModel<SQLIntegrityConstraintViolationException> {

	@Override
	public Class<SQLIntegrityConstraintViolationException> sequence() {
		return SQLIntegrityConstraintViolationException.class;
	}

	@Override
	public ResponseVO<?> handler(SQLIntegrityConstraintViolationException exception) {
		return Result.build(ResultEnum.SQL_Integrity_Constraint_Violation_ERROR, null);
	}

}
