package com.javabackend.mobileapp.webservicesmobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.javabackend.mobileapp.webservicesmobile.security.AppProperties;

@SpringBootApplication
public class WebservicesMobileApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebservicesMobileApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
	@Bean(name="AppProperties")
	public AppProperties appProperties() {
		return new AppProperties();
	}

}
