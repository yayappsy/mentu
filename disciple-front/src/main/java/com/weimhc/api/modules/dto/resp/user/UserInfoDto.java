/**
 * 
 */
package com.weimhc.api.modules.dto.resp.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.dto.AbstractIdDto;
import com.weimhc.framework.utils.UploadUtils;
import com.weimhc.modules.base.entity.Education;
import com.weimhc.modules.base.entity.Nation;
import com.weimhc.modules.base.entity.Region;
import com.weimhc.modules.base.entity.ReligiousBelief;
import com.weimhc.modules.sys.utils.DictUtils;
import com.weimhc.modules.user.entity.UserCorpInfo;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.format.annotation.DateTimeFormat;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * 用户信息 DTO
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class UserInfoDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6707586013228500755L;
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
	 * 手机
	 * 
	 */
	private String mobile;
	/**
	 * 邮箱
	 * 
	 */
	private String email;
	/**
	 * 会员头像
	 */
	private String avatar;
	/**
	 * 生日
	 * 
	 */
	private Date birth;
	/**
	 * 性别
	 * 
	 */
	private String gender;
	/**
	 * 真实姓名
	 * 
	 */
	private String name;

	/**
	 * qq
	 * 
	 */
	private String qq;
	/**
	 * 微信号
	 */
	private String wechatId;

	/**
	 * 身份证号
	 */
	private String identityCard;
	/**
	 * 籍贯
	 */
	private Region birthplace;
	/**
	 * 籍贯名称
	 */
	private String birthplaceName;
	/**
	 * 居住城市
	 */
	private Region residence;
	/**
	 * 居住低名称
	 */
	private String residenceName;
	/**
	 * 民族
	 */
	private Nation nation;
	/**
	 * 宗教信仰
	 * 
	 */
	private ReligiousBelief religiousBelief;

	/**
	 * 教育程度
	 */
	private Education education;

	/**
	 * 期待结识
	 */
	private String lookForwardToMeet;

	/**
	 * 企业信息
	 */
	private UserCorpInfo userCorpInfo;

	/**
	 * 第三方IM账号
	 */
	private String imUsername;

	/**
	 * 个人签名
	 */
	private String personalSignature;

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
	 * 获取会员手机号
	 * 
	 * @return the mobile
	 */
	@ApiModelProperty(value = "会员手机号", example = "18888888888")
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置会员手机号
	 * 
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	 * 获取会员邮箱
	 * 
	 * @return the email
	 */
	@ApiModelProperty(value = "会员邮箱", example = "123@qq.com")
	public String getEmail() {
		return email;
	}

	/**
	 * 设置会员邮箱
	 * 
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * 获取生日
	 * 
	 * @return 生日
	 */
	@ApiIgnore
	@ApiModelProperty(value = "生日")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getBirth() {
		return birth;
	}

	/**
	 * 设置生日
	 * 
	 * @param birth
	 *            生日
	 */
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	/**
	 * 设置年龄
	 * 
	 * @param 年龄
	 */
	@ApiModelProperty(value = "年龄")
	public int getAge() {
		if (birth != null) {
			DateTime now = new DateTime();
			DateTime start = new DateTime(birth);
			return Years.yearsBetween(start, now).getYears();
		}
		return 0;
	}

	/**
	 * 获取性别
	 * 
	 * @return 性别
	 */
	@ApiModelProperty(value = "性别")
	public String getGender() {
		return gender;
	}

	@ApiModelProperty(value = "性别描述")
	public String getGenderDescription() {
		return DictUtils.getDictLabel(gender, "sex", "");
	}

	/**
	 * 设置性别
	 * 
	 * @param gender
	 *            性别
	 */
	public void setGender(String gender) {
		this.gender = gender;
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
	 * 返回身份证号
	 * 
	 * @return the identityCard
	 */
	@ApiModelProperty(value = "身份证号")
	public String getIdentityCard() {
		return identityCard;
	}

	/**
	 * 设置身份证号
	 * 
	 * @param identityCard
	 *            the identityCard to set
	 */
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	/**
	 * 返回居住地
	 * 
	 * @return the residence
	 */
	@ApiIgnore
	@JsonIgnore
	public Region getResidence() {
		return residence;
	}

	/**
	 * 设置居住地
	 * 
	 * @param residence
	 *            the residence to set
	 */
	public void setResidence(Region residence) {
		this.residence = residence;
	}

	/**
	 * 返回 居住地id
	 * 
	 * @return the residenceId
	 */
	@ApiModelProperty(value = "居住地id")
	public String getResidenceId() {
		if (this.residence != null) {
			return getResidence().getId();
		}
		return "";
	}

	/**
	 * 返回 居住地名称
	 * 
	 * @return the residenceId
	 */
	@ApiModelProperty(value = "居住地名称")
	public String getResidenceName() {
		return residenceName;
	}

	/**
	 * 设置 居住地名称
	 * 
	 * @param residenceName
	 *            the residenceName to set
	 */
	public void setResidenceName(String residenceName) {
		this.residenceName = residenceName;
	}

	/**
	 * 返回 籍贯
	 * 
	 * @return the birthplace
	 */
	@JsonIgnore
	@ApiIgnore
	public Region getBirthplace() {
		return birthplace;
	}

	/**
	 * 设置 籍贯
	 * 
	 * @param birthplace
	 *            the birthplace to set
	 */
	public void setBirthplace(Region birthplace) {
		this.birthplace = birthplace;
	}

	/**
	 * 返回 籍贯id
	 * 
	 * @return the birthplaceId
	 */
	@ApiModelProperty(value = "籍贯id")
	public String getBirthplaceId() {
		if (this.birthplace != null) {
			return birthplace.getId();
		}
		return "";
	}

	/**
	 * 返回 籍贯名称
	 * 
	 * @return the birthplaceName
	 */
	@ApiModelProperty(value = "贯名称")
	public String getBirthplaceName() {
		return birthplaceName;
	}

	/**
	 * 设置 籍贯名称
	 * 
	 * @param birthplaceName
	 *            the birthplaceName to set
	 */
	public void setBirthplaceName(String birthplaceName) {
		this.birthplaceName = birthplaceName;
	}

	/**
	 * 返回民族
	 * 
	 * @return the nation
	 */
	@ApiIgnore
	@JsonIgnore
	public Nation getNation() {
		return nation;
	}

	/**
	 * 设置民族
	 * 
	 * @param nation
	 *            the nation to set
	 */
	public void setNation(Nation nation) {
		this.nation = nation;
	}

	/**
	 * 返回民族id
	 * 
	 * @return the nationId
	 */
	@ApiModelProperty(value = "民族id")
	public String getNationId() {
		if (getNation() != null) {
			return getNation().getId();
		}
		return "";
	}

	/**
	 * 返回 民族名称
	 * 
	 * @return the nationName
	 */
	@ApiModelProperty(value = "民族名称")
	public String getNationName() {
		if (getNation() != null) {
			return getNation().getName();
		}
		return "";
	}

	/**
	 * 返回宗教信仰
	 * 
	 * @return the religiousBelief
	 */
	@ApiIgnore
	@JsonIgnore
	public ReligiousBelief getReligiousBelief() {
		return religiousBelief;
	}

	/**
	 * 设置宗教信仰
	 * 
	 * @param religiousBelief
	 *            the religiousBelief to set
	 */
	public void setReligiousBelief(ReligiousBelief religiousBelief) {
		this.religiousBelief = religiousBelief;
	}

	/**
	 * 返回宗教信仰id
	 * 
	 * @return the religiousBeliefId
	 */
	@ApiModelProperty(value = "宗教信仰id")
	public String getReligiousBeliefId() {
		if (getReligiousBelief() != null) {
			return getReligiousBelief().getId();
		}
		return "";
	}

	/**
	 * 返回宗教信仰名称
	 * 
	 * @return the religiousBeliefName
	 */
	@ApiModelProperty(value = "宗教信仰名称")
	public String getReligiousBeliefName() {
		if (getReligiousBelief() != null) {
			return getReligiousBelief().getName();
		}
		return "";
	}

	/**
	 * 返回 教育程度
	 * 
	 * @return the education
	 */
	@ApiIgnore
	@JsonIgnore
	public Education getEducation() {
		return education;
	}

	/**
	 * 设置 教育程度
	 * 
	 * @param education
	 *            the education to set
	 */
	public void setEducation(Education education) {
		this.education = education;
	}

	/**
	 * 返回 教育程度
	 * 
	 * @return the educationId
	 */
	@ApiModelProperty(value = "教育程度id")
	public String getEducationId() {
		if (getEducation() != null) {
			return getEducation().getId();
		}
		return "";
	}

	/**
	 * 返回 教育程度
	 * 
	 * @return the educationName
	 */
	@ApiModelProperty(value = "教育程度名称")
	public String getEducationName() {
		if (getEducation() != null) {
			return getEducation().getName();
		}
		return "";
	}

	/**
	 * 获取qq
	 * 
	 * @return qq
	 */
	@ApiModelProperty(value = "qq号", example = "")
	public String getQq() {
		return qq;
	}

	/**
	 * 设置qq
	 * 
	 * @param qq
	 *            qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * 返回 微信号
	 * 
	 * @return the wechatId
	 */
	public String getWechatId() {
		return wechatId;
	}

	/**
	 * 设置 微信号
	 * 
	 * @param wechatId
	 *            the wechatId to set
	 */
	@ApiModelProperty(value = "微信号", example = "")
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	/**
	 * 返回 期待结识
	 * 
	 * @return the lookForwardToMeet
	 */
	@ApiModelProperty(value = "期待结识", example = "")
	public String getLookForwardToMeet() {
		return lookForwardToMeet;
	}

	/**
	 * 设置 期待结识
	 * 
	 * @param lookForwardToMeet
	 *            the lookForwardToMeet to set
	 */
	public void setLookForwardToMeet(String lookForwardToMeet) {
		this.lookForwardToMeet = lookForwardToMeet;
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
	 * 返回 第三方IM账号
	 * 
	 * @return the imUsername
	 */
	@ApiModelProperty(value = "第三方IM账号", example = "M_20170805021")
	public String getImUsername() {
		return imUsername;
	}

	/**
	 * 设置 第三方IM账号
	 * 
	 * @param imUsername
	 *            the imUsername to set
	 */
	public void setImUsername(String imUsername) {
		this.imUsername = imUsername;
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

	/**
	 * 返回 个人签名
	 * 
	 * @return the personalSignature
	 */
	@ApiModelProperty(value = "个人签名", example = "")
	public String getPersonalSignature() {
		return personalSignature;
	}

	/**
	 * 设置 个人签名
	 * 
	 * @param personalSignature
	 *            the personalSignature to set
	 */
	public void setPersonalSignature(String personalSignature) {
		this.personalSignature = personalSignature;
	}
}