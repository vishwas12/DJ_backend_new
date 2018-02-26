package com.dj.app.contoller;

import com.dj.app.domain.Vendor;
import com.dj.app.dto.UserDto;
import com.dj.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("v1/api")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ResponseEntity login(HttpServletRequest request, Authentication auth) {
		Vendor user = (Vendor) auth.getDetails();
		user.setPassword(null);
		user.setAccessToken((String)request.getAttribute("token"));
		return ResponseEntity.ok(auth.getDetails());
	}

	@RequestMapping(value = "/user/details",method = RequestMethod.GET)
	public ResponseEntity login(Principal principal) {
		UserDto dto = userService.getUserDetails(principal.getName());
		return ResponseEntity.ok(dto);
	}


}
