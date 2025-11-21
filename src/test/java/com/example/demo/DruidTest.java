package com.example.demo;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.druid.pool.DruidDataSource;


//@SpringBootTest(classes = { DemoApplication.class })
//@AutoConfigureMockMvc
//@AutoConfigureWebTestClient
//@WebAppConfiguration
@SpringBootTest
public class DruidTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	void contextLoads() {
		System.out.println(String.format("数据源:{%s}", dataSource.getClass()));
		DruidDataSource druidDataSource = (DruidDataSource) dataSource;
		System.out.println(String.format("初始化数量:{%d}", druidDataSource.getInitialSize()));
		System.out.println(String.format("最大数量:{%d}", druidDataSource.getMaxActive()));
	}
}
