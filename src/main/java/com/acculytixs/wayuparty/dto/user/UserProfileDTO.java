package com.acculytixs.wayuparty.dto.user;

import java.io.Serializable;
import java.util.List;

import com.acculytixs.wayuparty.annotations.Required;
import com.acculytixs.wayuparty.util.FileInfo;

public class UserProfileDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Required(desc="firstName")
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	@Required(desc="mobile")
	private String mobile;
	
	@Required(desc="gender")
	private String gender;
	
	private String preferredDrinks;
	
	private String preferredMusic;
	
	private String dob;
	
	private List<FileInfo> fileInfo;
	
	private String userUUID;
	
	private String profileImageUrl;
	
	

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPreferredDrinks() {
		return preferredDrinks;
	}

	public void setPreferredDrinks(String preferredDrinks) {
		this.preferredDrinks = preferredDrinks;
	}

	public String getPreferredMusic() {
		return preferredMusic;
	}

	public void setPreferredMusic(String preferredMusic) {
		this.preferredMusic = preferredMusic;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public List<FileInfo> getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(List<FileInfo> fileInfo) {
		this.fileInfo = fileInfo;
	}

	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	
	

}
