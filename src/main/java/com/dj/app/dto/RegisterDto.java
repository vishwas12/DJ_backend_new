package com.dj.app.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class RegisterDto {

	private String password;
	private String email;
	private String firstName;
	private String middleName;
	private String lastName;
	private Integer age;
	private String pic;
	private String phone;

}
