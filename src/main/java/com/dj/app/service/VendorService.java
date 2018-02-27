package com.dj.app.service;

import com.dj.app.domain.Vendor;
import com.dj.app.dto.LoginDto;
import com.dj.app.dto.RegisterDto;
import com.dj.app.dto.UserDto;
import com.dj.app.exception.CustomException;
import com.dj.app.handlers.ApiResponse;
import com.dj.app.repository.RoleRepository;
import com.dj.app.repository.VendorRepository;
import com.dj.app.transformers.VendorTransformer;
import com.dj.app.utils.CommonUtils;
import com.dj.app.utils.DjConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;

@Configuration
public class VendorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VendorService.class);

	@Autowired
	VendorRepository vendorRepository;

	@Autowired
	RoleRepository roleRepository;

	public Vendor authenticate(LoginDto loginDto) {
		Vendor user = null;
		try {
			String password = CommonUtils.encodeToMD5(loginDto.getPassword());
			if(CommonUtils.matchEmail(loginDto.getPrincipal())) {
				user = vendorRepository.findByEmailAndPassword(loginDto.getPrincipal(),password);
			}else {
				user = vendorRepository.findByPhoneAndPassword(loginDto.getPrincipal(),password);
			}

		}catch (Exception e) {
			LOGGER.error("Unable to authenticate : Principal {}",loginDto.getPrincipal());
		}
		return user;
	}

	public ApiResponse registerUser(RegisterDto registerDto) throws CustomException{
		Vendor vendor = new Vendor();
		ApiResponse apiResponse = null;
		try {
			BeanUtils.copyProperties(registerDto,vendor,"password");
			vendor.setPassword(CommonUtils.encodeToMD5(registerDto.getPassword()));
			vendor = vendorRepository.save(vendor);
			apiResponse = VendorTransformer.transformToRegisterResponse(vendor);
		}catch (Exception e) {
			LOGGER.error("Unable to register , Email  : {} Phone : {}",registerDto.getEmail(),registerDto.getPhone());
			String code = DjConstants.UNABLE_TO_REGISTER;
			if(e.getClass().isAssignableFrom(DataIntegrityViolationException.class)) {
				code = DjConstants.PHONE_OR_EMAIL_PRE_EXISTS;
			}
			apiResponse = new ApiResponse(DjConstants.ERROR,code);
		}
		return apiResponse;

	}

	public UserDto getUserDetails(String id) {
		Vendor user = null;
		UserDto userDto = new UserDto();
		try {
			 user = vendorRepository.findByVendorId(id);
			BeanUtils.copyProperties(user,userDto);
			userDto.setResponseStatus("SUCCESS");
		}catch(Exception e) {
			userDto.setResponseStatus("ERROR");
			userDto.setResponseCode("UNABLE_TO_FETCH_DETAILS");
		}
		return userDto;
	}
}
