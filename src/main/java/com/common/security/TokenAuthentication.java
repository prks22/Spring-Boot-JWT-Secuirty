package com.common.security;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Singleton;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.common.exception.TokenGenerationException;
import com.common.exception.TokenVerificationException;


@Component
@Singleton
public class TokenAuthentication {

	private static String secret = "secret-dmat-vz";
	

	public TokenAuthentication() {
	}

	
	public String getToken(final User user) throws TokenGenerationException {		
		final Calendar tokenExpiryTime = Calendar.getInstance();
		tokenExpiryTime.setTime(new Date());
		tokenExpiryTime.add(Calendar.HOUR_OF_DAY, 6);		
		try {
			final Algorithm algorithm = Algorithm.HMAC256(secret);
			final String token = JWT.create().withIssuer("auth0").withClaim("userName", user.getUserName())
					.withClaim("userId", user.getUserId()).withClaim("scope", user.getScope())
					.withClaim("isActive", user.getIsActive()).withClaim("isGroupAdmin", user.getIsGroupAdmin())
					.withClaim("userDomain", user.getUserDomain())
					.withClaim("expiry", tokenExpiryTime.getTimeInMillis() + "").sign(algorithm);
			return token;
		} catch (final UnsupportedEncodingException exception) {
			throw new TokenGenerationException("UTF-8 encoding not supported", exception);
		} catch (final JWTCreationException exception) {
			throw new TokenGenerationException("Token could not be generated", exception);
		}
	}

	public DecodedJWT validateToken(final String token) throws Exception {

		try {
			final Algorithm algorithm = Algorithm.HMAC256(secret);
			final JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
			final DecodedJWT jwt = verifier.verify(token);			
			return jwt;
		} catch (final UnsupportedEncodingException exception) {
			throw new TokenVerificationException("UTF-8 encoding not supported", exception);
		} catch (final JWTVerificationException exception) {
			throw new TokenGenerationException("Invalid user", exception);
		}
	}

	

	public static void main(final String[] args) {
		final TokenAuthentication gToken = new TokenAuthentication();
		
		try {
			final DecodedJWT jwt = gToken.validateToken(
					"ImlzcyI6ImF1dGgwIiwidXNlckRvbWFpbiI6IlZlcml6b25XaXJlbGVzcy5jb20iLCJ1c2VyTmFtZSI6IkpvZ2VuZHJhLllhcmFtY2hpdHRpQFZlcml6b25XaXJlbGVzcy5jb20iLCJpc0FjdGl2ZSI6MSwidXNlcklkIjo3OX0._FC0SSxCDa99pePt9ZCQlJ3K1DSEezTSb9u4qCIsyW4");
			final Integer userId = jwt.getClaim("userId").asInt();
			final Integer roleId = jwt.getClaim("scope").asInt();
			final String userName = jwt.getClaim("userName").asString();
			final Integer isActive = jwt.getClaim("isActive").asInt();
			final Integer isGroupAdmin = jwt.getClaim("isGroupAdmin").asInt();
			final String userDomain = jwt.getClaim("userDomain").asString();
			final User user = new User();	
			user.setUserName("pp");
			user.setUserDomain("tt");
			user.setUserId(79);
			user.setIsActive(1);
			user.setIsGroupAdmin(1);
			final String token = gToken.getToken(user);
			System.out.println("Token  - " + token);
			gToken.validateToken(token);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
