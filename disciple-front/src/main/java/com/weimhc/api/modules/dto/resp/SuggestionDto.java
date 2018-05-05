/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weimhc.framework.dto.AbstractIdDto;
import com.weimhc.framework.web.utils.MessageSourceUtils;
import com.weimhc.modules.suggestion.entity.SuggestionType;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 建议Entity
 * 
 * @author lc
 * @version 2016-12-01
 */
public class SuggestionDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5347344582552113729L;

	/**
	 * 建议主题
	 * 
	 */
	private String suggestionSubjectId;

	/**
	 * 建议详细内容
	 * 
	 */
	private String content;
	/**
	 * 建议人姓名
	 * 
	 */
	private String name;
	/**
	 * 手机号
	 * 
	 */
	private String mobile;
	/**
	 * 邮箱
	 * 
	 */
	private String email;
	/**
	 * 建议图片
	 * 
	 */
	private String images;

	/**
	 * 建议类型
	 */
	private SuggestionType suggestionType;

	/**
	 * 获取建议主题
	 * 
	 * @return 建议主题
	 */
	@ApiModelProperty(value = "建议主题id", example = "1")
	@ApiIgnore
	@JsonIgnore
	public String getSuggestionSubjectId() {
		return suggestionSubjectId;
	}

	/**
	 * 设置建议主题
	 * 
	 * @param suggestionSubjectId
	 *            建议主题
	 */
	public void setSuggestionSubjectId(String suggestionSubjectId) {
		this.suggestionSubjectId = suggestionSubjectId;
	}

	/**
	 * 获取建议详细内容
	 * 
	 * @return 建议详细内容
	 */
	@ApiModelProperty(value = "建议详细内容", example = "1")
	public String getContent() {
		return content;
	}

	/**
	 * 设置建议详细内容
	 * 
	 * @param content
	 *            建议详细内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取手机号
	 * 
	 * @return 手机号
	 */
	@ApiModelProperty(value = "手机号", example = "1")
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机号
	 * 
	 * @param mobile
	 *            手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取建议图片
	 * 
	 * @return 建议图片
	 */
	@ApiModelProperty(value = "建议图片", example = "1")
	public String getImages() {
		return images;
	}

	/**
	 * 设置建议图片
	 * 
	 * @param images
	 *            建议图片
	 */
	public void setImages(String images) {
		this.images = images;
	}

	/**
	 * 返回 建议人姓名
	 * 
	 * @return the name
	 */
	@ApiModelProperty(value = "建议人姓名", example = "张三")
	public String getName() {
		return name;
	}

	/**
	 * 设置 建议人姓名
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 邮箱
	 * 
	 * @return the email
	 */
	@ApiModelProperty(value = "建议人姓名", example = "张三")
	public String getEmail() {
		return email;
	}

	/**
	 * 设置 邮箱
	 * 
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 返回 建议类型
	 * 
	 * @return the suggestionType
	 */
	public SuggestionType getSuggestionType() {
		return suggestionType;
	}

	/**
	 * 设置 建议类型
	 * 
	 * @param suggestionType
	 *            the suggestionType to set
	 */
	public void setSuggestionType(SuggestionType suggestionType) {
		this.suggestionType = suggestionType;
	}

	/**
	 * 获取配送状态描述
	 * 
	 * @return the statusDescribe
	 */
	@ApiModelProperty(value = "建议类型描述")
	public String getSuggestionTypeDescription() {
		if (getSuggestionType() != null) {
			return MessageSourceUtils.getMessage(
					"Suggestion.SuggestionType." + getSuggestionType());
		} else {
			return null;
		}

	}
}