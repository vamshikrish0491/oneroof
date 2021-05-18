package com.acculytixs.wayuparty.dto.services;

import java.io.Serializable;
import java.math.BigInteger;

import org.apache.commons.lang.StringUtils;

public class ServicesDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private BigInteger serviceId;
	
	private String serviceName;
	
	private String serviceDisplayName;
	
	private String serviceUUID;
	
	private String serviceImage;
	
	private String isEntryRatioEnabled;
	
	private Integer menRatio;
	
	private Integer womenRatio;
	

	public BigInteger getServiceId() {
		return serviceId;
	}

	public void setServiceId(BigInteger serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getServiceDisplayName() {
		return serviceDisplayName;
	}

	public void setServiceDisplayName(String serviceDisplayName) {
		this.serviceDisplayName = serviceDisplayName;
	}

	public String getServiceUUID() {
		return serviceUUID;
	}

	public void setServiceUUID(String serviceUUID) {
		this.serviceUUID = serviceUUID;
	}

	public String getServiceImage() {
		return serviceImage;
	}

	public void setServiceImage(String serviceImage) {
		this.serviceImage = serviceImage;
	}

	public String getIsEntryRatioEnabled() {
		if(StringUtils.isNotBlank(isEntryRatioEnabled)) {
			return isEntryRatioEnabled;
		}else{
			return "N";
		}
	}

	public void setIsEntryRatioEnabled(String isEntryRatioEnabled) {
		this.isEntryRatioEnabled = isEntryRatioEnabled;
	}

	public Integer getMenRatio() {
		return menRatio;
	}

	public void setMenRatio(Integer menRatio) {
		this.menRatio = menRatio;
	}

	public Integer getWomenRatio() {
		return womenRatio;
	}

	public void setWomenRatio(Integer womenRatio) {
		this.womenRatio = womenRatio;
	}

}
