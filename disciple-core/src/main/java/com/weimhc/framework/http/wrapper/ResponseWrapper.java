package com.weimhc.framework.http.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2720265898792909453L;

	private static final String WARNING = "[WARNING]: ";
	private static final String ERROR = "[ERROR]: ";

	private List<String> messages = new ArrayList<String>();
	private Integer responseStatus;
	private Object responseBody;
	private Boolean hasError = Boolean.FALSE;

	public static ResponseWrapper newInstance() {
		return new ResponseWrapper();
	}

	public List<String> getMessages() {
		return messages;
	}

	public void addWarning(String warning) {
		messages.add(WARNING + warning);
	}

	public void addError(String error) {
		messages.add(ERROR + error);
		hasError = Boolean.TRUE;
	}

	public Boolean hasError() {
		return hasError;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}

	public Integer getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(Integer responseStatus) {
		this.responseStatus = responseStatus;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (String message : messages) {
			sb.append(message).append("\n");
		}
		sb.append("Status: ").append(responseStatus).append("\n");
		if (null != responseBody) {
			sb.append("Response Body: ").append(responseBody.toString());
		}

		return sb.toString();
	}
}
