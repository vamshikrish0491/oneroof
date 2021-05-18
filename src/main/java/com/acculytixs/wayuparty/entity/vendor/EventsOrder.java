package com.acculytixs.wayuparty.entity.vendor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "events_order")
public class EventsOrder implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "vendor_uuid")
	private String vendorUUID;
	
	@Column(name = "event_uuid")
	private String eventUUID;
	
	@Column(name = "user_uuid")
	private String userUUID;
	
	@Column(name = "order_code")
	private String orderCode;
	
	@Column(name = "ticket_type",columnDefinition = "TEXT default NULL")
	private String ticketType;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "event_amount")
	private Double eventAmount;
	
	@Column(name = "total_amount")
	private Double totalAmount;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "event_date")
	private Date eventDate;
	
	@Column(name = "time_slot",columnDefinition = "TEXT")
	private String timeSlot;
	
	@Column(name = "is_user_arrived")
	private String isUserArrived;
	
	@Column(name = "qr_code",columnDefinition = "TEXT")
	private String qrCode;
	
	@Column(name = "created_date")
	private Date createdDate;;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "status")
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getEventAmount() {
		return eventAmount;
	}

	public void setEventAmount(Double eventAmount) {
		this.eventAmount = eventAmount;
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

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getIsUserArrived() {
		return isUserArrived;
	}

	public void setIsUserArrived(String isUserArrived) {
		this.isUserArrived = isUserArrived;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
