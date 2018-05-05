/*
 * 
 * 
 * 
 */
package com.weimhc.framework.web;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.weimhc.framework.web.utils.MessageSourceUtils;

/**
 * 消息封装实体 后台提示消息使用国际化
 * 
 * 
 */
public class ResultMessage implements Serializable {

	private static final long serialVersionUID = 200525188639053296L;

	/**
	 * 类型
	 */
	public enum Type {
		/** 消息 */
		info,
		/** 成功 */
		success,

		/** 警告 */
		warning,

		/** 错误 */
		error,

		/** 正在加载 */
		loading
	}

	/** 类型 */
	private Type type;

	/** 国际化代码 */
	private String code;

	/** 内容 */
	private String content;

	private Object[] args;

	/**
	 * 初始化一个新创建的 ResultMessage 对象，使其表示一个空消息。
	 */
	public ResultMessage() {

	}

	/**
	 * 初始化一个新创建的 ResultMessage 对象
	 * 
	 * @param type
	 *            类型
	 * @param content
	 *            内容
	 */
	public ResultMessage(Type type, String content) {
		this.type = type;
		this.content = content;
	}

	/**
	 * @param type
	 *            类型
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 */
	public ResultMessage(Type type, String code, Object... args) {
		this.type = type;
		this.code = code;
		this.args = args;
		this.content = MessageSourceUtils.getMessage(code, args);
	}

	/**
	 * 返回提示消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 错误消息
	 */
	public static ResultMessage info(String code, Object... args) {
		return new ResultMessage(Type.info, code, args);
	}

	/**
	 * 返回成功消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 成功消息
	 */
	public static ResultMessage success(String code, Object... args) {
		return new ResultMessage(Type.success, code, args);
	}

	/**
	 * 返回警告消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 警告消息
	 */
	public static ResultMessage warn(String code, Object... args) {
		return new ResultMessage(Type.warning, code, args);
	}

	/**
	 * 返回错误消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 错误消息
	 */
	public static ResultMessage error(String code, Object... args) {
		return new ResultMessage(Type.error, code, args);
	}

	/**
	 * 返回正在加载消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 错误消息
	 */
	public static ResultMessage loading(String code, Object... args) {
		return new ResultMessage(Type.loading, code, args);
	}

	/**
	 * 获取类型
	 * 
	 * @return 类型
	 */
	public Type getType() {
		return type;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 *            类型
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	public String getContent() {
		if (StringUtils.isBlank(content)) {
			content = MessageSourceUtils.getMessage(code, args);
		}
		return content;
	}

	/**
	 * 设置内容
	 * 
	 * @param content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return MessageSourceUtils.getMessage(code, args);
	}

	/**
	 * 获取国际化代码
	 * 
	 * @return 获取国际化代码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置国际化代码
	 * 
	 * @param content
	 *            国际化代码
	 */
	public void setCode(String code) {
		this.code = code;
	}

}