/**
 * 
 */
package com.weimhc.api.modules.dto.resp.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.dto.AbstractIdDto;
import com.weimhc.framework.utils.UploadUtils;
import com.weimhc.modules.user.entity.UserCorpInfo;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author shaozuo
 *
 */
public class SearchMemberDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5240336269653125900L;

	/**
	 * 诚信号
	 * 
	 */
	private String sn;
	/**
	 * 昵称
	 * 
	 */
	private String nickname;

	/**
	 * 会员头像
	 */
	private String avatar;

	/**
	 * 真实姓名
	 * 
	 */
	private String name;

	/**
	 * 企业信息
	 */
	private UserCorpInfo userCorpInfo;
	/**
	 * 诚信指数
	 */
	private int integrityIndex;

	/**
	 * 返回 会员编号
	 * 
	 * @return the sn
	 */
	@ApiModelProperty(value = "诚信号", example = "20170805021")
	public String getSn() {
		return sn;
	}

	/**
	 * 设置 会员编号
	 * 
	 * @param sn
	 *            the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 获取会员名称
	 * 
	 * @return the nickname
	 */
	@ApiModelProperty(value = "昵称", example = "aaa")
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置昵称s
	 * 
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获取会员头像
	 * 
	 * @return the avatar
	 */
	@ApiModelProperty(value = "会员头像")
	public String getAvatar() {
		if (StringUtils.isNotBlank(avatar)) {
			return UploadUtils.getAccessUrl(avatar);
		}
		return "";
	}

	/**
	 * 设置会员头像
	 * 
	 * @param avatar
	 *            the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * 返回真实姓名
	 * 
	 * @return the realname
	 */
	@ApiModelProperty(value = "真实姓名")
	public String getName() {
		return name;
	}

	/**
	 * 设置 真实姓名
	 * 
	 * @param realname
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 企业信息
	 * 
	 * @return the userCorpInfo
	 */
	@ApiIgnore
	@JsonIgnore
	public UserCorpInfo getUserCorpInfo() {
		return userCorpInfo;
	}

	/**
	 * 设置 企业信息
	 * 
	 * @param userCorpInfo
	 *            the userCorpInfo to set
	 */
	public void setUserCorpInfo(UserCorpInfo userCorpInfo) {
		this.userCorpInfo = userCorpInfo;
	}

	/**
	 * 获取企业名称
	 * 
	 * @return 企业名称
	 */
	@ApiModelProperty(value = "企业名称", example = "")
	public String getCorpName() {
		if (userCorpInfo != null) {
			return userCorpInfo.getCorpName();
		}
		return "";
	}

	/**
	 * 获取公司网站
	 * 
	 * @return 公司网站
	 */
	@ApiModelProperty(value = "公司网站", example = "")
	public String getCorpWebsite() {
		if (userCorpInfo != null) {
			return userCorpInfo.getCorpName();
		}
		return "";
	}

	/**
	 * 获取公司电话
	 * 
	 * @return 公司电话
	 */
	@ApiModelProperty(value = "公司电话", example = "")
	public String getCorpPhone() {
		if (userCorpInfo != null) {
			return userCorpInfo.getCorpName();
		}
		return "";
	}

	/**
	 * 获取公司职务
	 * 
	 * @return 公司职务
	 */
	@ApiModelProperty(value = "公司职务", example = "")
	public String getCorpJob() {
		if (userCorpInfo != null) {
			return userCorpInfo.getCorpName();
		}
		return "";
	}

	/**
	 * 返回 所属行业
	 * 
	 * @return the industryId
	 */
	@ApiModelProperty(value = "所属行业id")
	public String getIndustryId() {
		if (userCorpInfo != null) {
			return userCorpInfo.getCorpName();
		}
		return "";
	}

	/**
	 * 返回 所属行业
	 * 
	 * @return the industryName
	 */
	@ApiModelProperty(value = "所属行业名称")
	public String getCorpIndustryName() {
		if (userCorpInfo != null) {
			return userCorpInfo.getCorpName();
		}
		return "";
	}

	/**
	 * 获取公司办公地址
	 * 
	 * @return 公司办公地址
	 */
	@ApiModelProperty(value = "公司办公地址Id", example = "")
	public String getCorpAddressId() {
		if (userCorpInfo != null) {
			return userCorpInfo.getCorpName();
		}
		return "";
	}

	/**
	 * 返回 公司办公地址名称
	 * 
	 * @return the corpAddressName
	 */
	@ApiModelProperty(value = "公司办公地址名称", example = "")
	public String getCorpAddressName() {
		if (userCorpInfo != null) {
			return userCorpInfo.getCorpName();
		}
		return "";
	}

	/**
	 * 获取公司详细地址
	 * 
	 * @return 公司详细地址
	 */
	@ApiModelProperty(value = "公司详细地址", example = "")
	public String getCorpDetailedAddress() {
		if (userCorpInfo != null) {
			return userCorpInfo.getCorpName();
		}
		return "";
	}

	/**
	 * 返回 诚信指数
	 * 
	 * @return the integrityIndex
	 */
	@ApiModelProperty(value = "诚信指数", example = "5")
	public int getIntegrityIndex() {
		return 5;
	}

	/**
	 * 设置 诚信指数
	 * 
	 * @param integrityIndex
	 *            the integrityIndex to set
	 */
	public void setIntegrityIndex(int integrityIndex) {
		this.integrityIndex = integrityIndex;
	}
}
