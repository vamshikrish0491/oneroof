package com.acculytixs.wayuparty.dto.services;

import java.util.List;

public class ServiceRescheduleDTO {
	
	private String serviceStartDate;
	
	private String serviceEndDate;
	
	private String serviceTimeSlots;

	private List<TimeSlotInfo> timeSlotList;
	
	private List<ServiceDateDTO> serviceDates;
	
	
	

	public String getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(String serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public String getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(String serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	public String getServiceTimeSlots() {
		return serviceTimeSlots;
	}

	public void setServiceTimeSlots(String serviceTimeSlots) {
		this.serviceTimeSlots = serviceTimeSlots;
	}

	public List<TimeSlotInfo> getTimeSlotList() {
		return timeSlotList;
	}

	public void setTimeSlotList(List<TimeSlotInfo> timeSlotList) {
		this.timeSlotList = timeSlotList;
	}

	public List<ServiceDateDTO> getServiceDates() {
		return serviceDates;
	}

	public void setServiceDates(List<ServiceDateDTO> serviceDates) {
		this.serviceDates = serviceDates;
	}
	
	
}
