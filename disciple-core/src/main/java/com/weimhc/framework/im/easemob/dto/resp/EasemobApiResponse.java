/**
 * 
 */
package com.weimhc.framework.im.easemob.dto.resp;

import java.io.Serializable;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Params;

/**
 * 环信返回 封装
 * 
 * @author laozh
 *
 */
public class EasemobApiResponse<T, D> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6195001832819737971L;

	private String action;

	private Params params;

	private String uri;

	private List<T> entities;

	private D data;

	private long timestamp;

	private int duration;

	private String cursor;

	private int count;

	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return this.action;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public Params getParams() {
		return this.params;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUri() {
		return this.uri;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}

	public List<T> getEntities() {
		return this.entities;
	}

	public void setData(D data) {
		this.data = data;
	}

	public D getData() {
		return this.data;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public String getCursor() {
		return this.cursor;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return this.count;
	}
}
