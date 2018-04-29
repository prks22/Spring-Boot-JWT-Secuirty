package com.common.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationTokenFilter implements Filter {

	@Override
	public void init(final FilterConfig fc) throws ServletException {

	}

	
	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain fc)
			throws IOException, ServletException {
		addCors(req, res);
		HttpServletResponse resp = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		// For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake		
		if (request.getMethod().equals("OPTIONS")) {
			resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			return;
		}
		
		final SecurityContext context = SecurityContextHolder.getContext();
		if (context.getAuthentication() != null && context.getAuthentication().isAuthenticated()) {
			// do nothing
		} else {
			final HttpServletRequest httpRequest = (HttpServletRequest) req;
			final String tokenPayload = httpRequest.getHeader("Authorization");
			req.getParameterMap();
			if (tokenPayload != null) {
				final String token = tokenPayload.split(" ")[1];
				if (token != null) {
					final Authentication auth = new AuthentiationToken(token);
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
		}

		fc.doFilter(req, res);
	}



	private void addCors(final ServletRequest req, final ServletResponse res) {
		// Authorize (allow) all domains to consume the content
		((HttpServletResponse) res).addHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse) res).addHeader("Access-Control-Allow-Methods", "GET, PATCH, OPTIONS, DELETE, HEAD, PUT, POST");
		((HttpServletResponse) res).addHeader("Access-Control-Allow-Headers",
				"X-Requested-With, Content-Type, X-Codingpedia, Authorization");
	}


	@Override
	public void destroy() {

	}
}