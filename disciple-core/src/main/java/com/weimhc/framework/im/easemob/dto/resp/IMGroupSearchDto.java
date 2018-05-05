package com.weimhc.framework.im.easemob.dto.resp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weimhc.framework.dto.AbstractIdDto;

public class IMGroupSearchDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5902968360361174849L;
	/**
	 * 群名称
	 */
	private String name;
	/**
	 * 群描述
	 */
	private String description;
	/**
	 * 群组类型：true：公开群，false：私有群。
	 */
	private boolean ifPubulic;
	/**
	 * 是否需要群主或者群管理员审批
	 */
	private boolean membersOnly;
	/**
	 * 是否允许群成员邀请别人加入此群
	 */
	private boolean allowInvites;
	/**
	 * 群成员上限
	 */
	private int maxUsers;
	/**
	 * 现有成员总数
	 */
	private int affiliationsCount;
	/**
	 * 现有成员列表
	 */
	private List<AffiliationsBean> affiliations;

	/**
	 * 群id
	 * 
	 * @return
	 */
	@Override
	public String getId() {
		return super.getId();
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置 群描述
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 返回 是否公开
	 * 
	 * @return the ifPubulic
	 */
	@JsonProperty("public")
	public boolean isIfPubulic() {
		return ifPubulic;
	}

	/**
	 * 设置 是否公开
	 * 
	 * @param ifPubulic
	 *            the ifPubulic to set
	 */
	public void setIfPubulic(boolean ifPubulic) {
		this.ifPubulic = ifPubulic;
	}

	/**
	 * 返回 是否需要群主或者群管理员审批
	 * 
	 * @return the membersOnly
	 */
	@JsonProperty("membersonly")
	public boolean isMembersOnly() {
		return membersOnly;
	}

	/**
	 * 设置 是否需要群主或者群管理员审批
	 * 
	 * @param membersOnly
	 *            the membersOnly to set
	 */
	public void setMembersOnly(boolean membersOnly) {
		this.membersOnly = membersOnly;
	}

	/**
	 * 返回 是否允许群成员邀请别人加入此群
	 * 
	 * @return the allowInvites
	 */
	@JsonProperty("allowinvites")
	public boolean isAllowInvites() {
		return allowInvites;
	}

	/**
	 * 设置 是否允许群成员邀请别人加入此群
	 * 
	 * @param allowInvites
	 *            the allowInvites to set
	 */
	public void setAllowInvites(boolean allowInvites) {
		this.allowInvites = allowInvites;
	}

	/**
	 * 返回 群成员上限
	 * 
	 * @return the maxUsers
	 */
	@JsonProperty("maxusers")
	public int getMaxUsers() {
		return maxUsers;
	}

	/**
	 * 设置 群成员上限
	 * 
	 * @param maxUsers
	 *            the maxUsers to set
	 */
	public void setMaxUsers(int maxUsers) {
		this.maxUsers = maxUsers;
	}

	/**
	 * 返回 现有成员个数
	 * 
	 * @return the affiliationsCount
	 */
	@JsonProperty("affiliationscount")
	public int getAffiliationsCount() {
		return affiliationsCount;
	}

	/**
	 * 设置 现有成员个数
	 * 
	 * @param affiliationsCount
	 *            the affiliationsCount to set
	 */
	public void setAffiliationsCount(int affiliationsCount) {
		this.affiliationsCount = affiliationsCount;
	}

	public List<AffiliationsBean> getAffiliations() {
		return affiliations;
	}

	public void setAffiliations(List<AffiliationsBean> affiliations) {
		this.affiliations = affiliations;
	}

	public static class AffiliationsBean {
		/**
		 * owner : 13800138001 member : v3y0kf9arx
		 */

		private String owner;
		private String member;

		public String getOwner() {
			return owner;
		}

		public void setOwner(String owner) {
			this.owner = owner;
		}

		public String getMember() {
			return member;
		}

		public void setMember(String member) {
			this.member = member;
		}
	}
}
