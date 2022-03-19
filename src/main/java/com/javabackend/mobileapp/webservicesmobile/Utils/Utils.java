package com.javabackend.mobileapp.webservicesmobile.Utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;
@Component
public class Utils {
	private final Random RANDOM= new SecureRandom();
	private final String ALPHABET ="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	public String generatedUserId(int length) {
		return generatedRandomString(length);
	}

	private String generatedRandomString(int length) {
		StringBuilder valueStr= new StringBuilder(length);
		for(int i =0 ; i<length; i++) {
			valueStr.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
			
		}
		return new String(valueStr);
	}
}
