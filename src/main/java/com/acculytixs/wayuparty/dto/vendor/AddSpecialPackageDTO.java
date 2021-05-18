package com.acculytixs.wayuparty.dto.vendor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.acculytixs.wayuparty.util.FileInfo;

public class AddSpecialPackageDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String vendorUUID;
	
	private String serviceType;
	
	private String startDate;
	
	private String endDate;
	
	private String eventBannerImage;
	
	private String eventMobileBannerImage;
	
	private String eventDisplayImage;

	private Date createdDate;
	
    private List<FileInfo> fileInfo;

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<FileInfo> getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(List<FileInfo> fileInfo) {
		this.fileInfo = fileInfo;
	}

	public String getEventDisplayImage() {
		return eventDisplayImage;
	}

	public void setEventDisplayImage(String eventDisplayImage) {
		this.eventDisplayImage = eventDisplayImage;
	}

	@Override
	public String toString() {
		return "AddSpecialPackageDTO [vendorUUID=" + vendorUUID + ", serviceType=" + serviceType + ", startDate="
				+ startDate + ", endDate=" + endDate + ", eventBannerImage=" + eventBannerImage
				+ ", eventMobileBannerImage=" + eventMobileBannerImage + ", eventDisplayImage=" + eventDisplayImage
				+ ", createdDate=" + createdDate + ", fileInfo=" + fileInfo + "]";
	}

}
