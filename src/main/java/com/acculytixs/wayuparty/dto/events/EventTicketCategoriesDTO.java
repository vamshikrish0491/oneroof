package com.acculytixs.wayuparty.dto.events;

public class EventTicketCategoriesDTO {

	private String ticketType;
	
	private Integer ticketAmount;
	
	private String currency;
	
	private Integer maxBookingAllowed;
	
	private String eventUUID;

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public Integer getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(Integer ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public Integer getMaxBookingAllowed() {
		return maxBookingAllowed;
	}

	public void setMaxBookingAllowed(Integer maxBookingAllowed) {
		this.maxBookingAllowed = maxBookingAllowed;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getEventUUID() {
		return eventUUID;
	}

	public void setEventUUID(String eventUUID) {
		this.eventUUID = eventUUID;
	}
	
	
}
