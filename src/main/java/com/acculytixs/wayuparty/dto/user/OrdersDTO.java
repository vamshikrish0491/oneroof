package com.acculytixs.wayuparty.dto.user;

import java.util.List;
import java.util.Map;

import com.acculytixs.wayuparty.dto.services.SurpriseDetailsDTO;

public class OrdersDTO {

	private String userName;
	
	private String orderCode;
	
	private String serviceOrderDate;
	
	private String placeOrderDate;
	
	private Double orderAmount;
	
	private String currency;
	
	private String vendorOrderStatus;
	
	private String timeSlot;
	
	private String orderUUID;
	
	private Integer quantity;
	
	private Double totalOrderAmount;
	
	private String packageMenuItems;
	
	private String masterServiceUUID;
	
	private String serviceName;
	
	private Integer allowed;
	
	private String serviceImage;
	
	private int start;
	
	private int length;
	
	private String searchValue;
	
	private String sortColumn;
	
	private String sortOrder;
	
	private List<Map<String, String>> order;
	
	List<UserPackageItemsDTO> packageItems;
	
	List<SurpriseDetailsDTO> surpriseList;
	
	private String surpriseInstructions;
	
	private String surpriseDetails;
	
	private String isUserArrived;
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getServiceOrderDate() {
		return serviceOrderDate;
	}

	public void setServiceOrderDate(String serviceOrderDate) {
		this.serviceOrderDate = serviceOrderDate;
	}

	public String getPlaceOrderDate() {
		return placeOrderDate;
	}

	public void setPlaceOrderDate(String placeOrderDate) {
		this.placeOrderDate = placeOrderDate;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getVendorOrderStatus() {
		return vendorOrderStatus;
	}

	public void setVendorOrderStatus(String vendorOrderStatus) {
		this.vendorOrderStatus = vendorOrderStatus;
	}
	
	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalOrderAmount() {
		return totalOrderAmount;
	}

	public void setTotalOrderAmount(Double totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}

	public String getPackageMenuItems() {
		return packageMenuItems;
	}

	public void setPackageMenuItems(String packageMenuItems) {
		this.packageMenuItems = packageMenuItems;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getMasterServiceUUID() {
		return masterServiceUUID;
	}

	public void setMasterServiceUUID(String masterServiceUUID) {
		this.masterServiceUUID = masterServiceUUID;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public List<Map<String, String>> getOrder() {
		return order;
	}

	public void setOrder(List<Map<String, String>> order) {
		this.order = order;
	}

	public String getOrderUUID() {
		return orderUUID;
	}

	public void setOrderUUID(String orderUUID) {
		this.orderUUID = orderUUID;
	}

	public Integer getAllowed() {
		return allowed;
	}

	public void setAllowed(Integer allowed) {
		this.allowed = allowed;
	}

	public String getServiceImage() {
		return serviceImage;
	}

	public void setServiceImage(String serviceImage) {
		this.serviceImage = serviceImage;
	}

	public List<UserPackageItemsDTO> getPackageItems() {
		return packageItems;
	}

	public void setPackageItems(List<UserPackageItemsDTO> packageItems) {
		this.packageItems = packageItems;
	}

	public List<SurpriseDetailsDTO> getSurpriseList() {
		return surpriseList;
	}

	public void setSurpriseList(List<SurpriseDetailsDTO> surpriseList) {
		this.surpriseList = surpriseList;
	}

	public String getSurpriseInstructions() {
		return surpriseInstructions;
	}

	public void setSurpriseInstructions(String surpriseInstructions) {
		this.surpriseInstructions = surpriseInstructions;
	}

	public String getSurpriseDetails() {
		return surpriseDetails;
	}

	public void setSurpriseDetails(String surpriseDetails) {
		this.surpriseDetails = surpriseDetails;
	}

	public String getIsUserArrived() {
		return isUserArrived;
	}

	public void setIsUserArrived(String isUserArrived) {
		this.isUserArrived = isUserArrived;
	}

	
}
