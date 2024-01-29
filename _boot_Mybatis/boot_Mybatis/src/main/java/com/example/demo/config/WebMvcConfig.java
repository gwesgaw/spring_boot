package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	//upload 경로 설정
	//@Value("${}") : WebMvcConfig 못읽어오는 에러가 발생
	
	String uploadPath = "file:///D:\\myProject\\_java\\_fileUpload\\";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**")
		.addResourceLocations(uploadPath);
	}

}
