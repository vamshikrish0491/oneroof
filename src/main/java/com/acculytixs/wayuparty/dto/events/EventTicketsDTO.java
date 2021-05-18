package com.acculytixs.wayuparty.dto.events;

import com.acculytixs.wayuparty.annotations.Required;

public class EventTicketsDTO {

	@Required(desc="ticketName")
	private String ticketName;
	
	@Required(desc="ticketRating")
	private Integer ticketRating;
	
	private String displayImage;
	
	private String bannerImage;
	
	private String ticketUUID;
	
	private String vendorUUID;

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public Integer getTicketRating() {
		return ticketRating;
	}

	public void setTicketRating(Integer ticketRating) {
		this.ticketRating = ticketRating;
	}
	
	public String getDisplayImage() {
		return displayImage;
	}

	public void setDisplayImage(String displayImage) {
		this.displayImage = displayImage;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getTicketUUID() {
		return ticketUUID;
	}

	public void setTicketUUID(String ticketUUID) {
		this.ticketUUID = ticketUUID;
	}

	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}
	
	
}
