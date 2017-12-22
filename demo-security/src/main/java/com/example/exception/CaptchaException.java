package com.example.exception;

import org.springframework.security.core.AuthenticationException;

public class CaptchaException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7759697400429524267L;

	public CaptchaException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}  
	
	 
	
}
