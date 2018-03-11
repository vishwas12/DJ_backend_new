package com.dj.app.handlers;


import com.dj.app.exception.ApiError;
import com.dj.app.exception.CustomException;
import com.dj.app.exception.ValidationException;
import com.dj.app.utils.Constants;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;
import java.text.MessageFormat;
import java.util.Locale;

@RestControllerAdvice
public class ResponseHandler implements ResponseBodyAdvice<Object> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	MessageSource messageSource;

	@Override
	public boolean supports(MethodParameter returnType,
							Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType,
								  MediaType selectedContentType,
								  Class<? extends HttpMessageConverter<?>> selectedConverterType,
								  ServerHttpRequest request1, ServerHttpResponse response1) {
		Locale locale = request.getLocale();
		int status = response.getStatus();
		ApiResponse apiResponse = new ApiResponse(Constants.SUCCESS,Constants.EMPTY_STRING,body);
		return apiResponse;
	}
	public String resolveLocaleMessage(String code,Object [] args,Locale locale) {
		String message = messageSource.getMessage(code, null, "", locale);
		return MessageFormat.format(message, args);
	}

	@ExceptionHandler(value = ValidationException.class)
	protected ResponseEntity<Object> handleValidationException(ValidationException e) {
		ApiError apiError =
				new ApiError("BAD_REQUEST", Constants.ERROR, e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = CustomException.class)
	protected ResponseEntity<Object> handleCustomException(CustomException e) {
		ApiError apiError =
				new ApiError("INTERNAL_SERVER_ERROR", Constants.ERROR, e.getCode(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Object>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {

		ApiError apiError =
				new ApiError("METHOD_NOT_ALLOWED", Constants.ERROR, e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED.value());
		return new ResponseEntity<Object>(apiError, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException e) {
		ApiError apiError =
				new ApiError("404_NOT_FOUND", Constants.ERROR, e.getRequestURL() + " : Resource Not Found", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Object>(apiError, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		ApiError apiError =
				new ApiError("MISSING_PARAMETER", Constants.ERROR, e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<Object> handleJsonMappingException(JsonMappingException e) {
		ApiError apiError =
				new ApiError("INVALID_REQUEST_BODY", Constants.ERROR, e.getMessage(),HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		ApiError apiError =
				new ApiError("INVALID_REQUEST_BODY", Constants.ERROR, e.getLocalizedMessage(),HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<Object> HttpClientErrorException(HttpMediaTypeNotSupportedException e) {
		ApiError apiError =
				new ApiError("MEDIA_TYPE_NOT_SUPPORTED", Constants.ERROR, "supported types : " + e.getSupportedMediaTypes().toString(),HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
		return new ResponseEntity<Object>(apiError, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e) {
		ApiError apiError =
				new ApiError("ACCESS_DENIED", Constants.ERROR, "Please check your access privileges",HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<Object>(apiError, HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleConversionFailedException(MethodArgumentTypeMismatchException e) {
		ApiError apiError =
				new ApiError("INVALID_ARGUEMENT", Constants.ERROR, e.getName(),HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception e) {
		ApiError apiError =
				new ApiError("INTERNAL_SERVER_ERROR", Constants.ERROR, e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Object>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}