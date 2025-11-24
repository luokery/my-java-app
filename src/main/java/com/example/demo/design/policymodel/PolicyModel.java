package com.example.demo.design.policymodel;

public interface PolicyModel<T> {
	
	/**
	 * 唯一标识
	 * 用于区分处理实现
	 * @return
	 */
	Class<T> sequence();
	
}
