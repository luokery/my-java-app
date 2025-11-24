package com.example.demo.design.policymodel;

import com.example.demo.model.vo.ResultVo;

/**
 * 异常处理策略模型
 * @author 
 *
 */
public interface ExceptionHandlerPolicyModel<T> extends PolicyModel<T> {
	
	/**
	 * 具体处理逻辑
	 * @param exception 具体异常
	 * @return
	 */
	ResultVo<T> handler(T exception);
}
