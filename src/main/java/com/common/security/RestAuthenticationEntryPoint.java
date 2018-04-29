package com.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.common.dto.ErrorResponseDto;
import com.common.exception.AuthException;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException authenticationException) throws IOException, ServletException {
		response.setContentType("application/json");
		final ErrorResponseDto responseDto = new ErrorResponseDto();
		responseDto.setMessage(authenticationException.getMessage());
		responseDto.setErrorCode(HttpStatus.UNAUTHORIZED.value());
		if (authenticationException instanceof AuthException) {
			final String errorCode = ((AuthException) authenticationException).getErrorCode();
			if (errorCode != null) {
				responseDto.setErrorCode(Integer.parseInt(errorCode));
			}
		}
		response.getWriter().write(convertObjectToJson(responseDto));
	}

	
	private String convertObjectToJson(final Object object) {
		if (object == null) {
			return null;
		}
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}