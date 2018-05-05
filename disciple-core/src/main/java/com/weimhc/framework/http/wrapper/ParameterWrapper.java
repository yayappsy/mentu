package com.weimhc.framework.http.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * 查询参数，表单参数封装
 * 
 * @author shaozuo
 *
 */
public class ParameterWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7875555938825535736L;

	private List<NameValuePair> parameters = new ArrayList<NameValuePair>();

	public static ParameterWrapper newInstance() {
		return new ParameterWrapper();
	}

	public ParameterWrapper() {

	}

	public ParameterWrapper(List<NameValuePair> parameters) {
		if (parameters != null) {
			this.parameters.addAll(parameters);
		}
	}

	public ParameterWrapper addParameter(String key, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
			return this;
		}
		parameters.add(new BasicNameValuePair(key, value));
		return this;
	}

	public ParameterWrapper addParameters(List<BasicNameValuePair> parameters) {
		parameters.addAll(parameters);
		return this;
	}

	public List<NameValuePair> getParameters() {
		return parameters;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (NameValuePair query : parameters) {
			sb.append("[").append(query.getName()).append(":").append(query.getValue())
					.append("] ");
		}
		return sb.toString();
	}
}
