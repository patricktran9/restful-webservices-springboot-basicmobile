package com.javabackend.mobileapp.webservicesmobile.security;

import com.javabackend.mobileapp.webservicesmobile.SpringApplicationContext;

public class SecurityConstant {
	public static final long EXPIRATION_TIME=259200;//3DAYS
	public static final String TOKEN_PREFIX="Bearer ";
	public static final String HEADER_STRING="Authorization";
	public static final String SIGN_UP_URL="/users";

	
	public static String getTokenSecret() {
		AppProperties appProperties= (AppProperties) SpringApplicationContext.getBean("appProperties");
		return appProperties.getTokenSecret();
	}
}
