package com.dj.app.security;

import com.dj.app.domain.User;
import com.dj.app.dto.LoginDto;
import com.dj.app.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter{

	private UserService userService;

	private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

	public JwtLoginFilter(String url, UserService userService,AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		this.userService = userService;
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
												HttpServletResponse httpServletResponse)
			throws AuthenticationException, IOException, ServletException {
		Map<String, String> params = new ObjectMapper()
				.readValue(httpServletRequest.getInputStream(), Map.class);

		String principal = params.get("principal");
		String password = params.get("password");
		User user = null;
		try {
			//TODO Authenticate user
			user = userService.authenticate(new LoginDto(principal,password));
			if(null == user) {
				unsuccessfulAuthentication(httpServletRequest,httpServletResponse,new BadCredentialsException("BAD_CREDENTIALS"));
				return null;
			}
		} catch (Exception e) {
			//TODO Authentication Failure
			unsuccessfulAuthentication(httpServletRequest,httpServletResponse,new BadCredentialsException("BAD_CREDENTIALS"));
			return null;
		}
		Set<GrantedAuthority> grantedAuthorities = null;

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user.getUserId(),
				null, grantedAuthorities);
		authenticationToken.setDetails(user);
		return authenticationToken;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,  HttpServletResponse res, FilterChain chain,
											Authentication auth) throws IOException, ServletException {
		SecurityContextHolder.getContext().setAuthentication(auth);
		JwtAuthenticationService.addAuthentication(req, res, (UsernamePasswordAuthenticationToken) auth);
		req.setAttribute("isAuthenticated", true);
		chain.doFilter(req, res);
	}
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
											  HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		if(!response.isCommitted())
			failureHandler.onAuthenticationFailure(request, response, failed);
	}
}
