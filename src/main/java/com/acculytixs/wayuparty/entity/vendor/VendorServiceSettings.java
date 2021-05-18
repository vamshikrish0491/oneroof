package com.acculytixs.wayuparty.entity.vendor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vendor_service_settings")
public class VendorServiceSettings implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "vendor_uuid")
	private String vendorUUID;
	
	@Column(name = "service_uuid")
	private String serviceUUID;
	
	
	@Column(name = "is_entry_ratio_enabled")
	private String isEntryRatioEnabled;
	
	@Column(name = "men_entry_ratio")
	private Integer menEntryRatio;
	
	@Column(name = "women_entry_ratio")
	private Integer womenEntryRatio;
	
	@Column(name = "is_cancel_order_enabled")
	private String isCancelOrderEnabled;
	
	@Column(name = "cancel_order_value")
	private Integer cancelOrderValue;
	
	@Column(name = "cancel_order_type")
	private String cancelOrderType;
	
	@Column(name = "is_reschedule_order_enabled")
	private String isRescheduleOrderEnabled;
	
	@Column(name = "reschedule_order_value")
	private Integer rescheduleOrderValue;
	
	@Column(name = "reschedule_order_type")
	private String rescheduleOrderType;
	
	@Column(name = "is_order_prior_booking_enabled")
	private String isOrderPriorBookingEnabled;
	
	@Column(name = "order_prior_booking_value")
	private Integer orderPriorBookingValue;
	
	@Column(name = "order_prior_booking_type")
	private String orderPriorBookingType;
	
	@Column(name = "order_approval")
	private String orderApproval;
	
	

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

	public String getServiceUUID() {
		return serviceUUID;
	}

	public void setServiceUUID(String serviceUUID) {
		this.serviceUUID = serviceUUID;
	}

	public String getIsEntryRatioEnabled() {
		return isEntryRatioEnabled;
	}

	public void setIsEntryRatioEnabled(String isEntryRatioEnabled) {
		this.isEntryRatioEnabled = isEntryRatioEnabled;
	}

	public Integer getMenEntryRatio() {
		return menEntryRatio;
	}

	public void setMenEntryRatio(Integer menEntryRatio) {
		this.menEntryRatio = menEntryRatio;
	}

	public Integer getWomenEntryRatio() {
		return womenEntryRatio;
	}

	public void setWomenEntryRatio(Integer womenEntryRatio) {
		this.womenEntryRatio = womenEntryRatio;
	}

	public String getIsCancelOrderEnabled() {
		return isCancelOrderEnabled;
	}

	public void setIsCancelOrderEnabled(String isCancelOrderEnabled) {
		this.isCancelOrderEnabled = isCancelOrderEnabled;
	}

	public Integer getCancelOrderValue() {
		return cancelOrderValue;
	}

	public void setCancelOrderValue(Integer cancelOrderValue) {
		this.cancelOrderValue = cancelOrderValue;
	}

	public String getCancelOrderType() {
		return cancelOrderType;
	}

	public void setCancelOrderType(String cancelOrderType) {
		this.cancelOrderType = cancelOrderType;
	}

	public String getIsRescheduleOrderEnabled() {
		return isRescheduleOrderEnabled;
	}

	public void setIsRescheduleOrderEnabled(String isRescheduleOrderEnabled) {
		this.isRescheduleOrderEnabled = isRescheduleOrderEnabled;
	}

	public Integer getRescheduleOrderValue() {
		return rescheduleOrderValue;
	}

	public void setRescheduleOrderValue(Integer rescheduleOrderValue) {
		this.rescheduleOrderValue = rescheduleOrderValue;
	}

	public String getRescheduleOrderType() {
		return rescheduleOrderType;
	}

	public void setRescheduleOrderType(String rescheduleOrderType) {
		this.rescheduleOrderType = rescheduleOrderType;
	}

	public String getIsOrderPriorBookingEnabled() {
		return isOrderPriorBookingEnabled;
	}

	public void setIsOrderPriorBookingEnabled(String isOrderPriorBookingEnabled) {
		this.isOrderPriorBookingEnabled = isOrderPriorBookingEnabled;
	}

	public Integer getOrderPriorBookingValue() {
		return orderPriorBookingValue;
	}

	public void setOrderPriorBookingValue(Integer orderPriorBookingValue) {
		this.orderPriorBookingValue = orderPriorBookingValue;
	}

	public String getOrderPriorBookingType() {
		return orderPriorBookingType;
	}

	public void setOrderPriorBookingType(String orderPriorBookingType) {
		this.orderPriorBookingType = orderPriorBookingType;
	}

	public String getOrderApproval() {
		return orderApproval;
	}

	public void setOrderApproval(String orderApproval) {
		this.orderApproval = orderApproval;
	}
	
	
	

}
