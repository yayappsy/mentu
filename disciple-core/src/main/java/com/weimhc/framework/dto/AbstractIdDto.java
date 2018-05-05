/**
 * 
 */
package com.weimhc.framework.dto;

/**
 * 
 * 返回的Dto的基类 包括id字段
 * <P>
 * 使用 JsonIgnore 避免生成额外的josn
 * </P>
 * 
 * @author szuo
 * @see com.fasterxml.jackson.annotation.JsonIgnore
 */
public abstract class AbstractIdDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8703115182008184339L;
	/**
	 */
	private String id;

	/**
	 * 获取 id
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设定 id
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
