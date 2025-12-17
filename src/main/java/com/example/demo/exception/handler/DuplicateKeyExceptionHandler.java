package com.example.demo.exception.handler;


import org.springframework.dao.DuplicateKeyException;
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
public class DuplicateKeyExceptionHandler implements ExceptionHandlerPolicyModel<DuplicateKeyException> {

	@Override
	public Class<DuplicateKeyException> sequence() {
		return DuplicateKeyException.class;
	}

	@Override
	public ResponseVO<?> handler(DuplicateKeyException exception) {
		// exception.getRootCause().getMessage()
		// Duplicate entry 'xDFWeHHwluxHPxw' for key 'tbl_user.AK_uq_TBL_USER_username'
		// FIXME: 明确错误字段
		return Result.build(ResultEnum.SQL_Integrity_Constraint_Violation_ERROR, exception.getRootCause().getMessage());
	}

}
