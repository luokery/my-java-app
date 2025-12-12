package com.example.demo;


import static org.testng.Assert.assertEquals;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.alibaba.druid.pool.DruidDataSource;


//@SpringBootTest(classes = { DemoApplication.class })
//@AutoConfigureMockMvc
//@AutoConfigureWebTestClient
//@WebAppConfiguration
@SpringBootTest
public class DruidTest extends AbstractTestNGSpringContextTests {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	void contextLoads() {
		
		log.info(String.format("数据源:{%s}", dataSource.getClass()));
		DruidDataSource druidDataSource = (DruidDataSource) dataSource;
		log.info(String.format("初始化数量:{%d}", druidDataSource.getInitialSize()));
		log.info(String.format("最大数量:{%d}", druidDataSource.getMaxActive()));
		
		assertEquals(dataSource.getClass().toString(), "class com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceWrapper");
	}
}
