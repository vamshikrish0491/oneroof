package com.acculytixs.wayuparty.dto.services;

import org.apache.commons.lang.StringUtils;

public class EntryRatioDTO {

	private String isEntryRatioEnabled;
	
	private Integer menRatio;
	
	private Integer womenRatio;

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
