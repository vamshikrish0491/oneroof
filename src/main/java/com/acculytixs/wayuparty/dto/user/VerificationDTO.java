package com.acculytixs.wayuparty.dto.user;

import org.apache.commons.lang.StringUtils;

import com.acculytixs.wayuparty.annotations.Required;

public class VerificationDTO {

	@Required(desc="verificationUUID")
	private String verificationUUID;
	
	private String verificationCode;
	
	@Required(desc="password")
	private String password;
	
	@Required(desc="confirmPassword")
	private String confirmPassword;
	
	private String userUUID;

	public String getVerificationUUID() {
		
		if(StringUtils.isNotBlank(verificationUUID)) {
			return verificationUUID;
		}else {
			return "";
		}
		
	}

	public void setVerificationUUID(String verificationUUID) {
		this.verificationUUID = verificationUUID;
	}

	public String getVerificationCode() {
		if(StringUtils.isNotBlank(verificationCode)) {
			return verificationCode;
		}else {
			return "";
		}
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getPassword() {
		if(StringUtils.isNotBlank(password)) {
			return password;
		}else {
			return "";
		}
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getConfirmPassword() {
		if(StringUtils.isNotBlank(confirmPassword)) {
			return confirmPassword;
		}else {
			return "";
		}
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getUserUUID() {
		if(StringUtils.isNotBlank(userUUID)) {
			return userUUID;
		}else {
			return "";
		}
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}
	
	
	
	
}
