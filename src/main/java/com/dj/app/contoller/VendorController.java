package com.dj.app.contoller;

import com.dj.app.domain.Vendor;
import com.dj.app.dto.UserDto;
import com.dj.app.handlers.ApiResponse;
import com.dj.app.service.VendorService;
import com.dj.app.transformers.VendorTransformer;
import com.dj.app.utils.DjConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.security.pkcs11.wrapper.Constants;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("v1/api")
public class VendorController {

	@Autowired
	VendorService userService;

	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ResponseEntity login(HttpServletRequest request, Authentication auth) {
		Vendor vendor = (Vendor) auth.getDetails();
		ApiResponse apiResponse = null;
		if(null != vendor) {
			apiResponse = VendorTransformer.transformToLoginResponse(vendor,(String)request.getAttribute("token"));
		}else {
			apiResponse = new ApiResponse(DjConstants.ERROR,DjConstants.BAD_CREDENTIALS);
		}
		return ResponseEntity.ok(apiResponse);
	}

	@RequestMapping(value = "/user/details",method = RequestMethod.GET)
	public ResponseEntity login(Principal principal) {
		UserDto dto = userService.getUserDetails(principal.getName());
		return ResponseEntity.ok(dto);
	}


}
