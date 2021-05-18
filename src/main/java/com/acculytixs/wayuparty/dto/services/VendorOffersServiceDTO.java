package com.acculytixs.wayuparty.dto.services;

import java.io.Serializable;
import java.util.List;

import com.acculytixs.wayuparty.annotations.Required;
import com.acculytixs.wayuparty.util.FileInfo;

public class VendorOffersServiceDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Required(desc="offerType")
	private String offerType;
	
	@Required(desc="offerName")
	private String offerName;
	
	@Required(desc="minimumOrder")
	private Double minimumOrder;
	
	@Required (desc = "discountValue", dependsOn = "offerType", parentValue = "39")
	private Double discountValue;
	
	@Required (desc = "discountType", dependsOn = "offerType", parentValue = "39")
	private String discountType;
	
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
	
	private String offerUUID;
	
	

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public Double getMinimumOrder() {
		return minimumOrder;
	}

	public void setMinimumOrder(Double minimumOrder) {
		this.minimumOrder = minimumOrder;
	}

	public Double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(Double discountValue) {
		this.discountValue = discountValue;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
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

	public String getOfferUUID() {
		return offerUUID;
	}

	public void setOfferUUID(String offerUUID) {
		this.offerUUID = offerUUID;
	}
	
	
	

}
