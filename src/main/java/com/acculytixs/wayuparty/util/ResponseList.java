package com.acculytixs.wayuparty.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ResponseList<T> {


	private List<T> data;
	private String response;
	private String responseMessage;
	private int recordsTotal;
	private int recordsFiltered;
	private String razorpayKeyId;
	private String razorpayKeySecretId;


	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getResponse()
	{
		return response;
	}

	public void setResponse(String response)
	{
		this.response = response;
	}

	public String getResponseMessage()
	{
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage)
	{
		this.responseMessage = responseMessage;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public String getRazorpayKeyId() {
		if(StringUtils.isNotBlank(razorpayKeyId)) {
			return razorpayKeyId;
		}else {
			return "";
		}
	}

	public void setRazorpayKeyId(String razorpayKeyId) {
		this.razorpayKeyId = razorpayKeyId;
	}

	public String getRazorpayKeySecretId() {
		if(StringUtils.isNotBlank(razorpayKeySecretId)) {
			return razorpayKeySecretId;
		}else {
			return "";
		}
	}

	public void setRazorpayKeySecretId(String razorpayKeySecretId) {
		this.razorpayKeySecretId = razorpayKeySecretId;
	}

	
	
}
