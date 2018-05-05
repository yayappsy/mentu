/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

/**
 * 网站概况dto
 * 
 * @author lc
 * @version 2017-04-07
 */
public class StatsSiteLanguageDto extends StatsSiteDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4371093896275487489L;
	/**
	 * 语言环境
	 * 
	 */
	private String language;

	/**
	 * 获取语言环境
	 * 
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * 设置语言环境
	 * 
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

}