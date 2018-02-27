package com.dj.app.exception;

public class ApiError {
	private String code;
	private String status;
	private String message;
	private Integer httpStatus;

	public ApiError() {
	}

	public ApiError(String code, String status, String message, Integer httpStatus) {
		this.code = code;
		this.status = status;
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}
}
