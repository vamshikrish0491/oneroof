package com.acculytixs.wayuparty.dao.vendor;

import java.util.List;

import com.acculytixs.wayuparty.dto.user.UserGuestClubsDTO;
import com.acculytixs.wayuparty.dto.user.UserGuestClubsDetailsDTO;
import com.acculytixs.wayuparty.dto.user.VendorGuestUsersDTO;
import com.acculytixs.wayuparty.entity.vendor.VendorGuests;
import com.acculytixs.wayuparty.util.ResponseList;

public interface VendorGuestsDao {

	boolean isUserExistedAsGuest(String userUUID, String vendorUUID) throws Exception;
	
	void saveGuestUser(VendorGuests vendorGuests) throws Exception;
	
	ResponseList<VendorGuestUsersDTO> getGuestUsersList(VendorGuestUsersDTO guestUserDTO) throws Exception;
	
	VendorGuestUsersDTO getGuestUseDetails(String guestUUID) throws Exception;
	
	ResponseList<VendorGuestUsersDTO> getUserGuestsList(VendorGuestUsersDTO guestUserDTO) throws Exception;
	
	List<UserGuestClubsDTO> getGuestClubsList(String userUUID) throws Exception;
	
	UserGuestClubsDetailsDTO getGuestClubDetails(String guestUUID) throws Exception;
}
