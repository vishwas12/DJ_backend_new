package com.dj.app.contoller;


import com.dj.app.domain.Vendor;
import com.dj.app.dto.RegisterDto;
import com.dj.app.exception.CustomException;
import com.dj.app.exception.ValidationException;
import com.dj.app.handlers.ApiResponse;
import com.dj.app.service.VendorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/public")
public class PublicController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PublicController.class);

	@Autowired
	VendorService vendorService;

	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterDto registerDto,
												BindingResult results) {
		if(results.hasErrors()) {
			throw new ValidationException(HttpStatus.BAD_REQUEST,results.getFieldError().getDefaultMessage());
		}
		ApiResponse response = vendorService.registerUser(registerDto);
		return ResponseEntity.ok(response);
	}
}
