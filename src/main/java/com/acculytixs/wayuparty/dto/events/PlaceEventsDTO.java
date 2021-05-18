package com.acculytixs.wayuparty.dto.events;

import com.acculytixs.wayuparty.annotations.Required;

public class PlaceEventsDTO {

	@Required(desc="vendorUUID")
	private String vendorUUID;
	
	@Required(desc="userUUID")
	private String userUUID;
	
	@Required(desc="eventUUID")
	private String eventUUID;
	
	@Required(desc="ticketType")
	private String ticketType;
	
	@Required(desc="categoryType")
	private String categoryType;
	
	@Required(desc="ticketAmount")
	private String ticketAmount;
	
	@Required(desc="quantity")
	private String quantity;
	
	@Required(desc="timeslot")
	private String timeslot;
	
	@Required(desc="currency")
	private String currency;
	
	private String paymentId;
	
	private String orderId;
	
	private String signature;
	
	

	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}

	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	public String getEventUUID() {
		return eventUUID;
	}

	public void setEventUUID(String eventUUID) {
		this.eventUUID = eventUUID;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(String ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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
	
	
	
	
}
