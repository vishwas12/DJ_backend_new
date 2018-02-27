package com.dj.app.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Vendor {

	@Id
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "vendor_id")
	private String vendorId;

	private String firstName;
	private String lastName;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address userAddress;
	@Column(unique = true)
	private String email;
	private String password;
	@Column(unique = true)
	private String phone;
	private Integer status;
	private Date createdOn;
	private String createdBy;

	@Transient
	private String accessToken;
	private String deviceId;
	// private UserType userType;
	private String userTypeVal;

	@Column(name = "IS_EMAIL_VERIFIED")
	private Boolean isEmailVerified;

/*	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLES", joinColumns = {
			@JoinColumn(name = "VENDOR_ID", referencedColumnName = "VENDOR_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID") })
	private List<Role> roles = new ArrayList<>();*/

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private VendorVerification userVerification;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vendor")
	private Set<Profile> profile;

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public Set<Profile> getProfile() {
		return profile;
	}

	public void setProfile(Set<Profile> profile) {
		this.profile = profile;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(Address userAddress) {
		this.userAddress = userAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUserTypeVal() {
		return userTypeVal;
	}

	public void setUserTypeVal(String userTypeVal) {
		this.userTypeVal = userTypeVal;
	}

	public Boolean getEmailVerified() {
		return isEmailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		isEmailVerified = emailVerified;
	}

/*	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}*/

	public VendorVerification getUserVerification() {
		return userVerification;
	}

	public void setUserVerification(VendorVerification userVerification) {
		this.userVerification = userVerification;
	}
}
