package com.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.common.exception.AuthException;
import com.enums.ErrorCode;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	TokenAuthentication tokenAuthentication;

	
	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

		authentication.getName();
		final String token = (String) authentication.getCredentials();
		 long expiry=0;
		try {
			final DecodedJWT jwt = tokenAuthentication.validateToken(token);
			final Integer userId = jwt.getClaim("userId").asInt();
			final Integer roleId = jwt.getClaim("scope").asInt();
			final String userName = jwt.getClaim("userName").asString();
			final Integer isActive = jwt.getClaim("isActive").asInt();
			final Integer isGroupAdmin = jwt.getClaim("isGroupAdmin").asInt();
			final String userDomain = jwt.getClaim("userDomain").asString();			
			final User user = new User(userId, userName, roleId, isActive, isGroupAdmin, userDomain);
			((AuthentiationToken) authentication).setDetails(user);
			 expiry = Long.parseLong(jwt.getClaim("expiry").asString());
		} catch (final Exception e) {
			//throw new AuthException("Invalid token", "508", e);
		}
		
		final long currentTime=System.currentTimeMillis();
		if(expiry==0||currentTime>expiry){
			//throw new AuthException(ErrorCode.TOKEN_EXPIRED.getErrorMessage(), ErrorCode.TOKEN_EXPIRED.getErrorCode()+"",null);
		}
		authentication.setAuthenticated(true);
		return authentication;
	}

	
	@Override
	public boolean supports(final Class<?> authentication) {
		return true;
	}

}