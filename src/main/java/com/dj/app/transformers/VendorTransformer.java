package com.dj.app.transformers;

import com.dj.app.domain.Vendor;
import com.dj.app.dto.RegisterDto;
import com.dj.app.handlers.ApiResponse;
import com.dj.app.utils.DjConstants;
import org.springframework.beans.BeanUtils;

public class VendorTransformer {

	public static ApiResponse transformToRegisterResponse(Vendor vendor) {
		RegisterDto registerDto = new RegisterDto();
		BeanUtils.copyProperties(vendor,registerDto,"password");
		ApiResponse response = new ApiResponse(DjConstants.SUCCESS,DjConstants.REGISTRATION_SUCCESSFUL,registerDto);
		return response;
	}

	public static ApiResponse transformToLoginResponse(Vendor vendor,String token) {
		RegisterDto registerDto = new RegisterDto();
		BeanUtils.copyProperties(vendor,registerDto,"password");
		registerDto.setToken(token);
		ApiResponse response = new ApiResponse(DjConstants.SUCCESS,DjConstants.LOGIN_SUCCESSFUL,registerDto);
		return response;
	}
}
