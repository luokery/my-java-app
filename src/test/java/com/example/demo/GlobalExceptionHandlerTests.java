package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.demo.cosnst.ResultEnum;
import com.example.demo.exception.BusinessException;

@SpringBootTest
public class GlobalExceptionHandlerTests {
	
	@Test
	void contextLoads() {
//		throw new BusinessException(ResultEnum.VALIDATION_ERROR, "test Exception");
	}
}
