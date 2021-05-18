package com.acculytixs.wayuparty.dao.user;

import java.util.List;

import com.acculytixs.wayuparty.dto.search.PopularCitiesDTO;
import com.acculytixs.wayuparty.dto.user.RegisteredUsersDTO;
import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.dto.user.UserLoginDTO;
import com.acculytixs.wayuparty.dto.user.UserMobileLoginDTO;
import com.acculytixs.wayuparty.dto.user.VendorGuestUsersDTO;
import com.acculytixs.wayuparty.dto.user.VerificationDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorInfoDTO;
import com.acculytixs.wayuparty.entity.user.PasswordVerification;
import com.acculytixs.wayuparty.entity.user.PlaceOrderRatings;
import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.entity.vendor.VendorRatings;
import com.acculytixs.wayuparty.util.ResponseList;

public interface UserDao {

	boolean isUserExistedByEmailId(String emailId) throws Exception;
	
	boolean isUserExistedByMobileNumber(String mobileNumber) throws Exception;
	
	UserDTO getUserDetailsByUUID(String userUUID) throws Exception;
	
	void saveUser(User user) throws Exception;
	
	VendorInfoDTO getVendorInfoDetails(String vendorUUID) throws Exception;
	
	List<String> getVendorCustomCategories(List<Long> categoriesIds) throws Exception;
	
	User getUserByUUID(String uuid) throws Exception;
	
	UserLoginDTO getLoginUserDetails(Long userId) throws Exception;
	
	User getUserByVendorUUID(String vendorUUID) throws Exception;
	
	UserMobileLoginDTO getLoginMobileUserDetails(String userUUID) throws Exception;
	
	UserLoginDTO getLoginUserDetailsByEmail(String email) throws Exception;
	
	void savePasswordVerification(PasswordVerification passwordVerification) throws Exception;
	
	VerificationDTO getVerificationDetails(String uuid) throws Exception;
	
	PasswordVerification getPasswordVerificationByUUID(String uuid) throws Exception;
	
	String getUserUUIDByEmail(String email) throws Exception;
	
	String getUserUUIDByMobile(String mobileNumber) throws Exception;
	
	VendorGuestUsersDTO getGuestUserDetailsByEmail(String email) throws Exception;
	
	VendorGuestUsersDTO getGuestUserDetailsByMobileNumber(String mobileNumber) throws Exception;
	
	List<User> getUsersListByVendorId(Long vendorId) throws Exception;
	
	List<PopularCitiesDTO> getPopularCities();
	
	ResponseList<RegisteredUsersDTO> getRegisteredUsersList(RegisteredUsersDTO registeredUsersDTO) throws Exception;
	
	void savePlaceOrderRatings(PlaceOrderRatings placeOrderRatings) throws Exception;
	
	void sendSMS(UserDTO userDTO, String uuid) throws Exception;

}
