package com.acculytixs.wayuparty.dto.events;

public class EventCategoriesDTO {

	private String categoryName;
	
	private Integer minimumCost;
	
	private String currency;
	
	private String eventUUID;
	
	private String categoryType;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getMinimumCost() {
		return minimumCost;
	}

	public void setMinimumCost(Integer minimumCost) {
		this.minimumCost = minimumCost;
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

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	
	
}
