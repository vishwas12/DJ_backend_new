package com.dj.app.contoller;


import com.dj.app.dto.CategoryDto;
import com.dj.app.dto.EquipmentsDto;
import com.dj.app.dto.RegisterDto;
import com.dj.app.exception.CustomException;
import com.dj.app.exception.ValidationException;
import com.dj.app.handlers.ApiResponse;
import com.dj.app.service.CategoryService;
import com.dj.app.service.EquipmentService;
import com.dj.app.service.MessagingService;
import com.dj.app.service.VendorService;
import com.dj.app.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/public")
public class PublicController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PublicController.class);

	@Autowired
	VendorService vendorService;

	@Autowired
	EquipmentService equipmentService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	MessagingService messagingService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterDto registerDto,
												BindingResult results) {
		if (results.hasErrors()) {
			throw new ValidationException(HttpStatus.BAD_REQUEST, results.getFieldError().getDefaultMessage());
		}
		ApiResponse response = vendorService.registerUser(registerDto);
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "/equipments", method = RequestMethod.GET)
	public ResponseEntity<List<EquipmentsDto>> fetchEquipments(@RequestParam(value = "categoryId", required = false) Integer categoryId,
											 @RequestParam(value = "subCategoryId", required = false) Integer subCategoryId) {
		if (null == categoryId && null == subCategoryId) {
			throw new CustomException(Constants.REQUIRED_CATEGORY_OR_SUBCATEGORY);
		}
		List<EquipmentsDto> equipmentsDtos = equipmentService.fetchEquipmentsByCategory(categoryId, subCategoryId);
		return ResponseEntity.ok(equipmentsDtos);

	}

	@RequestMapping(value = "/categoryList", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDto>> fetchCategoryList() {
		List<CategoryDto> categories = categoryService.fetchCategoryList();
		return ResponseEntity.ok(categories);
	}

	@RequestMapping(value = "/verifyEmail", method = RequestMethod.GET)
	public ResponseEntity<?> verifyEmail(@RequestParam(name = "code") String code,
										 @RequestParam(name = "id") Long id) {
		Boolean isVerified = vendorService.verifyEmail(id, code);
		return ResponseEntity.ok(isVerified);
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
		messagingService.sendPasswordResetMail(email);
		return ResponseEntity.ok("");
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> map) throws Exception {
		vendorService.resetPassword(map);
		return ResponseEntity.ok("");
	}

	@RequestMapping(value = "/validateEmail", method = RequestMethod.GET)
	public ResponseEntity<?> verifyEmail(@RequestParam String email) {
		boolean isAlreadyExists = vendorService.verifyPrincipal(email);
		return ResponseEntity.ok(isAlreadyExists);
	}


}
