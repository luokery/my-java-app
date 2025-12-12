package com.example.demo.exception;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

@SpringBootTest
public class GlobalExceptionHandlerTest {

	@Test
	public void handleAllOtherExceptionsTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void handleBusinessExceptionTest() {
		throw new RuntimeException("Test not implemented");
	}
}
