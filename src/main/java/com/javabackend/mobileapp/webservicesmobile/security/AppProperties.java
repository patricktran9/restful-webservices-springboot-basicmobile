package com.javabackend.mobileapp.webservicesmobile.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.javabackend.mobileapp.webservicesmobile.SpringApplicationContext;

@Component
public class AppProperties {
	@Autowired
	private Environment env;
	
	public String getTokenSecret() {
		
		return env.getProperty("tokenSecret") ;
	}
}
