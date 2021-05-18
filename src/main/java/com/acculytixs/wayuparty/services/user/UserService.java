package com.acculytixs.wayuparty.services.user;

import java.util.List;

import com.acculytixs.wayuparty.dto.search.PopularCitiesDTO;
import com.acculytixs.wayuparty.dto.user.PlaceOrderRatingsDTO;
import com.acculytixs.wayuparty.dto.user.RegisteredUsersDTO;
import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.dto.user.UserLoginDTO;
import com.acculytixs.wayuparty.dto.user.UserMobileLoginDTO;
import com.acculytixs.wayuparty.dto.user.UserProfileDTO;
import com.acculytixs.wayuparty.dto.user.UserRegistrationDTO;
import com.acculytixs.wayuparty.dto.user.VendorGuestUsersDTO;
import com.acculytixs.wayuparty.dto.user.VerificationDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorInfoDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorRatingsDTO;
import com.acculytixs.wayuparty.entity.user.PasswordVerification;
import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.util.ResponseList;

public interface UserService {
	
	boolean isUserExistedByEmailId(String emailId) throws Exception;
	
	boolean isUserExistedByMobileNumber(String mobileNumber) throws Exception;
	
	UserDTO getUserDetailsByUUID(String userUUID) throws Exception;
	
	String saveNewUserRegistration(UserRegistrationDTO userRegistrationDTO) throws Exception;
	
	VendorInfoDTO getVendorInfoDetails(String vendorUUID) throws Exception;
	
	List<String> getVendorCustomCategories(List<Long> categoriesIds) throws Exception;
	
	String saveUserProfile(UserProfileDTO userProfileDTO) throws Exception;
	
	User getUserByUUID(String uuid) throws Exception;
	
	String resetUserPassword(String password, String userUUID) throws Exception;
	
	UserLoginDTO getLoginUserDetails(Long userId) throws Exception;
	
	UserMobileLoginDTO getLoginMobileUserDetails(String userUUID) throws Exception;
	
	void saveUser(User user) throws Exception;
	
	UserLoginDTO getLoginUserDetailsByEmail(String email) throws Exception;
	
	String getForgotPasswordVerificationCode(String email) throws Exception;
	
	VerificationDTO getVerificationDetails(String uuid) throws Exception;
	
	PasswordVerification getPasswordVerificationByUUID(String uuid) throws Exception;
	
	void savePasswordVerification(PasswordVerification passwordVerification) throws Exception;
	
	String getUserUUIDByEmail(String email) throws Exception;
	
	String getUserUUIDByMobile(String mobileNumber) throws Exception;
	
	VendorGuestUsersDTO getGuestUserDetailsByEmail(String email) throws Exception;
	
	VendorGuestUsersDTO getGuestUserDetailsByMobileNumber(String mobileNumber) throws Exception;
	
	void resendUserInvation(String userUUID) throws Exception;
	
	List<User> getUsersListByVendorId(Long vendorId) throws Exception;
	
	List<PopularCitiesDTO> getPopularCities();
	
	ResponseList<RegisteredUsersDTO> getRegisteredUsersList(RegisteredUsersDTO registeredUsersDTO) throws Exception;
	
	String saveUserPlaceOrderRatings(PlaceOrderRatingsDTO placeOrderRatingsDTO) throws Exception;
	
	void sendSMS(UserDTO userDTO, String uuid) throws Exception;


}
