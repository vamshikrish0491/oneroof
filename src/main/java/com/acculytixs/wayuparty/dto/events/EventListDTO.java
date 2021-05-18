package com.acculytixs.wayuparty.dto.events;

import java.math.BigInteger;

public class EventListDTO {

	private String eventName;
	
	private String eventLocation;
	
	private BigInteger date;
	
	private String time;
	
	private String day;
	
	private String month;
	
	private String eventImage;
	
	private String eventUUID;
	
	private String eventDate;
	
	private String eventHost;
	
	private String eventTimeSlots;
	
	private BigInteger bookingsOpen;
	
	private String dayDiff;
	
	

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public BigInteger getDate() {
		return date;
	}

	public void setDate(BigInteger date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getEventImage() {
		return eventImage;
	}

	public void setEventImage(String eventImage) {
		this.eventImage = eventImage;
	}

	public String getEventUUID() {
		return eventUUID;
	}

	public void setEventUUID(String eventUUID) {
		this.eventUUID = eventUUID;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventHost() {
		return eventHost;
	}

	public void setEventHost(String eventHost) {
		this.eventHost = eventHost;
	}

	public String getEventTimeSlots() {
		return eventTimeSlots;
	}

	public void setEventTimeSlots(String eventTimeSlots) {
		this.eventTimeSlots = eventTimeSlots;
	}

	public BigInteger getBookingsOpen() {
		return bookingsOpen;
	}

	public void setBookingsOpen(BigInteger bookingsOpen) {
		this.bookingsOpen = bookingsOpen;
	}

	public String getDayDiff() {
		return dayDiff;
	}

	public void setDayDiff(String dayDiff) {
		this.dayDiff = dayDiff;
	}

	
}
