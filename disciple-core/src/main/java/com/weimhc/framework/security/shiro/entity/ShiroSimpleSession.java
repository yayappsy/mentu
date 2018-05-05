package com.weimhc.framework.security.shiro.entity;

import java.io.Serializable;

import com.thinkgem.javamg.common.persistence.DataEntity;

public class ShiroSimpleSession extends DataEntity<ShiroSimpleSession> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5606401160607500588L;

	/**
	 * session唯一标志
	 */
	private String sessionId;

	/**
	 * session类型
	 */
	private String sessionType;

	/***
	 * session
	 */
	private Serializable session;

	public Serializable entity() {
		return session;
	}

	/**
	 *
	 * 返回 session唯一标志
	 * 
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * 设置 session唯一标志
	 * 
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 *
	 * 返回 数据库存储的session
	 * 
	 * @return the session
	 */
	public void setSession(Serializable session) {
		this.session = session;
	}

	/**
	 *
	 * 返回 数据库存储的session
	 * 
	 * @return the session
	 */
	public Serializable getSession() {
		return session;
	}

	/**
	 *
	 * 返回 session类型
	 * 
	 * @return the sessionType
	 */
	public String getSessionType() {
		return sessionType;
	}

	/**
	 * 设置 session类型
	 * 
	 * @param sessionType
	 *            the sessionType to set
	 */
	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}

}
