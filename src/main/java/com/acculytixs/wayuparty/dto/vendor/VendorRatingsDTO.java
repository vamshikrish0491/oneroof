package com.acculytixs.wayuparty.dto.vendor;

import com.acculytixs.wayuparty.annotations.Required;

public class VendorRatingsDTO {

	@Required(desc="vendorUUID")
	private String vendorUUID;
	
	@Required(desc="userUUID")
	private String userUUID;
	
	@Required(desc="rating")
	private String rating;
	
	@Required(desc="ratingDescription")
	private String ratingDescription;
	

	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}

	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRatingDescription() {
		return ratingDescription;
	}

	public void setRatingDescription(String ratingDescription) {
		this.ratingDescription = ratingDescription;
	}
	
	
	
}
