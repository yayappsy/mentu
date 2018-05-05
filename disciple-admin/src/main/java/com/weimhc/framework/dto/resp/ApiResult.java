/**
 * 
 */
package com.weimhc.framework.dto.resp;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
@ApiModel
public class ApiResult<T> implements Serializable {

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

	public ApiResult(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ApiResult(int code, String message) {
		this(code, message, null);
	}

	public ApiResult() {
	}

	/**
	 * 创建默认返回信息
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public static <T> ApiResult<T> error(int code, String message) {
		return new ApiResult<>(code, message);
	}

	/**
	 * 创建默认返回信息
	 * 
	 * @param message
	 * @return
	 */
	public static <T> ApiResult<T> error(String message) {
		return ApiResult.error(ERROR, message);
	}

	/**
	 * 创建默认返回信息
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public static <T> ApiResult<T> success() {
		return ApiResult.success();
	}

	/**
	 * 创建 成功信息
	 * 
	 * @param message
	 * @return
	 */
	public static <T> ApiResult<T> success(String message) {
		return new ApiResult<>(SUCCESS, message);
	}

	/**
	 * 获取 返回代码
	 * 
	 * @return the code
	 */
	@ApiModelProperty("返回代码")
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
	@ApiModelProperty("返回消息")
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
	@ApiModelProperty(value = "返回数据", name = "data")
	@JsonInclude(Include.NON_NULL)
	public T getData() {
		return data;
	}

	/**
	 * 兼容其他格式
	 * 
	 * @return getData()的数据
	 */
	@ApiModelProperty(value = "返回数据,data的拷贝", name = "result")
	public T getResult() {
		return getData();
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

	/**
	 * 返回时间
	 * 
	 * @return the time
	 */
	@ApiModelProperty("返回时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTime() {
		return new Date();
	}

}
