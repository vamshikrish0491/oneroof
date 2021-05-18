package com.acculytixs.wayuparty.dto.user;

import com.acculytixs.wayuparty.annotations.Required;

public class RescheduleOrderDTO {

	@Required(desc="serviceOrderDate")
	private String serviceOrderDate;
	
	@Required(desc="serviceTimeSlot")
	private String serviceTimeSlot;
	
	@Required(desc="orderUUID")
	private String orderUUID;

	public String getServiceOrderDate() {
		return serviceOrderDate;
	}

	public void setServiceOrderDate(String serviceOrderDate) {
		this.serviceOrderDate = serviceOrderDate;
	}

	public String getServiceTimeSlot() {
		return serviceTimeSlot;
	}

	public void setServiceTimeSlot(String serviceTimeSlot) {
		this.serviceTimeSlot = serviceTimeSlot;
	}

	public String getOrderUUID() {
		return orderUUID;
	}

	public void setOrderUUID(String orderUUID) {
		this.orderUUID = orderUUID;
	}
	
	
}
