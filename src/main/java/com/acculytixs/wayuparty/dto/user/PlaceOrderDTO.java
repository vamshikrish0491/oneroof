package com.acculytixs.wayuparty.dto.user;

import java.io.Serializable;
import java.util.List;

import com.acculytixs.wayuparty.annotations.Required;
import com.acculytixs.wayuparty.dto.services.MenuOfferingItemsDTO;
import com.acculytixs.wayuparty.dto.services.SurpriseDetailsDTO;

public class PlaceOrderDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Required(desc="userUUID")
	private String userUUID;
	
	@Required(desc="cartItems")
	private String cartItems;
	
	private String cartOrderCode;
	
	private String orderCode;
	
	private String serviceOrderDate;
	
	private String serviceTimeSlot;
	
	private String vendorUUID;
	
	private String vendorName;
	
	private String vendorLocation;
	
	private String serviceName;
	
	private String masterServiceUUID;
	
	private String serviceUUID;
	
	private Integer quantity;
	
	private Double rates; 
	
	private Double orderAmount;
	
	private String currency;
	
	private String qrCode;
	
	private String orderStatus;
	
	private String packageMenuItems;
	
	private String surpriseDetails;
	
	List<UserPackageItemsDTO> packageItems;
	
	List<SurpriseDetailsDTO> surpriseList;
	
	private String surpriseInstructions;
	
	private String paymentId;
	
	private String orderId;
	
	private String signature;
	
	private String placeOrderUUID;
	
	private String isUserArrived;
	
	private Integer rating;
	
	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	public String getCartItems() {
		return cartItems;
	}

	public void setCartItems(String cartItems) {
		this.cartItems = cartItems;
	}

	public String getCartOrderCode() {
		return cartOrderCode;
	}

	public void setCartOrderCode(String cartOrderCode) {
		this.cartOrderCode = cartOrderCode;
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

	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}
	
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Double getRates() {
		return rates;
	}

	public void setRates(Double rates) {
		this.rates = rates;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getMasterServiceUUID() {
		return masterServiceUUID;
	}

	public void setMasterServiceUUID(String masterServiceUUID) {
		this.masterServiceUUID = masterServiceUUID;
	}
	
	public String getServiceUUID() {
		return serviceUUID;
	}

	public void setServiceUUID(String serviceUUID) {
		this.serviceUUID = serviceUUID;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getVendorLocation() {
		return vendorLocation;
	}

	public void setVendorLocation(String vendorLocation) {
		this.vendorLocation = vendorLocation;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getServiceTimeSlot() {
		return serviceTimeSlot;
	}

	public void setServiceTimeSlot(String serviceTimeSlot) {
		this.serviceTimeSlot = serviceTimeSlot;
	}

	public String getPackageMenuItems() {
		return packageMenuItems;
	}

	public void setPackageMenuItems(String packageMenuItems) {
		this.packageMenuItems = packageMenuItems;
	}

	public String getSurpriseDetails() {
		return surpriseDetails;
	}

	public void setSurpriseDetails(String surpriseDetails) {
		this.surpriseDetails = surpriseDetails;
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

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getPlaceOrderUUID() {
		return placeOrderUUID;
	}

	public void setPlaceOrderUUID(String placeOrderUUID) {
		this.placeOrderUUID = placeOrderUUID;
	}

	public String getIsUserArrived() {
		return isUserArrived;
	}

	public void setIsUserArrived(String isUserArrived) {
		this.isUserArrived = isUserArrived;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	
}
