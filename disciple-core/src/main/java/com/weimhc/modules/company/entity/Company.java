/**
 * 
 */
package com.weimhc.modules.company.entity;

import com.weimhc.modules.user.entity.UserInfo;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.persistence.DataEntity;
import com.thinkgem.javamg.common.utils.Collections3;
import com.weimhc.modules.base.entity.BaseIndustry;
import com.weimhc.modules.industry.entity.Industry;
import com.weimhc.modules.sys.entity.Area;
import com.weimhc.modules.sys.entity.Role;
import org.springframework.web.multipart.MultipartFile;

/**
 * 企业Entity
 * @author lc
 * @version 2017-11-13
 */
public class Company extends DataEntity<Company> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 企业名称
	private String shortName;		// 企业简称
	private String mainBusinessDescription;		// 主营业务描述
	private String logo;		// 企业logo
	private Area area;		// 企业所在区域
	private String address;		// 企业详细地址
	private Industry industry;		// 所属行业
	private String size;		// 公司规模
	private String site;		// 公司网址
	private String email;		// 企业邮箱
	private String introduce;		// 企业简介
	private String phone;
	private CompanyType companyType;		// 公司类型
	private String organizationCode;		// 组织机构代码
	private Date foundingTime;		// 成立日期
	private String capital;		// 成立资本
	private String legalName;  //法律名称
	private Integer stage; //验证完成阶段
	private String proofData; //企业证明资料
	private String isPass; //是否通过审核
	private MultipartFile logoFile;//
	private String ids;		//企业ids

	private UserInfo userInfo;//
	private String homeShow;//

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public MultipartFile getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(MultipartFile logoFile) {
		this.logoFile = logoFile;
	}

	/**
	 * 拥有公司标签列表

	 */
	List<CompanyLabel> companyLabelList=Lists.newArrayList();
	private CompanyLabel companyLabel;
	
	public Company() {
		super();
	}

	public Company(String id){
		super(id);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public enum CompanyType{
		/**
		 * 合资企业
		 */
		joint,
		/**
		 * 独资企业
		 */
		owned,
		/**
		 * 国有企业
		 */
		stateOwned,
		/**
		 * 私营企业
		 */
		privateSector,
		/**
		 * 股份制企业
		 */
		jointStock,
		/**
		 * 有限责任企业
		 */
		limited;
	}

	@Length(min=1, max=255)
	@ApiModelProperty(value = "企业名称", example = "好汉科技")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getIds() {
		return ids;
	}

	public String getHomeShow() {
		return homeShow;
	}

	public void setHomeShow(String homeShow) {
		this.homeShow = homeShow;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@ApiModelProperty(value = "企业简称", example = "shortName")
	@Length(min=1, max=64)

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	@Length(min=1, max=255)
	public String getMainBusinessDescription() {
		return mainBusinessDescription;
	}

	public void setMainBusinessDescription(String mainBusinessDescription) {
		this.mainBusinessDescription = mainBusinessDescription;
	}

	@ApiModelProperty(value = "企业logo", example = "url://...")
	@Length(min=1, max=255)
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@NotNull()
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@ApiModelProperty(value = "企业详细地址", example = "北京海淀区中关村软件园")
	@Length(min=1, max=255)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@NotNull()
	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}
	
	@Length(min=1, max=64)
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@ApiModelProperty(value = "公司网址", example = "www.haohankeji.com")
	@Length(min=0, max=64)
	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	
	@Length(min=0, max=64)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	@Length(min=0, max=100)
	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}
	
	@Length(min=0, max=100)
	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getFoundingTime() {
		return foundingTime;
	}

	public void setFoundingTime(Date foundingTime) {
		this.foundingTime = foundingTime;
	}
	
	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * 获取法律名称
	 * @return the legalName
	 */
	public String getLegalName() {
		return legalName;
	}

	/**
	 * 设置法律名称
	 * @param legalName the legalName to set
	 */
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	/**
	 * 获取完成阶段
	 * @return the stage
	 */
	public Integer getStage() {
		return stage;
	}

	/**
	 * 设置完成阶段
	 * @param stage the stage to set
	 */
	public void setStage(Integer stage) {
		this.stage = stage;
	}

	/**
	 * 获取证明资料
	 * @return the proofData
	 */
	public String getProofData() {
		return proofData;
	}

	/**
	 * 设置证明资料
	 * @param proofData the proofData to set
	 */
	public void setProofData(String proofData) {
		this.proofData = proofData;
	}

	/**
	 * 获取是否通过
	 * @return the isPass
	 */
	public String getIsPass() {
		return isPass;
	}

	/**
	 * 设置是否通过
	 * @param isPass the isPass to set
	 */
	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}

	/**
	 * 获取拥有企业标签列表
	 * @return the companyLabelList
	 */
	@JsonIgnore
	public List<CompanyLabel> getCompanyLabelList() {
		return companyLabelList;
	}

	/**
	 * 设置拥有企业标签列表
	 * @param companyLabelList the companyLabelList to set
	 */
	public void setCompanyLabelList(List<CompanyLabel> companyLabelList) {
		this.companyLabelList = companyLabelList;
	}

	
	@JsonIgnore
	public List<String> getCompanyLabelIdList() {
		List<String> companyLabelIdList = Lists.newArrayList();
		for (CompanyLabel companyLabel : companyLabelList) {
			companyLabelIdList.add(companyLabel.getId());
		}
		return companyLabelIdList;
	}

	public void setCompanyLabelIdList(List<String> companyLabelIdList) {
		companyLabelList = Lists.newArrayList();
		for (String companyLabelId : companyLabelIdList) {
			CompanyLabel companyLabel = new CompanyLabel();
			companyLabel.setId(companyLabelId);
			companyLabelList.add(companyLabel);
		}
	}
	
	/**
	 * 用户拥有的企业标签名称字符串, 多个角色名称用','分隔.
	 */
	public String getCompanyLabelNames() {
		return Collections3.extractToString(companyLabelList, "name", ",");
	}

	/**
	 * @return the companyLabel
	 */
	public CompanyLabel getCompanyLabel() {
		return companyLabel;
	}

	/**
	 * @param companyLabel the companyLabel to set
	 */
	public void setCompanyLabel(CompanyLabel companyLabel) {
		this.companyLabel = companyLabel;
	}
	
}