package com.acculytixs.wayuparty.entity.vendor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "coupon")
public class Coupon  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "user_uuid")
	private String userUUID;
	
	@Column(name = "coupon_name")
	private String couponName;
	
	@Column(name = "coupon_code")
	private String couponCode;
	
	@Column(name = "coupon_value")
	private String couponValue;
	
	@Column(name = "display_offer")
	private String displayOffer;
	
	@Column(name = "minimum_order")
	private String minimumOrder;
	
	@Column(name = "discount_type")
	private String discountType;
	
	@Column(name = "expire_date")
	private Date expireDate;
	
	@Column(name = "coupon_applicable")
	private String couponApplicable;
	
	@Column(name = "usage_limit")
	private String usageLimit;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

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

	public String getUsageLimit() {
		return usageLimit;
	}

	public void setUsageLimit(String usageLimit) {
		this.usageLimit = usageLimit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", userUUID=" + userUUID + ", couponName=" + couponName + ", couponCode="
				+ couponCode + ", couponValue=" + couponValue + ", displayOffer=" + displayOffer + ", minimumOrder="
				+ minimumOrder + ", discountType=" + discountType + ", expireDate=" + expireDate + ", couponApplicable="
				+ couponApplicable + ", usageLimit=" + usageLimit + "]";
	}
	
}
