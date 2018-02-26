package com.dj.app.service;

import com.dj.app.domain.Role;
import com.dj.app.domain.Vendor;
import com.dj.app.dto.LoginDto;
import com.dj.app.dto.UserDto;
import com.dj.app.repository.RoleRepository;
import com.dj.app.repository.UserRepository;
import com.dj.app.utils.CommonUtils;
import com.dj.app.utils.DjConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	public Vendor authenticate(LoginDto loginDto) {
		Vendor user = null;
		try {
			if(CommonUtils.matchEmail(loginDto.getPrincipal())) {
				user = userRepository.findByEmailAndPassword(loginDto.getPrincipal(),loginDto.getPassword());
			}else {
				user = userRepository.findByPhoneAndPassword(loginDto.getPrincipal(),loginDto.getPassword());
			}

		}catch (Exception e) {
			LOGGER.error("Unable to authenticate : Principal {}",loginDto.getPrincipal());
		}
		return user;
	}

	public void registerUser(Vendor user) {
		try {
			user.setPassword(CommonUtils.encodeToMD5(user.getPassword()));
			Role role = roleRepository.findByRole(DjConstants.ROLE_USER);
			user.getRoles().add(role);
			user = userRepository.save(user);
			//TODO SEND SUCCESSFUL REGISTRATION EMAIL
			//mailer.prepareEmail(user,Constants.EMAIL_VERIFICATION,"Account Verification");
		}catch (Exception e) {
			LOGGER.error("Unable to register , Email  : {} Phone : {}",user.getEmail(),user.getPhone());
		}

	}

	public UserDto getUserDetails(String id) {
		Vendor user = null;
		UserDto userDto = new UserDto();
		try {
			 user = userRepository.findByUserId(id);
			BeanUtils.copyProperties(user,userDto);
			userDto.setResponseStatus("SUCCESS");
		}catch(Exception e) {
			userDto.setResponseStatus("ERROR");
			userDto.setResponseCode("UNABLE_TO_FETCH_DETAILS");
		}
		return userDto;
	}
}
