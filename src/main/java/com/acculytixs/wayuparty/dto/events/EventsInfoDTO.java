package com.acculytixs.wayuparty.dto.events;

public class EventsInfoDTO {

	private String eventDate;
	
	private String timeSlot;
	
	private String eventUUID;
	
	private String eventTickets;
	
	private String currency;

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getEventTickets() {
		return eventTickets;
	}

	public void setEventTickets(String eventTickets) {
		this.eventTickets = eventTickets;
	}

	public String getEventUUID() {
		return eventUUID;
	}

	public void setEventUUID(String eventUUID) {
		this.eventUUID = eventUUID;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
