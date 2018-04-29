package com.common.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthException  extends AuthenticationException{

	public AuthException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public String getErrorCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
