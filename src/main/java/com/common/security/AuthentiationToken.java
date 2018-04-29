package com.common.security;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


public class AuthentiationToken implements Authentication {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private final String token;

	
	private boolean isAuthenticated;
	
	private User userDetails;

	
	public AuthentiationToken(final String token) {
		this.token = token;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<GrantedAuthority>(0);
	}

	
	@Override
	public Object getCredentials() {
		return token;
	}

	
	@Override
	public Object getDetails() {
		return userDetails;
	}
	public void setDetails(User user) {
		this.userDetails=user;
		
	}

	@Override
	public Object getPrincipal() {
		return userDetails;
	}

	@Override
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	
	@Override
	public void setAuthenticated(final boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuthenticated = isAuthenticated;
	}

	
	@Override
	public String getName() {
		return null;
	}
}