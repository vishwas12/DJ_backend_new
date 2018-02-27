package com.dj.app.handlers;

public class ApiResponse {
	private String status;
	private String code;
	private String message;
	private Object data;

	public ApiResponse(String status, Object data) {
		this.status = status;
		this.data = data;
	}

	public ApiResponse(String status, String code, Object data) {
		this.status = status;
		this.code = code;
		this.data = data;
	}

	public ApiResponse(String status, String code) {
		this.status = status;
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
