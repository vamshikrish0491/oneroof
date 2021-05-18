package com.acculytixs.wayuparty.dto.user;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.acculytixs.wayuparty.dto.services.MenuOfferingItemsDTO;
import com.acculytixs.wayuparty.dto.vendor.ServicesCategoryDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorProfileCategoriesDTO;

public class UserMobileLoginDTO {

	private String loginUserName;
	
	private String firstName;
	
	private String lastName;
	
	private String userUUID;
	
	private String userEmail;
	
	private String userMobile;
	
	private String userImage;
	
	private String gender;
	
	private String dob;
	 
	private String preferredDrinks;
	 
	private String preferredMusic;
	
	List<String> preferredDrinksList;
	
	List<String> preferredMusicList;
	

	public String getLoginUserName() {
		if(StringUtils.isNotBlank(loginUserName)) {
			return loginUserName;
		}else {
			return "";
		}
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	

	public String getFirstName() {
		if(StringUtils.isNotBlank(firstName)) {
			return firstName;
		}else {
			return "";
		}
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		if(StringUtils.isNotBlank(lastName)) {
			return lastName;
		}else {
			return "";
		}
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getUserEmail() {
		if(StringUtils.isNotBlank(userEmail)) {
			return userEmail;
		}else {
			return "";
		}
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		
		if(StringUtils.isNotBlank(userMobile)) {
			return userMobile;
		}else {
			return "";
		}
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserImage() {
		if(StringUtils.isNotBlank(userImage)) {
			return userImage;
		}else {
			return "";
		}
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getGender() {
		if(StringUtils.isNotBlank(gender)) {
			return gender;
		}else {
			return "";
		}
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getDob() {
		if(StringUtils.isNotBlank(dob)) {
			return dob;
		}else {
			return "";
		}
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPreferredDrinks() {
		if(StringUtils.isNotBlank(preferredDrinks)) {
			return preferredDrinks;
		}else {
			return "";
		}
	}

	public void setPreferredDrinks(String preferredDrinks) {
		this.preferredDrinks = preferredDrinks;
	}

	public String getPreferredMusic() {
		if(StringUtils.isNotBlank(preferredMusic)) {
			return preferredMusic;
		}else {
			return "";
		}
	}

	public void setPreferredMusic(String preferredMusic) {
		this.preferredMusic = preferredMusic;
	}

	public List<String> getPreferredDrinksList() {
		if(preferredDrinksList != null && !preferredDrinksList.isEmpty()) {
			return preferredDrinksList;
		}else {
			List<String> emptyList = Collections.<String>emptyList();  
			return emptyList;
		}
		
	}

	public void setPreferredDrinksList(List<String> preferredDrinksList) {
		this.preferredDrinksList = preferredDrinksList;
	}

	public List<String> getPreferredMusicList() {
		
		if(preferredMusicList != null && !preferredMusicList.isEmpty()) {
			return preferredMusicList;
		}else {
			List<String> emptyList = Collections.<String>emptyList();  
			return emptyList;
		}
	}

	public void setPreferredMusicList(List<String> preferredMusicList) {
		this.preferredMusicList = preferredMusicList;
	}

	
}
