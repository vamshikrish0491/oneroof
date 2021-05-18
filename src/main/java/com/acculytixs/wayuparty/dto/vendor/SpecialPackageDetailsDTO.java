package com.acculytixs.wayuparty.dto.vendor;

public class SpecialPackageDetailsDTO {
	
	private String vendorUUID;
	
	private String serviceType;
	
	private String eventBannerImage;
	
	private String eventMobileBannerImage;

	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getEventBannerImage() {
		return eventBannerImage;
	}

	public void setEventBannerImage(String eventBannerImage) {
		this.eventBannerImage = eventBannerImage;
	}

	public String getEventMobileBannerImage() {
		return eventMobileBannerImage;
	}

	public void setEventMobileBannerImage(String eventMobileBannerImage) {
		this.eventMobileBannerImage = eventMobileBannerImage;
	}

	@Override
	public String toString() {
		return "SpecialPackageDetailsDTO [vendorUUID=" + vendorUUID + ", serviceType=" + serviceType
				+ ", eventBannerImage=" + eventBannerImage + ", eventMobileBannerImage=" + eventMobileBannerImage + "]";
	}
	
	
}
