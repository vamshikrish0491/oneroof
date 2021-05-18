package com.acculytixs.wayuparty.dto.user;

public class UserOrdersDTO {

	private String clubName;
	
	private String clubLocation;
	
	private String orderDate;
	
	private String orderItems;
	
	private String orderRates;
	
	private Double totalAmount;
	
	private String currency;
	
	private String orderStatus;
	
	private Integer canceledOrdersCount;
	
	private String orderUUIDs;
	
	private String qrCode;
	
	private String orderDateStatus;
	
	private String orderItemsCanceled;
	
	private String orderItemsReschedule;
	
	private String userArrivedStatus;
	
	private String placeOrderCode;
	
	private Integer rating;
	
	private String isUserRated;
	
	

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	
	public String getClubLocation() {
		return clubLocation;
	}

	public void setClubLocation(String clubLocation) {
		this.clubLocation = clubLocation;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(String orderItems) {
		this.orderItems = orderItems;
	}

	public String getOrderRates() {
		return orderRates;
	}

	public void setOrderRates(String orderRates) {
		this.orderRates = orderRates;
	}
	
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getOrderUUIDs() {
		return orderUUIDs;
	}

	public void setOrderUUIDs(String orderUUIDs) {
		this.orderUUIDs = orderUUIDs;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getCanceledOrdersCount() {
		return canceledOrdersCount;
	}

	public void setCanceledOrdersCount(Integer canceledOrdersCount) {
		this.canceledOrdersCount = canceledOrdersCount;
	}
	
	public String getOrderDateStatus() {
		return orderDateStatus;
	}

	public void setOrderDateStatus(String orderDateStatus) {
		this.orderDateStatus = orderDateStatus;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getOrderItemsCanceled() {
		return orderItemsCanceled;
	}

	public void setOrderItemsCanceled(String orderItemsCanceled) {
		this.orderItemsCanceled = orderItemsCanceled;
	}

	public String getOrderItemsReschedule() {
		return orderItemsReschedule;
	}

	public void setOrderItemsReschedule(String orderItemsReschedule) {
		this.orderItemsReschedule = orderItemsReschedule;
	}

	public String getUserArrivedStatus() {
		return userArrivedStatus;
	}

	public void setUserArrivedStatus(String userArrivedStatus) {
		this.userArrivedStatus = userArrivedStatus;
	}

	public String getPlaceOrderCode() {
		return placeOrderCode;
	}

	public void setPlaceOrderCode(String placeOrderCode) {
		this.placeOrderCode = placeOrderCode;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getIsUserRated() {
		return isUserRated;
	}

	public void setIsUserRated(String isUserRated) {
		this.isUserRated = isUserRated;
	}

	
	
}
