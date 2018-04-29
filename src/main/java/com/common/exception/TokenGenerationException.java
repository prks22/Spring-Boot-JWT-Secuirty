package com.common.exception;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class TokenGenerationException extends Exception {

	public TokenGenerationException(String string, UnsupportedEncodingException exception) {
		// TODO Auto-generated constructor stub
	}

	public TokenGenerationException(String string, JWTCreationException exception) {
		// TODO Auto-generated constructor stub
	}

	public TokenGenerationException(String string, JWTVerificationException exception) {
		// TODO Auto-generated constructor stub
	}

}
