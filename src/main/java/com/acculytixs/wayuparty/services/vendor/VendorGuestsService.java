package com.acculytixs.wayuparty.services.vendor;

import java.util.List;

import com.acculytixs.wayuparty.dto.user.GuestUserDTO;
import com.acculytixs.wayuparty.dto.user.UserGuestClubsDTO;
import com.acculytixs.wayuparty.dto.user.UserGuestClubsDetailsDTO;
import com.acculytixs.wayuparty.dto.user.VendorGuestUsersDTO;
import com.acculytixs.wayuparty.util.ResponseList;

public interface VendorGuestsService {

	boolean isUserExistedAsGuest(String userUUID, String vendorUUID) throws Exception;
	
	String saveGuestUser(GuestUserDTO guestUserDTO) throws Exception;
	
	ResponseList<VendorGuestUsersDTO> getGuestUsersList(VendorGuestUsersDTO guestUserDTO) throws Exception;
	
	VendorGuestUsersDTO getGuestUseDetails(String guestUUID) throws Exception;
	
	ResponseList<VendorGuestUsersDTO> getUserGuestsList(VendorGuestUsersDTO guestUserDTO) throws Exception;
	
	List<UserGuestClubsDTO> getGuestClubsList(String userUUID) throws Exception;
	
	UserGuestClubsDetailsDTO getGuestClubDetails(String guestUUID) throws Exception;
}
