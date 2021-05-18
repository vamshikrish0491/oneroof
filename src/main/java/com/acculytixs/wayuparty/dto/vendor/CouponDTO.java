package com.acculytixs.wayuparty.dto.vendor;

import java.util.Date;

import javax.persistence.Column;

public class CouponDTO {
	
	private String couponName;
	private String couponCode;
	private String couponValue;
	private String displayOffer;
	private String minimumOrder;
	private String discountType;
	private Date expireDate;
	private String couponApplicable;
	private String userUUID;
	private String usageLimit;
	
	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}

	public String getDisplayOffer() {
		return displayOffer;
	}

	public void setDisplayOffer(String displayOffer) {
		this.displayOffer = displayOffer;
	}

	public String getMinimumOrder() {
		return minimumOrder;
	}

	public void setMinimumOrder(String minimumOrder) {
		this.minimumOrder = minimumOrder;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getCouponApplicable() {
		return couponApplicable;
	}

	public void setCouponApplicable(String couponApplicable) {
		this.couponApplicable = couponApplicable;
	}

	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	public String getUsageLimit() {
		return usageLimit;
	}

	public void setUsageLimit(String usageLimit) {
		this.usageLimit = usageLimit;
	}

	@Override
	public String toString() {
		return "CouponDTO [couponName=" + couponName + ", couponCode=" + couponCode + ", couponValue=" + couponValue
				+ ", displayOffer=" + displayOffer + ", minimumOrder=" + minimumOrder + ", discountType=" + discountType
				+ ", expireDate=" + expireDate + ", couponApplicable=" + couponApplicable + ", userUUID=" + userUUID
				+ ", usageLimit=" + usageLimit + "]";
	}



}
