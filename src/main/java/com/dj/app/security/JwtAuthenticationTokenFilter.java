package com.dj.app.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request,
						 ServletResponse response,
						 FilterChain filterChain)
			throws IOException, ServletException {

		String path = ((HttpServletRequest) request).getRequestURI();
		String method = ((HttpServletRequest) request).getMethod();

		Boolean isAuthenticated = (Boolean) request.getAttribute("isAuthenticated");
		if ((isAuthenticated != null && isAuthenticated == true) ||
				(path.startsWith("/v1/public/register") && "POST".equals(method))
				) {
			filterChain.doFilter(request, response);
			return;
		}
		Authentication authentication = JwtAuthenticationService.getAuthentication((HttpServletRequest) request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}
}
