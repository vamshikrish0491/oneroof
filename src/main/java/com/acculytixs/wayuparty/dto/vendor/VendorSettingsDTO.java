package com.acculytixs.wayuparty.dto.vendor;

import java.io.Serializable;

public class VendorSettingsDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String vendorUUID;
	
	private String serviceUUID;
	
	private String isEntryRatioEnabled;
	
	private Integer menRatioValue;
	
	private Integer womenRatioValue;
	
	private String isCancelOrderEnabled;
	
	private Integer cancelOderValue;
	
	private String cancelOrder;
	
	private String isRescheduleOrderEnabled;
	
	private Integer rescheduleOrderValue;
	
	private String rescheduleOrder;
	
	private String ispPriorOrderEnabled;
	
	private Integer priorOrderValue;
	
	private String priorOrder;
	
	private String orderApproval;
	
	

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

	public String getIsEntryRatioEnabled() {
		return isEntryRatioEnabled;
	}

	public void setIsEntryRatioEnabled(String isEntryRatioEnabled) {
		this.isEntryRatioEnabled = isEntryRatioEnabled;
	}

	public Integer getMenRatioValue() {
		return menRatioValue;
	}

	public void setMenRatioValue(Integer menRatioValue) {
		this.menRatioValue = menRatioValue;
	}

	public Integer getWomenRatioValue() {
		return womenRatioValue;
	}

	public void setWomenRatioValue(Integer womenRatioValue) {
		this.womenRatioValue = womenRatioValue;
	}

	public String getIsCancelOrderEnabled() {
		return isCancelOrderEnabled;
	}

	public void setIsCancelOrderEnabled(String isCancelOrderEnabled) {
		this.isCancelOrderEnabled = isCancelOrderEnabled;
	}

	public Integer getCancelOderValue() {
		return cancelOderValue;
	}

	public void setCancelOderValue(Integer cancelOderValue) {
		this.cancelOderValue = cancelOderValue;
	}

	public String getCancelOrder() {
		return cancelOrder;
	}

	public void setCancelOrder(String cancelOrder) {
		this.cancelOrder = cancelOrder;
	}

	public String getIsRescheduleOrderEnabled() {
		return isRescheduleOrderEnabled;
	}

	public void setIsRescheduleOrderEnabled(String isRescheduleOrderEnabled) {
		this.isRescheduleOrderEnabled = isRescheduleOrderEnabled;
	}

	public Integer getRescheduleOrderValue() {
		return rescheduleOrderValue;
	}

	public void setRescheduleOrderValue(Integer rescheduleOrderValue) {
		this.rescheduleOrderValue = rescheduleOrderValue;
	}

	public String getRescheduleOrder() {
		return rescheduleOrder;
	}

	public void setRescheduleOrder(String rescheduleOrder) {
		this.rescheduleOrder = rescheduleOrder;
	}

	public String getIspPriorOrderEnabled() {
		return ispPriorOrderEnabled;
	}

	public void setIspPriorOrderEnabled(String ispPriorOrderEnabled) {
		this.ispPriorOrderEnabled = ispPriorOrderEnabled;
	}

	public Integer getPriorOrderValue() {
		return priorOrderValue;
	}

	public void setPriorOrderValue(Integer priorOrderValue) {
		this.priorOrderValue = priorOrderValue;
	}

	public String getPriorOrder() {
		return priorOrder;
	}

	public void setPriorOrder(String priorOrder) {
		this.priorOrder = priorOrder;
	}

	public String getOrderApproval() {
		return orderApproval;
	}

	public void setOrderApproval(String orderApproval) {
		this.orderApproval = orderApproval;
	}
	

}
