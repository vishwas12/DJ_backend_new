package com.dj.app.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
	private String code;

	public CustomException(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
