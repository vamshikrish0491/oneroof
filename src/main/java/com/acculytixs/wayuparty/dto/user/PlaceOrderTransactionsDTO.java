package com.acculytixs.wayuparty.dto.user;

import java.io.Serializable;

public class PlaceOrderTransactionsDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String transactionUUID;
	
	private String vendorUUID;
	
	private String userUUID;
	
	private String cartOrderCode;
	
	private Double amount;
	
	private String currency;
	
	private String paymentId;
	
	private String orderId;
	
	private String signature;
	
	private String paymentDate;
	
	

	public String getTransactionUUID() {
		return transactionUUID;
	}

	public void setTransactionUUID(String transactionUUID) {
		this.transactionUUID = transactionUUID;
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

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

}
