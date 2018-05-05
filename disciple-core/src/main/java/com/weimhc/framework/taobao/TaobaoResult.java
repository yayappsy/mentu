/**
 * 
 */
package com.weimhc.framework.taobao;

import java.io.Serializable;

/**
 * 
 * api返回数据封装
 * <P>
 * 使用 JsonIgnore 避免生成额外的josn
 * </P>
 * 
 * @author szuo
 * @see com.fasterxml.jackson.annotation.JsonIgnore
 */
public class TaobaoResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8052566108151898248L;
	/**
	 * 操作成功的代码
	 */
	public static final String SUCCESS_MESSAGE_CODE = "front.common.success";

	/**
	 * 成功的返回代码
	 */
	public static final int SUCCESS = 0;
	/**
	 * 错误的的返回代码
	 */
	public static final int ERROR = -1;

	/**
	 * 返回代码 1 成功 0 失败
	 * <p>
	 * 默认为成功
	 * </p>
	 */
	private int code = SUCCESS;

	/**
	 * 请求成功失败说明
	 */
	private String message = "";
	/**
	 * 返回的数据
	 */
	private T data;

	public TaobaoResult(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public TaobaoResult(int code, String message) {
		this(code, message, null);
	}

	public TaobaoResult() {
	}

	/**
	 * 创建默认返回信息
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public static <T> TaobaoResult<T> error(int code, String message) {
		return new TaobaoResult<>(code, message);
	}

	/**
	 * 创建默认返回信息
	 * 
	 * @param message
	 * @return
	 */
	public static <T> TaobaoResult<T> error(String message) {
		return TaobaoResult.error(ERROR, message);
	}

	/**
	 * 获取 返回代码
	 * 
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 设定 返回代码
	 * 
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 获取 消息
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设定 消息
	 * 
	 * @param msg
	 *            the msg to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 获取 返回数据
	 * 
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * 设定 返回数据
	 * 
	 * @param data
	 *            the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

}
