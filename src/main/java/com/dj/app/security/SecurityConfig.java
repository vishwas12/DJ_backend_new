package com.dj.app.security;

import com.dj.app.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	VendorService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
				.and()
				.csrf().disable().authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilterBefore(new JwtLoginFilter("/v1/api/login",
								userService, authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JwtAuthenticationTokenFilter(),
						UsernamePasswordAuthenticationFilter.class);


	}

	@Override
	public void configure(WebSecurity webSecurity) {
		webSecurity.ignoring()
				.antMatchers("/")
				.antMatchers(HttpMethod.OPTIONS)
				.antMatchers("/v1/public/**");
	}
}
