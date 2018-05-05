/**
 * 
 */
package com.weimhc.framework.dto;

import java.io.Serializable;

/**
 * 
 * 返回的Dto的基类
 * <P>
 * 使用 JsonIgnore 避免生成额外的josn
 * </P>
 * 
 * @author szuo
 * @see com.fasterxml.jackson.annotation.JsonIgnore
 */
public abstract class AbstractDto implements Serializable {

	private static final long serialVersionUID = 5328468600696957113L;

}
