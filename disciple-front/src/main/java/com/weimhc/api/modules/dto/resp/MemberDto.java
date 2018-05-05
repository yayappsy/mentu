/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weimhc.modules.member.entity.MemberRank;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 会员DTO
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class MemberDto extends UserInfoDto {

	public static final String[] DEFAULT_TAGS = { "member" };

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095501047336970672L;

	/**
	 * 会员等级
	 */
	private MemberRank memberRank;

	/**
	 * 会员级别
	 * 
	 * @return the memberRank
	 */
	@ApiIgnore
	@JsonIgnore
	public MemberRank getMemberRank() {
		return memberRank;
	}

	/**
	 * 会员级别
	 * 
	 * @param memberRank
	 *            the memberRank to set
	 */
	public void setMemberRank(MemberRank memberRank) {
		this.memberRank = memberRank;
	}

	/**
	 * 返回会员级别名称
	 * 
	 * @return the memberRankName
	 */
	@ApiModelProperty(value = "会员级别名称")
	public String getMemberRankName() {
		if (getMemberRank() != null) {
			return getMemberRank().getName();
		}
		return "VIP1";
	}
}