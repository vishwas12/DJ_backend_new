package com.dj.app.dto;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class RegisterDto {

	@NotNull(message = "Password is required")
	@NotEmpty(message = "Password is required")
	private String password;

	@NotNull(message = "Email is required")
	@NotEmpty(message = "Email is required")
	@Email(message = "Email is not valid")
	private String email;

	@NotNull(message = "First name is required")
	@NotEmpty(message = "First name is required")
	private String firstName;

	private String middleName;

	@NotNull(message = "Last name  is required")
	@NotEmpty(message = "Last name is required")
	private String lastName;

	private Integer age;
	private String pic;

	@NotNull(message = "Phone number is required")
	@NotEmpty(message = "Phone number is required")
	private String phone;

	private String token;
	private String vendorId;


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
}
