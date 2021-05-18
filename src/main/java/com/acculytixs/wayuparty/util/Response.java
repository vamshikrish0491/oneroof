package com.acculytixs.wayuparty.util;

import org.apache.commons.lang.StringUtils;

public class Response<T> {

    private T object;
    private String error;
    private String response;
    private String responseMessage;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public String getError() {
    	if(StringUtils.isNotBlank(error)) {
    		return error;
    	}else {
    		return "";
    	}
        
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
	
}
