package com.dj.app.service;

import com.dj.app.domain.Vendor;
import com.dj.app.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

	@Autowired
	VendorRepository vendorRepository;

	public void sendPasswordResetMail(String email) {
		Vendor vendor = vendorRepository.findByEmail(email);
		//TODO SEND EMAIL TO USER
	}
}
