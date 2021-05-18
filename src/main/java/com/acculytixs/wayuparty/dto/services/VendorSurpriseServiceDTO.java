package com.acculytixs.wayuparty.dto.services;

import java.io.Serializable;
import java.util.List;

import com.acculytixs.wayuparty.annotations.Required;
import com.acculytixs.wayuparty.util.FileInfo;

public class VendorSurpriseServiceDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Required(desc="surpriseType")
	private String surpriseType;
	
	@Required(desc="surpriseName")
	private String surpriseName;
	
	@Required(desc="actualPrice")
	private Double actualPrice;
	
	@Required(desc="offerPrice")
	private Double offerPrice;
	
	@Required(desc="allowed")
	private Integer allowed;
	
	@Required(desc="startDate")
	private String startDate;
	
	@Required(desc="endDate")
	private String endDate;
	
	@Required(desc="description")
	private String description;
	
	@Required(desc="serviceOffer")
	private String serviceOffer;
	
	private List<TimeSlotSchedulerInfo> timeSchedulerInfo;
	
    private List<FileInfo> fileInfo;
	
	@Required(desc="termsAndConditions")
	private String termsAndConditions;
	
	private String vendorUUID;
	
	private String serviceUUID;
	
	private String surpriseUUID;
	

	public String getSurpriseType() {
		return surpriseType;
	}

	public void setSurpriseType(String surpriseType) {
		this.surpriseType = surpriseType;
	}

	public String getSurpriseName() {
		return surpriseName;
	}

	public void setSurpriseName(String surpriseName) {
		this.surpriseName = surpriseName;
	}

	public Double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}

	public Integer getAllowed() {
		return allowed;
	}

	public void setAllowed(Integer allowed) {
		this.allowed = allowed;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getServiceOffer() {
		return serviceOffer;
	}

	public void setServiceOffer(String serviceOffer) {
		this.serviceOffer = serviceOffer;
	}

	public List<TimeSlotSchedulerInfo> getTimeSchedulerInfo() {
		return timeSchedulerInfo;
	}

	public void setTimeSchedulerInfo(List<TimeSlotSchedulerInfo> timeSchedulerInfo) {
		this.timeSchedulerInfo = timeSchedulerInfo;
	}

	public List<FileInfo> getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(List<FileInfo> fileInfo) {
		this.fileInfo = fileInfo;
	}

	public String getTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}

	public String getServiceUUID() {
		return serviceUUID;
	}

	public void setServiceUUID(String serviceUUID) {
		this.serviceUUID = serviceUUID;
	}

	public String getSurpriseUUID() {
		return surpriseUUID;
	}

	public void setSurpriseUUID(String surpriseUUID) {
		this.surpriseUUID = surpriseUUID;
	}
	
	
	

}
