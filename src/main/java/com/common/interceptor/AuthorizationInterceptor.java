package com.common.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.annotation.AUTHORIZED;
import com.common.exception.AuthorizationException;
import com.common.security.User;
import com.enums.ErrorCode;
import com.enums.Roles;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final HandlerMethod hm = (HandlerMethod) handler;
		final Method method = hm.getMethod();
		if (method.isAnnotationPresent(AUTHORIZED.class)) {
			final String[] values = method.getAnnotation(AUTHORIZED.class).value();
			final String[] notAllowed = method.getAnnotation(AUTHORIZED.class).notAllowed();
			final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			final Integer roleId = user.getScope();
			final Integer active = user.getIsActive();
			final Integer isGroupAdmin = user.getIsGroupAdmin();
			boolean isMatchRole = false;
			for (final String value : notAllowed) {
				if (Roles.getRoles(roleId).equals(value)) {
					throw new AuthorizationException(ErrorCode.ACCESS_DENIED.getErrorMessage(),
							ErrorCode.ACCESS_DENIED.getErrorCode());

				}

			}
			if (values.length == 0) {
				isMatchRole = true;
			} else {
				for (final String value : values) {
					if (Roles.getRoles(roleId).equals(value) || Roles.ALL.name().equalsIgnoreCase(value)) {
						isMatchRole = true;

					}

				}
			}
			if (isMatchRole && active == 1) {
				return true;
			}
			if (isGroupAdmin != null && isGroupAdmin == 1 && active == 1) {
				return true;
			}
		} else {
			return true;
		}
		throw new AuthorizationException(ErrorCode.ACCESS_DENIED.getErrorMessage(),
				ErrorCode.ACCESS_DENIED.getErrorCode());

	}
}
