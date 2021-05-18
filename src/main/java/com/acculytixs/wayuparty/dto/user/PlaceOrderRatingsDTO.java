package com.acculytixs.wayuparty.dto.user;

import com.acculytixs.wayuparty.annotations.Required;

public class PlaceOrderRatingsDTO {

	
	@Required(desc="userUUID")
	private String userUUID;
	
	@Required(desc="rating")
	private String rating;
	
	private String ratingTag;
	
	@Required(desc="ratingDescription")
	private String ratingDescription;
	
	@Required(desc="placeOrderCode")
	private String placeOrderCode;

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

	public String getRatingTag() {
		return ratingTag;
	}

	public void setRatingTag(String ratingTag) {
		this.ratingTag = ratingTag;
	}

	public String getRatingDescription() {
		return ratingDescription;
	}

	public void setRatingDescription(String ratingDescription) {
		this.ratingDescription = ratingDescription;
	}

	public String getPlaceOrderCode() {
		return placeOrderCode;
	}

	public void setPlaceOrderCode(String placeOrderCode) {
		this.placeOrderCode = placeOrderCode;
	}
	
	
}
