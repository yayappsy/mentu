/**
 * 
 */
package com.weimhc.modules.base.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 邮件发送配置信息Entity
 * 
 * @author zsm
 * @version 2017-06-14
 */
public class MailConfig extends DataEntity<MailConfig> {

	private static final long serialVersionUID = 1L;
	/**
	 * 企业邮箱地址
	 * 
	 */
	private String address;
	/**
	 * 密码
	 * 
	 */
	private String password;
	/**
	 * 显示名称
	 * 
	 */
	private String displayName;
	/**
	 * pop3服务器地址
	 * 
	 */
	private String pop3Address;
	/**
	 * smtp服务器地址
	 * 
	 */
	private String smtpAddress;
	/**
	 * IMAP服务器地址
	 * 
	 */
	private String imapAddress;
	/**
	 * 字符集
	 * 
	 */
	private String charset;
	/**
	 * 企业邮箱登录地址
	 * 
	 */
	private String loginUrl;

	/**
	 * 新密码(用于修改密码)
	 * 
	 */
	private String newPassword;

	public MailConfig() {
		super();
	}

	public MailConfig(String id) {
		super(id);
	}

	/**
	 * 获取企业邮箱地址
	 * 
	 * @return 企业邮箱地址
	 */
	@Length(min = 1, max = 255)
	public String getAddress() {
		return address;
	}

	/**
	 * 设置企业邮箱地址
	 * 
	 * @param address
	 *            企业邮箱地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取密码
	 * 
	 * @return 密码 已经使用aes加密 解密调用 Cryptos.aesDecrypt(String)
	 * @see com.thinkgem.javamg.common.security.Cryptos#aesDecrypt(String)
	 */
	@Length(min = 1, max = 255)
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码
	 * 
	 * @param password
	 *            密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取显示名称
	 * 
	 * @return 显示名称
	 */
	@Length(min = 1, max = 100)
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * 设置显示名称
	 * 
	 * @param displayName
	 *            显示名称
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 获取pop3服务器地址
	 * 
	 * @return pop3服务器地址
	 */
	@Length(min = 0, max = 255)
	public String getPop3Address() {
		return pop3Address;
	}

	/**
	 * 设置pop3服务器地址
	 * 
	 * @param pop3Address
	 *            pop3服务器地址
	 */
	public void setPop3Address(String pop3Address) {
		this.pop3Address = pop3Address;
	}

	/**
	 * 获取smtp服务器地址
	 * 
	 * @return smtp服务器地址
	 */
	@Length(min = 0, max = 255)
	public String getSmtpAddress() {
		return smtpAddress;
	}

	/**
	 * 设置smtp服务器地址
	 * 
	 * @param smtpAddress
	 *            smtp服务器地址
	 */
	public void setSmtpAddress(String smtpAddress) {
		this.smtpAddress = smtpAddress;
	}

	/**
	 * 获取IMAP服务器地址
	 * 
	 * @return IMAP服务器地址
	 */
	@Length(min = 0, max = 255)
	public String getImapAddress() {
		return imapAddress;
	}

	/**
	 * 设置IMAP服务器地址
	 * 
	 * @param imapAddress
	 *            IMAP服务器地址
	 */
	public void setImapAddress(String imapAddress) {
		this.imapAddress = imapAddress;
	}

	/**
	 * 获取字符集
	 * 
	 * @return 字符集
	 */
	@Length(min = 0, max = 255)
	public String getCharset() {
		return charset;
	}

	/**
	 * 设置字符集
	 * 
	 * @param charset
	 *            字符集
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * 获取企业邮箱登录地址
	 * 
	 * @return 企业邮箱登录地址
	 */
	@Length(min = 1, max = 255)
	public String getLoginUrl() {
		return loginUrl;
	}

	/**
	 * 设置企业邮箱登录地址
	 * 
	 * @param loginUrl
	 *            企业邮箱登录地址
	 */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	/**
	 * 返回 新密码(用于修改密码)
	 * 
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * 设置 新密码(用于修改密码)
	 * 
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}