/**
 * 
 */
package com.weimhc.modules.suggestion.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.modules.member.entity.Member;

/**
 * 建议Entity
 * 
 * @author lc
 * @version 2016-12-01
 */
public class Suggestion extends DataEntity<Suggestion> {

	private static final long serialVersionUID = 1L;
	/**
	 * 建议人
	 * 
	 */
	private Member member;
	/**
	 * 建议主题
	 * 
	 */
	private SuggestionSubject suggestionSubject;
	/**
	 * 建议人昵称
	 * 
	 */
	private String memberNickname;

	/**
	 * 建议详细内容
	 * 
	 */
	private String content;
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
	private SuggestionType suggestionType = SuggestionType.suggestion;

	public Suggestion() {
		super();
	}

	public Suggestion(String id) {
		super(id);
	}

	/**
	 * 获取建议人
	 * 
	 * @return 建议人
	 */

	public Member getMember() {
		return member;
	}

	/**
	 * 设置建议人
	 * 
	 * @param memberId
	 *            建议人
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * 获取建议主题
	 * 
	 * @return 建议主题
	 */
	public SuggestionSubject getSuggestionSubject() {
		return suggestionSubject;
	}

	/**
	 * 设置建议主题
	 * 
	 * @param suggestionSubjectId
	 *            建议主题
	 */
	public void setSuggestionSubject(SuggestionSubject suggestionSubject) {
		this.suggestionSubject = suggestionSubject;
	}

	/**
	 * 获取建议人昵称
	 * 
	 * @return 建议人昵称
	 */
	@Length(min = 0, max = 64)
	public String getMemberNickname() {
		return memberNickname;
	}

	/**
	 * 设置建议人昵称
	 * 
	 * @param memberNickname
	 *            建议人昵称
	 */
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	/**
	 * 获取建议详细内容
	 * 
	 * @return 建议详细内容
	 */
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
	@Length(min = 1, max = 20)
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
	@Length(min = 0, max = 255)
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
	 * 返回 邮箱
	 * 
	 * @return the email
	 */
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

}