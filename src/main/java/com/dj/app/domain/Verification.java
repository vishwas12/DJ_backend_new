package com.dj.app.domain;

import com.dj.app.domain.Vendor;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Verification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vendorVerificationId;

	private String verificationCode;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "VENDOR_ID")
	private Vendor vendor;

	private Date verificationCodeCreatedDate;

	private String resetPasswordCode;

	private Date resetPasswordCreatedDate;

	public Long getVendorVerificationId() {
		return vendorVerificationId;
	}

	public void setVendorVerificationId(Long vendorVerificationId) {
		this.vendorVerificationId = vendorVerificationId;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Date getVerificationCodeCreatedDate() {
		return verificationCodeCreatedDate;
	}

	public void setVerificationCodeCreatedDate(Date verificationCodeCreatedDate) {
		this.verificationCodeCreatedDate = verificationCodeCreatedDate;
	}

	public String getResetPasswordCode() {
		return resetPasswordCode;
	}

	public void setResetPasswordCode(String resetPasswordCode) {
		this.resetPasswordCode = resetPasswordCode;
	}

	public Date getResetPasswordCreatedDate() {
		return resetPasswordCreatedDate;
	}

	public void setResetPasswordCreatedDate(Date resetPasswordCreatedDate) {
		this.resetPasswordCreatedDate = resetPasswordCreatedDate;
	}
}
