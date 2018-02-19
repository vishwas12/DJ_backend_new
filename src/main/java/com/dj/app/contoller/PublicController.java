package com.dj.app.contoller;


import com.dj.app.domain.User;
import com.dj.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("v1/public")
public class PublicController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public ResponseEntity register(@RequestBody User user) {
		userService.registerUser(user);

		return ResponseEntity.ok("");
	}
}
