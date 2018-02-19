package com.dj.app.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class LoginDto {

	@NotNull
	@NotEmpty
	private String principal;

	@NotNull
	@NotEmpty
	private String password;

	public LoginDto() {
	}

	public LoginDto(String principal, String password) {
		this.principal = principal;
		this.password = password;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
