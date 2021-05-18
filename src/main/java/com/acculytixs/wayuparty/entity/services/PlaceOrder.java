package com.acculytixs.wayuparty.entity.services;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "place_order")
public class PlaceOrder implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "vendor_uuid")
	private String vendorUUID;
	
	@Column(name = "user_uuid")
	private String userUUID;
	
	@Column(name = "order_code")
	private String orderCode;
	
	@Column(name = "cart_order_code")
	private String cartOrderCode;
	
	@Column(name = "service_uuid")
	private String serviceUUID;
	
	@Column(name = "master_service_uuid")
	private String masterServiceUUID;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "order_amount")
	private Double orderAmount;
	
	@Column(name = "total_amount")
	private Double totalAmount;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "service_ordered_date")
	private Date serviceOrderedDate;
	
	@Column(name = "service_time_slot",columnDefinition = "TEXT default NULL")
	private String serviceTimeSlot;
	
	@Column(name = "package_menu_items",columnDefinition = "TEXT default NULL")
	private String packageMenuItems;
	
	@Column(name = "surprise_details")
	private String surpriseDetails;
	
	@Column(name = "surprise_instructions",columnDefinition = "TEXT default NULL")
	private String surpriseInstructions;
	
	@Column(name = "order_status")
	private String orderStatus;
	
	@Column(name = "order_status_modified_date")
	private Date orderStatusModifiedDate;
	
	@Column(name = "is_user_arrived")
	private String isUserArrived;
	
	@Column(name = "is_vendor_canceled")
	private String isVendorCanceled;
	
	@Column(name = "is_user_canceled")
	private String isUserCanceled;
	
	@Column(name = "order_canceled_date")
	private Date orderCanceledDate;
	
	@Column(name = "qr_code",columnDefinition = "TEXT default NULL")
	private String qrCode;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "created_date")
	private Date createdDate;
	
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
	
	public String getCartOrderCode() {
		return cartOrderCode;
	}

	public void setCartOrderCode(String cartOrderCode) {
		this.cartOrderCode = cartOrderCode;
	}

	public String getServiceUUID() {
		return serviceUUID;
	}

	public void setServiceUUID(String serviceUUID) {
		this.serviceUUID = serviceUUID;
	}

	public String getMasterServiceUUID() {
		return masterServiceUUID;
	}

	public void setMasterServiceUUID(String masterServiceUUID) {
		this.masterServiceUUID = masterServiceUUID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
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

	public Date getServiceOrderedDate() {
		return serviceOrderedDate;
	}

	public void setServiceOrderedDate(Date serviceOrderedDate) {
		this.serviceOrderedDate = serviceOrderedDate;
	}

	public String getServiceTimeSlot() {
		return serviceTimeSlot;
	}

	public void setServiceTimeSlot(String serviceTimeSlot) {
		this.serviceTimeSlot = serviceTimeSlot;
	}

	public String getPackageMenuItems() {
		return packageMenuItems;
	}

	public void setPackageMenuItems(String packageMenuItems) {
		this.packageMenuItems = packageMenuItems;
	}

	public String getSurpriseDetails() {
		return surpriseDetails;
	}

	public void setSurpriseDetails(String surpriseDetails) {
		this.surpriseDetails = surpriseDetails;
	}

	public String getSurpriseInstructions() {
		return surpriseInstructions;
	}

	public void setSurpriseInstructions(String surpriseInstructions) {
		this.surpriseInstructions = surpriseInstructions;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderStatusModifiedDate() {
		return orderStatusModifiedDate;
	}

	public void setOrderStatusModifiedDate(Date orderStatusModifiedDate) {
		this.orderStatusModifiedDate = orderStatusModifiedDate;
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIsVendorCanceled() {
		return isVendorCanceled;
	}

	public void setIsVendorCanceled(String isVendorCanceled) {
		this.isVendorCanceled = isVendorCanceled;
	}

	public String getIsUserCanceled() {
		return isUserCanceled;
	}

	public void setIsUserCanceled(String isUserCanceled) {
		this.isUserCanceled = isUserCanceled;
	}

	public Date getOrderCanceledDate() {
		return orderCanceledDate;
	}

	public void setOrderCanceledDate(Date orderCanceledDate) {
		this.orderCanceledDate = orderCanceledDate;
	}
	

}
