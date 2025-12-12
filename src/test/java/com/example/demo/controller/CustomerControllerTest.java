package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.example.demo.DemoApplication;
import com.example.demo.service.api.CustomerService;
import com.example.demo.service.api.UserService;

@SpringBootTest(classes = DemoApplication.class)
public class CustomerControllerTest extends AbstractTestNGSpringContextTests {

	@Autowired
	CustomerService customerService;
	@Autowired
	UserService userService;
  @Test
  public void CustomerControllerTest() {
	  customerService.deleteById("1");
  }

  @Test
  public void createCustomerTest() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void deleteCustomerTest() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getAllCustomersTest() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getCustomerByIdTest() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void updateCustomerTest() {
    throw new RuntimeException("Test not implemented");
  }
}
