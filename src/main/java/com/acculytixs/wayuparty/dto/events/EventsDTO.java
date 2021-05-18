package com.acculytixs.wayuparty.dto.events;

import java.util.List;

import com.acculytixs.wayuparty.dto.services.TimeSlotSchedulerInfo;
import com.acculytixs.wayuparty.util.FileInfo;

public class EventsDTO {

	private String eventName;
	
	private String eventType;
	
	private String eventLocation;
	
	private String eventHost;
	
	private String eventDate;
	
	private String startTime;
	
	private String endTime;
	
	private String musicType;
	
	private List<TimeSlotSchedulerInfo> timeSchedulerInfo;
	
	private List<EventsTicketInfoDTO> ticketItemsList;
	
	private String eventLanguage;
	
	private List<FileInfo> fileInfo;
	
	private String eventAddress;
	
	private String vendorUUID;
	
	private String eventUUID;
	
	private String displayImage;
	
	private String bannerImage;
	
	private String eventTimeSlots;
	
	private String eventTickets;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventHost() {
		return eventHost;
	}

	public void setEventHost(String eventHost) {
		this.eventHost = eventHost;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	
	public String getMusicType() {
		return musicType;
	}

	public void setMusicType(String musicType) {
		this.musicType = musicType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public List<TimeSlotSchedulerInfo> getTimeSchedulerInfo() {
		return timeSchedulerInfo;
	}

	public void setTimeSchedulerInfo(List<TimeSlotSchedulerInfo> timeSchedulerInfo) {
		this.timeSchedulerInfo = timeSchedulerInfo;
	}
	
	public List<EventsTicketInfoDTO> getTicketItemsList() {
		return ticketItemsList;
	}

	public void setTicketItemsList(List<EventsTicketInfoDTO> ticketItemsList) {
		this.ticketItemsList = ticketItemsList;
	}

	public String getEventLanguage() {
		return eventLanguage;
	}

	public void setEventLanguage(String eventLanguage) {
		this.eventLanguage = eventLanguage;
	}

	public List<FileInfo> getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(List<FileInfo> fileInfo) {
		this.fileInfo = fileInfo;
	}

	public String getEventAddress() {
		return eventAddress;
	}

	public void setEventAddress(String eventAddress) {
		this.eventAddress = eventAddress;
	}

	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}

	public String getEventUUID() {
		return eventUUID;
	}

	public void setEventUUID(String eventUUID) {
		this.eventUUID = eventUUID;
	}

	public String getDisplayImage() {
		return displayImage;
	}

	public void setDisplayImage(String displayImage) {
		this.displayImage = displayImage;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getEventTimeSlots() {
		return eventTimeSlots;
	}

	public void setEventTimeSlots(String eventTimeSlots) {
		this.eventTimeSlots = eventTimeSlots;
	}

	public String getEventTickets() {
		return eventTickets;
	}

	public void setEventTickets(String eventTickets) {
		this.eventTickets = eventTickets;
	}
	
	
}
