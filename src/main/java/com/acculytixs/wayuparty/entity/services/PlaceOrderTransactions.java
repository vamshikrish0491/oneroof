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
@Table(name = "place_order_transactions")
public class PlaceOrderTransactions implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "vendor_uuid")
	private String vendorUUID;
	
	@Column(name = "user_uuid")
	private String userUUID;
	
	@Column(name = "cart_order_code")
	private String cartOrderCode;
	
	@Column(name = "event_order_code")
	private String eventOrderCode;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "payment_id",columnDefinition = "TEXT")
	private String paymentId;
	
	@Column(name = "order_id",columnDefinition = "TEXT")
	private String orderId;
	
	@Column(name = "signature",columnDefinition = "TEXT")
	private String signature;
	
	@Column(name = "payment_date")
	private Date paymentDate;
	
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

	public String getCartOrderCode() {
		return cartOrderCode;
	}

	public void setCartOrderCode(String cartOrderCode) {
		this.cartOrderCode = cartOrderCode;
	}
	
	public String getEventOrderCode() {
		return eventOrderCode;
	}

	public void setEventOrderCode(String eventOrderCode) {
		this.eventOrderCode = eventOrderCode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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
