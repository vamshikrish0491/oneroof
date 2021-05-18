package com.acculytixs.wayuparty.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_canceled_orders")
public class UserCanceledOrders implements Serializable{

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
	
	@Column(name = "canceled_order_amount")
	private Double canceledOrderAmount;
	
	@Column(name = "total_transaction_amount")
	private Double totalTransactionAmount;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "service_ordered_date")
	private Date serviceOrderedDate;
	
	@Column(name = "service_time_slot",columnDefinition = "TEXT default NULL")
	private String serviceTimeSlot;
	
	@Column(name = "payment_id",columnDefinition = "TEXT")
	private String paymentId;
	
	@Column(name = "order_id",columnDefinition = "TEXT")
	private String orderId;
	
	@Column(name = "signature",columnDefinition = "TEXT")
	private String signature;
	
	@Column(name = "payment_date")
	private Date paymentDate;
	
	@Column(name = "canceled_date")
	private Date canceledDate;
	
	@Column(name = "canceled_by")
	private String canceledBy;
	
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

	public Double getCanceledOrderAmount() {
		return canceledOrderAmount;
	}

	public void setCanceledOrderAmount(Double canceledOrderAmount) {
		this.canceledOrderAmount = canceledOrderAmount;
	}

	public Double getTotalTransactionAmount() {
		return totalTransactionAmount;
	}

	public void setTotalTransactionAmount(Double totalTransactionAmount) {
		this.totalTransactionAmount = totalTransactionAmount;
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

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getCanceledDate() {
		return canceledDate;
	}

	public void setCanceledDate(Date canceledDate) {
		this.canceledDate = canceledDate;
	}

	public String getCanceledBy() {
		return canceledBy;
	}

	public void setCanceledBy(String canceledBy) {
		this.canceledBy = canceledBy;
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
