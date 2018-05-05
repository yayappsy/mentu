/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.dto.AbstractIdDto;
import com.weimhc.framework.utils.UploadUtils;
import com.weimhc.modules.base.entity.*;
import com.weimhc.modules.sys.entity.Area;
import com.weimhc.modules.sys.utils.DictUtils;
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
	 * 会员号
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
	 * 身高
	 */
	private Double height;
	/**
	 * 体重
	 */
	private Double weight;
	/**
	 * 腰围
	 */
	private Double waistline;
	/**
	 * 胸围
	 */
	private Double bust;
	/**
	 * 臀围
	 */
	private Double hipline;

	/**
	 * 身份证号
	 */
	private String identityCard;
	/**
	 * 居住城市
	 */
	private Area residence;
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
	 * 职 业
	 */
	private BaseIndustry industry;

	/**
	 * 教育程度
	 */
	private Education education;

	/**
	 * 紧急联系人关系
	 */
	private Relationship relationship;

	/**
	 * 紧急联系人
	 */
	private String emergencyContact;
	/**
	 * 紧急联系电话
	 */
	private String emergencyContactPhone;

	/**
	 * 返回 会员编号
	 * 
	 * @return the sn
	 */
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
		return DictUtils.getDictLabel(gender, "sex", "男");
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
	 * 返回身高
	 * 
	 * @return the height
	 */
	@ApiModelProperty(value = "身高")
	public Double getHeight() {
		return height;
	}

	/**
	 * 设置身高
	 * 
	 * @param height
	 *            the height to set
	 */
	public void setHeight(Double height) {
		this.height = height;
	}

	/**
	 * 返回体重
	 * 
	 * @return the weight
	 */
	@ApiModelProperty(value = "体重")
	public Double getWeight() {
		return weight;
	}

	/**
	 * 设置体重
	 * 
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * 返回腰围
	 * 
	 * @return the waistline
	 */
	@ApiModelProperty(value = "腰围")
	public Double getWaistline() {
		return waistline;
	}

	/**
	 * 设置腰围
	 * 
	 * @param waistline
	 *            the waistline to set
	 */
	public void setWaistline(Double waistline) {
		this.waistline = waistline;
	}

	/**
	 * 获取胸围
	 * 
	 * @return the bust
	 */
	@ApiModelProperty(value = "胸围")
	public Double getBust() {
		return bust;
	}

	/**
	 * 设置胸围
	 * 
	 * @param bust
	 *            the bust to set
	 */
	public void setBust(Double bust) {
		this.bust = bust;
	}

	/**
	 * 获取臀围
	 * 
	 * @return the hipline
	 */
	@ApiModelProperty(value = "臀围")
	public Double getHipline() {
		return hipline;
	}

	/**
	 * 设置臀围
	 * 
	 * @param hipline
	 *            the hipline to set
	 */
	public void setHipline(Double hipline) {
		this.hipline = hipline;
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
	public Area getResidence() {
		return residence;
	}

	/**
	 * 设置居住地
	 * 
	 * @param residence
	 *            the residence to set
	 */
	public void setResidence(Area residence) {
		this.residence = residence;
	}

	/**
	 * 返回 居住地id
	 * 
	 * @return the residenceId
	 */
	@ApiModelProperty(value = "居住地id")
	public String getResidenceId() {
		if (getResidence() != null) {
			return getResidence().getId();
		}
		return "";
	}

	/**
	 * 返回 居住地父ids
	 * 
	 * @return the residenceId
	 */
	@ApiModelProperty(value = "居住地父ids")
	public String getResidenceParentIds() {
		if (getResidence() != null) {
			return getResidence().getParentIds();
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
		if (getResidence() != null) {
			return getResidence().getName();
		}
		return "";
	}

	/**
	 * 返回 居住地名称
	 * 
	 * @return the residenceId
	 */
	@ApiModelProperty(value = "居住地全称")
	public String getResidenceMergerName() {
		if (getResidence() != null) {
			return getResidence().getMergerName();
		}
		return "";
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
	 * 返回 职 业
	 * 
	 * @return the industry
	 */
	@ApiIgnore
	@JsonIgnore
	public BaseIndustry getIndustry() {
		return industry;
	}

	/**
	 * 返回 职 业
	 * 
	 * @return the industryId
	 */
	@ApiModelProperty(value = "职 业id")
	public String getIndustryId() {
		if (getIndustry() != null) {
			return getIndustry().getId();
		}
		return "";
	}

	/**
	 * 返回 职 业
	 * 
	 * @return the industryName
	 */
	@ApiModelProperty(value = "职 业名称")
	public String getIndustryName() {
		if (getIndustry() != null) {
			return getIndustry().getName();
		}
		return "";
	}

	/**
	 * 设置 职 业
	 * 
	 * @param industry
	 *            the industry to set
	 */
	public void setIndustry(BaseIndustry industry) {
		this.industry = industry;
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
	 * 返回 紧急联系人关系
	 * 
	 * @return the relationship
	 */
	@ApiIgnore
	@JsonIgnore
	public Relationship getRelationship() {
		return relationship;
	}

	/**
	 * 返回 紧急联系人关系
	 * 
	 * @return the relationshipId
	 */
	@ApiModelProperty(value = "紧急联系人关系id")
	public String getRelationshipId() {
		if (getRelationship() != null) {
			return getRelationship().getId();
		}
		return "";
	}

	/**
	 * 返回紧急联系人关系
	 * 
	 * @return the relationshipName
	 */
	@ApiModelProperty(value = "紧急联系人关系名称")
	public String getRelationshipName() {
		if (getRelationship() != null) {
			return getRelationship().getName();
		}
		return "";
	}

	/**
	 * 设置 紧急联系人关系
	 * 
	 * @param relationship
	 *            the relationship to set
	 */
	public void setRelationship(Relationship relationship) {
		this.relationship = relationship;
	}

	/**
	 * 返回 紧急联系人
	 * 
	 * @return the emergencyContact
	 */
	@ApiModelProperty(value = "紧急联系人")
	public String getEmergencyContact() {
		return emergencyContact;
	}

	/**
	 * 设置 紧急联系人
	 * 
	 * @param emergencyContact
	 *            the emergencyContact to set
	 */
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	/**
	 * 返回 紧急联系电话
	 * 
	 * @return the emergencyContactPhone
	 */
	@ApiModelProperty(value = "紧急联系电话")
	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}

	/**
	 * 设置 紧急联系电话
	 * 
	 * @param emergencyContactPhone
	 *            the emergencyContactPhone to set
	 */
	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}

}