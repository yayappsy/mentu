package com.weimhc.modules.base.utils.setting;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

/**
 * 
 * 把暂时不用的配置
 * 
 * @author szuo
 * @version 2016年6月29日
 */
public abstract class SettingExtendUtils {

	/** 注册协议 */
	private String registerAgreement;

	/** 安全密匙有效时间 */
	private Integer safeKeyExpiryTime;

	/** 发件人邮箱 */
	private String smtpFromMail;

	/** SMTP服务器地址 */
	private String smtpHost;

	/** SMTP服务器端口 */
	private Integer smtpPort;

	/** SMTP用户名 */
	private String smtpUsername;

	/** SMTP密码 */
	private String smtpPassword;

	/** 货币符号 */
	private String currencySign;

	/** 货币单位 */
	private String currencyUnit;

	/** 库存警告数 */
	private Integer stockAlertCount;

	/** 默认积分换算比例 */
	private Double defaultPointScale;

	/** 是否开启开发模式 */
	private Boolean isDevelopmentEnabled;

	/** 是否开启评论 */
	private Boolean isReviewEnabled;

	/** 是否审核评论 */
	private Boolean isReviewCheck;

	/** 评论权限 */
	private ReviewAuthority reviewAuthority;

	/** 是否开启咨询 */
	private Boolean isConsultationEnabled;

	/** 是否审核咨询 */
	private Boolean isConsultationCheck;

	/** 咨询权限 */
	private ConsultationAuthority consultationAuthority;

	/** 是否开启发票功能 */
	private Boolean isInvoiceEnabled;

	/** 是否开启含税价 */
	private Boolean isTaxPriceEnabled;

	/** 税率 */
	private Double taxRate;

	/** 快递100授权KEY */
	private String kuaidi100Key;

	/** 是否开启CNZZ统计 */
	private Boolean isCnzzEnabled;

	/** CNZZ统计站点ID */
	private String cnzzSiteId;

	/** CNZZ统计密码 */
	private String cnzzPassword;

	/** 是否开启51.la统计 */
	private Boolean isLa51Enabled;

	/** 51.la统计站点ID */
	private String la51SiteId;

	/** 51.la统计密码 */
	private String la51Password;

	/**
	 * 获取注册协议
	 * 
	 * @return 注册协议
	 */

	public String getRegisterAgreement() {
		return registerAgreement;
	}

	/**
	 * 设置注册协议
	 * 
	 * @param registerAgreement
	 *            注册协议
	 */
	public void setRegisterAgreement(String registerAgreement) {
		this.registerAgreement = registerAgreement;
	}

	/**
	 * 获取安全密匙有效时间
	 * 
	 * @return 安全密匙有效时间
	 */
	@NotNull
	@Min(0)
	public Integer getSafeKeyExpiryTime() {
		return safeKeyExpiryTime;
	}

	/**
	 * 设置安全密匙有效时间
	 * 
	 * @param safeKeyExpiryTime
	 *            安全密匙有效时间
	 */
	public void setSafeKeyExpiryTime(Integer safeKeyExpiryTime) {
		this.safeKeyExpiryTime = safeKeyExpiryTime;
	}

	/**
	 * 获取发件人邮箱
	 * 
	 * @return 发件人邮箱
	 */

	@Email

	public String getSmtpFromMail() {
		return smtpFromMail;
	}

	/**
	 * 设置发件人邮箱
	 * 
	 * @param smtpFromMail
	 *            发件人邮箱
	 */
	public void setSmtpFromMail(String smtpFromMail) {
		this.smtpFromMail = smtpFromMail;
	}

	/**
	 * 获取SMTP服务器地址
	 * 
	 * @return SMTP服务器地址
	 */

	public String getSmtpHost() {
		return smtpHost;
	}

	/**
	 * 设置SMTP服务器地址
	 * 
	 * @param smtpHost
	 *            SMTP服务器地址
	 */
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	/**
	 * 获取SMTP服务器端口
	 * 
	 * @return SMTP服务器端口
	 */
	@NotNull
	@Min(0)
	public Integer getSmtpPort() {
		return smtpPort;
	}

	/**
	 * 设置SMTP服务器端口
	 * 
	 * @param smtpPort
	 *            SMTP服务器端口
	 */
	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}

	/**
	 * 获取SMTP用户名
	 * 
	 * @return SMTP用户名
	 */

	public String getSmtpUsername() {
		return smtpUsername;
	}

	/**
	 * 设置SMTP用户名
	 * 
	 * @param smtpUsername
	 *            SMTP用户名
	 */
	public void setSmtpUsername(String smtpUsername) {
		this.smtpUsername = smtpUsername;
	}

	/**
	 * 获取SMTP密码
	 * 
	 * @return SMTP密码
	 */

	public String getSmtpPassword() {
		return smtpPassword;
	}

	/**
	 * 设置SMTP密码
	 * 
	 * @param smtpPassword
	 *            SMTP密码
	 */
	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	/**
	 * 获取货币符号
	 * 
	 * @return 货币符号
	 */

	public String getCurrencySign() {
		return currencySign;
	}

	/**
	 * 设置货币符号
	 * 
	 * @param currencySign
	 *            货币符号
	 */
	public void setCurrencySign(String currencySign) {
		this.currencySign = currencySign;
	}

	/**
	 * 获取货币单位
	 * 
	 * @return 货币单位
	 */

	public String getCurrencyUnit() {
		return currencyUnit;
	}

	/**
	 * 设置货币单位
	 * 
	 * @param currencyUnit
	 *            货币单位
	 */
	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
	}

	/**
	 * 获取库存警告数
	 * 
	 * @return 库存警告数
	 */
	@NotNull
	@Min(0)
	public Integer getStockAlertCount() {
		return stockAlertCount;
	}

	/**
	 * 设置库存警告数
	 * 
	 * @param stockAlertCount
	 *            库存警告数
	 */
	public void setStockAlertCount(Integer stockAlertCount) {
		this.stockAlertCount = stockAlertCount;
	}

	/**
	 * 获取默认积分换算比例
	 * 
	 * @return 默认积分换算比例
	 */
	@NotNull
	@Min(0)
	@Digits(integer = 3, fraction = 3)
	public Double getDefaultPointScale() {
		return defaultPointScale;
	}

	/**
	 * 设置默认积分换算比例
	 * 
	 * @param defaultPointScale
	 *            默认积分换算比例
	 */
	public void setDefaultPointScale(Double defaultPointScale) {
		this.defaultPointScale = defaultPointScale;
	}

	/**
	 * 获取是否开启开发模式
	 * 
	 * @return 是否开启开发模式
	 */
	@NotNull
	public Boolean getIsDevelopmentEnabled() {
		return isDevelopmentEnabled;
	}

	/**
	 * 设置是否开启开发模式
	 * 
	 * @param isDevelopmentEnabled
	 *            是否开启开发模式
	 */
	public void setIsDevelopmentEnabled(Boolean isDevelopmentEnabled) {
		this.isDevelopmentEnabled = isDevelopmentEnabled;
	}

	/**
	 * 获取是否开启评论
	 * 
	 * @return 是否开启评论
	 */
	@NotNull
	public Boolean getIsReviewEnabled() {
		return isReviewEnabled;
	}

	/**
	 * 设置是否开启评论
	 * 
	 * @param isReviewEnabled
	 *            是否开启评论
	 */
	public void setIsReviewEnabled(Boolean isReviewEnabled) {
		this.isReviewEnabled = isReviewEnabled;
	}

	/**
	 * 获取是否审核评论
	 * 
	 * @return 是否审核评论
	 */
	@NotNull
	public Boolean getIsReviewCheck() {
		return isReviewCheck;
	}

	/**
	 * 设置是否审核评论
	 * 
	 * @param isReviewCheck
	 *            是否审核评论
	 */
	public void setIsReviewCheck(Boolean isReviewCheck) {
		this.isReviewCheck = isReviewCheck;
	}

	/**
	 * 获取评论权限
	 * 
	 * @return 评论权限
	 */
	@NotNull
	public ReviewAuthority getReviewAuthority() {
		return reviewAuthority;
	}

	/**
	 * 设置评论权限
	 * 
	 * @param reviewAuthority
	 *            评论权限
	 */
	public void setReviewAuthority(ReviewAuthority reviewAuthority) {
		this.reviewAuthority = reviewAuthority;
	}

	/**
	 * 获取是否开启咨询
	 * 
	 * @return 是否开启咨询
	 */
	@NotNull
	public Boolean getIsConsultationEnabled() {
		return isConsultationEnabled;
	}

	/**
	 * 设置是否开启咨询
	 * 
	 * @param isConsultationEnabled
	 *            是否开启咨询
	 */
	public void setIsConsultationEnabled(Boolean isConsultationEnabled) {
		this.isConsultationEnabled = isConsultationEnabled;
	}

	/**
	 * 获取是否审核咨询
	 * 
	 * @return 是否审核咨询
	 */
	@NotNull
	public Boolean getIsConsultationCheck() {
		return isConsultationCheck;
	}

	/**
	 * 设置是否审核咨询
	 * 
	 * @param isConsultationCheck
	 *            是否审核咨询
	 */
	public void setIsConsultationCheck(Boolean isConsultationCheck) {
		this.isConsultationCheck = isConsultationCheck;
	}

	/**
	 * 获取咨询权限
	 * 
	 * @return 咨询权限
	 */
	@NotNull
	public ConsultationAuthority getConsultationAuthority() {
		return consultationAuthority;
	}

	/**
	 * 设置咨询权限
	 * 
	 * @param consultationAuthority
	 *            咨询权限
	 */
	public void setConsultationAuthority(
			ConsultationAuthority consultationAuthority) {
		this.consultationAuthority = consultationAuthority;
	}

	/**
	 * 获取是否开启发票功能
	 * 
	 * @return 是否开启发票功能
	 */
	@NotNull
	public Boolean getIsInvoiceEnabled() {
		return isInvoiceEnabled;
	}

	/**
	 * 设置是否开启发票功能
	 * 
	 * @param isInvoiceEnabled
	 *            是否开启发票功能
	 */
	public void setIsInvoiceEnabled(Boolean isInvoiceEnabled) {
		this.isInvoiceEnabled = isInvoiceEnabled;
	}

	/**
	 * 获取是否开启含税价
	 * 
	 * @return 是否开启含税价
	 */
	@NotNull
	public Boolean getIsTaxPriceEnabled() {
		return isTaxPriceEnabled;
	}

	/**
	 * 设置是否开启含税价
	 * 
	 * @param isTaxPriceEnabled
	 *            是否开启含税价
	 */
	public void setIsTaxPriceEnabled(Boolean isTaxPriceEnabled) {
		this.isTaxPriceEnabled = isTaxPriceEnabled;
	}

	/**
	 * 获取税率
	 * 
	 * @return 税率
	 */
	@NotNull
	@Min(0)
	@Digits(integer = 3, fraction = 3)
	public Double getTaxRate() {
		return taxRate;
	}

	/**
	 * 设置税率
	 * 
	 * @param taxRate
	 *            税率
	 */
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	/**
	 * 获取快递100授权KEY
	 * 
	 * @return 快递100授权KEY
	 */

	public String getKuaidi100Key() {
		return kuaidi100Key;
	}

	/**
	 * 设置快递100授权KEY
	 * 
	 * @param kuaidi100Key
	 *            快递100授权KEY
	 */
	public void setKuaidi100Key(String kuaidi100Key) {
		this.kuaidi100Key = kuaidi100Key;
	}

	/**
	 * 获取是否开启CNZZ统计
	 * 
	 * @return 是否开启CNZZ统计
	 */
	public Boolean getIsCnzzEnabled() {
		return isCnzzEnabled;
	}

	/**
	 * 设置是否开启CNZZ统计
	 * 
	 * @param isCnzzEnabled
	 *            是否开启CNZZ统计
	 */
	public void setIsCnzzEnabled(Boolean isCnzzEnabled) {
		this.isCnzzEnabled = isCnzzEnabled;
	}

	/**
	 * 获取CNZZ统计站点ID
	 * 
	 * @return CNZZ统计站点ID
	 */
	public String getCnzzSiteId() {
		return cnzzSiteId;
	}

	/**
	 * 设置CNZZ统计站点ID
	 * 
	 * @param cnzzSiteId
	 *            CNZZ统计站点ID
	 */
	public void setCnzzSiteId(String cnzzSiteId) {
		this.cnzzSiteId = cnzzSiteId;
	}

	/**
	 * 获取CNZZ统计密码
	 * 
	 * @return CNZZ统计密码
	 */
	public String getCnzzPassword() {
		return cnzzPassword;
	}

	/**
	 * 设置CNZZ统计密码
	 * 
	 * @param cnzzPassword
	 *            CNZZ统计密码
	 */
	public void setCnzzPassword(String cnzzPassword) {
		this.cnzzPassword = cnzzPassword;
	}

	/**
	 * 获取isLa51Enabled
	 *
	 * @return the isLa51Enabled
	 */

	public Boolean getIsLa51Enabled() {
		return isLa51Enabled;
	}

	/**
	 * 设置isLa51Enabled
	 *
	 * @param isLa51Enabled
	 */
	public void setIsLa51Enabled(Boolean isLa51Enabled) {
		this.isLa51Enabled = isLa51Enabled;
	}

	/**
	 * 获取la51SiteId
	 *
	 * @return the la51SiteId
	 */

	public String getLa51SiteId() {
		return la51SiteId;
	}

	/**
	 * 设置la51SiteId
	 *
	 * @param la51SiteId
	 */
	public void setLa51SiteId(String la51SiteId) {
		this.la51SiteId = la51SiteId;
	}

	/**
	 * 获取la51Password
	 *
	 * @return the la51Password
	 */

	public String getLa51Password() {
		return la51Password;
	}

	/**
	 * 设置la51Password
	 *
	 * @param la51Password
	 */
	public void setLa51Password(String la51Password) {
		this.la51Password = la51Password;
	}

}
