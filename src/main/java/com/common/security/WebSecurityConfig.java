package com.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/** The Constant TOKEN_REFRESH_ENTRY_POINT. */
	public static final String TOKEN_REFRESH_ENTRY_POINT = "/usermang/v1/login";	

	/** The authentication provider. */
	@Autowired
	AuthenticationProvider authenticationProvider;

	
	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests().antMatchers(TOKEN_REFRESH_ENTRY_POINT).permitAll().and().authorizeRequests()
				.anyRequest().authenticated();
		http.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint());
		http.addFilterBefore(new AuthenticationTokenFilter(), BasicAuthenticationFilter.class);
	}
	
	
}