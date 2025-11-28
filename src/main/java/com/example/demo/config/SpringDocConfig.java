package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfig {
//	
//	@Bean
//	public OpenAPI createOpenApi(){
//		return new OpenAPI()
//				.info(createInfo());
//	}
//	
//	private Info createInfo(){
//		
//		return new Info()
//				.title("demo测试文档")
//				.description("demo测试文档描述")
//				.version("1.0.0")
//				.contact(createContact())
//				.license(new License()
//						.name("aaaaaa")
//						.url("https://www.mydemo.com/license"));
//	}
//	
//	private Contact createContact(){
//		Contact contact = new Contact();
//		contact.name("名字");
//		contact.url("https://www.mydemo.com");
//		contact.email("demo@mydemo.com");
//		return contact;
//	}
//	
//	private License createLicense(){
//		return new License()
//				.name("aaaaaa")
//				.url("https://www.mydemo.com/license");
//	}
	
}
