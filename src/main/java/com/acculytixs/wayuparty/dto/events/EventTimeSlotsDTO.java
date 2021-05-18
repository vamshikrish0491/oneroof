package com.acculytixs.wayuparty.dto.events;

import java.util.List;

import com.acculytixs.wayuparty.dto.services.TimeSlotInfo;

public class EventTimeSlotsDTO {

	private String eventDate;
	
	private List<TimeSlotInfo> timeSlots;
	
	private String eventUUID;

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public List<TimeSlotInfo> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(List<TimeSlotInfo> timeSlots) {
		this.timeSlots = timeSlots;
	}

	public String getEventUUID() {
		return eventUUID;
	}

	public void setEventUUID(String eventUUID) {
		this.eventUUID = eventUUID;
	}
	
	
}
