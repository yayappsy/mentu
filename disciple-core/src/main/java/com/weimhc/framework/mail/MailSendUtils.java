
package com.weimhc.framework.mail;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.google.common.collect.Maps;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.security.Cryptos;
import com.weimhc.framework.mail.dto.MailSendDto;
import com.weimhc.framework.mail.dto.MailSendDto.MailSendDtoBuilder;
import com.weimhc.modules.base.entity.MailConfig;
import com.weimhc.modules.base.utils.MailConfigUtils;

/**
 * @ClassName: MailSendUtils
 * @Description: 发送邮件
 * @author zsm
 * @date 2015年5月20日 上午12:06:45
 */
public final class MailSendUtils {

	private static final String DEFAULT_ENCODING = "utf-8";
	private static final String MAIL_CONFING_IF_BY_DB = "mail.config.ifByDb";
	private static final String MAIL_HOST_NAME = "mail.host.name";
	private static final String MAIL_DEFAULT_USERNAME = "mail.default.username";
	private static final String MAIL_DEFAULT_PASSWORD = "mail.default.password";

	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(MailSendUtils.class);

	private static Map<String, String> senderCfg = null;

	private static Map<String, String> getEmailCfgFromDB() {
		MailConfig mailConfig = MailConfigUtils.findDefault();
		Map<String, String> senderCfg = Maps.newHashMap();
		senderCfg.put("host", mailConfig.getSmtpAddress());
		senderCfg.put("username", mailConfig.getAddress());
		senderCfg.put("password", Cryptos.aesDecrypt(mailConfig.getPassword()));

		return senderCfg;
	}

	/**
	 * 获取邮件配置信息
	 * 
	 * @return
	 */
	private static Map<String, String> getEmailCfg() {
		if (Global.TRUE
				.equalsIgnoreCase(Global.getConfig(MAIL_CONFING_IF_BY_DB))) {
			return getEmailCfgFromDB();
		}
		Map<String, String> senderCfg = Maps.newHashMap();
		senderCfg.put("host", Global.getConfig(MAIL_HOST_NAME));
		senderCfg.put("username", Global.getConfig(MAIL_DEFAULT_USERNAME));
		senderCfg.put("password", Global.getConfig(MAIL_DEFAULT_PASSWORD));
		return senderCfg;
	}

	/***
	 * 实际发送邮件使用的方法。
	 * 
	 * @param mailSendDto
	 *            发送邮寄所需的信息
	 * 
	 * @throws MessagingException
	 */
	private static void mailSender(MailSendDto mailSendDto)
			throws MessagingException {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

		if (senderCfg == null) {
			senderCfg = getEmailCfg();
		}
		// 设定mail server
		senderImpl.setHost(senderCfg.get("host"));
		senderImpl.setUsername(senderCfg.get("username"));
		senderImpl.setPassword(senderCfg.get("password"));

		Properties prop = new Properties();
		prop.setProperty("mail.smtp.auth", "true");
		senderImpl.setJavaMailProperties(prop);
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true,
				DEFAULT_ENCODING);
		helper.setTo(mailSendDto.getTo());
		if (mailSendDto.getCc() != null && mailSendDto.getCc().length > 0) {
			helper.setCc(mailSendDto.getCc());
		}
		helper.setFrom(mailSendDto.getFrom() == null ? senderCfg.get("username")
				: mailSendDto.getFrom());
		helper.setSubject(mailSendDto.getSubject());
		helper.setText(mailSendDto.getContent(), true);

		if (mailSendDto.getAttachments() != null) {
			for (File attachment : mailSendDto.getAttachments()) {
				helper.addAttachment(attachment.getName(), attachment);
			}
		}
		senderImpl.send(mailMessage);
	}

	/**
	 * 发送邮件
	 * 
	 * @param to
	 *            收件人
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @throws MessagingException
	 */
	public static void mailSend(String to, String subject, String content)
			throws MessagingException {
		MailSendDto mailSendDto = MailSendDtoBuilder.newBuilder().addTo(to)
				.addSubject(subject).addContent(content).build();
		mailSender(mailSendDto);
	}

	/**
	 * 发送邮件
	 * 
	 * @param to
	 *            收件人
	 * @param cc
	 *            抄送人
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @throws MessagingException
	 */
	public static void mailSend(String to, String cc, String subject,
			String content) throws MessagingException {
		MailSendDto mailSendDto = MailSendDtoBuilder.newBuilder().addTo(to)
				.addCc(cc).addSubject(subject).addContent(content).build();
		mailSender(mailSendDto);
	}

	/**
	 * 发送邮件
	 * 
	 * @param to
	 *            收件人列表
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @throws MessagingException
	 */
	public static void mailSend(String[] to, String subject, String content)
			throws MessagingException {
		MailSendDto mailSendDto = MailSendDtoBuilder.newBuilder().addTo(to)
				.addSubject(subject).addContent(content).build();
		mailSender(mailSendDto);
	}

	/**
	 * 发送邮件
	 * 
	 * @param to
	 *            收件人
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @param attachment
	 *            附件
	 * @throws MessagingException
	 */
	public static void mailSend(String to, String subject, String content,
			File attachment) throws MessagingException {
		MailSendDto mailSendDto = MailSendDtoBuilder.newBuilder().addTo(to)
				.addSubject(subject).addContent(content)
				.addAttachment(attachment).build();
		mailSender(mailSendDto);
	}

	/**
	 * 发送邮件
	 * 
	 * @param to
	 *            收件人列表
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @param attachment
	 *            附件
	 * @throws MessagingException
	 */
	public static void mailSend(String[] to, String subject, String content,
			File attachment) throws MessagingException {
		MailSendDto mailSendDto = MailSendDtoBuilder.newBuilder().addTo(to)
				.addSubject(subject).addContent(content)
				.addAttachment(attachment).build();
		mailSender(mailSendDto);
	}

	/**
	 * 邮件信息
	 * 
	 * @param mailSendDto
	 * @throws MessagingException
	 */
	public static void mailSend(MailSendDto mailSendDto)
			throws MessagingException {
		mailSender(mailSendDto);
	}

	public static void main(String[] args) {
		senderCfg = new HashMap<String, String>();
		senderCfg.put("host", "smtp.exmail.qq.com");
		senderCfg.put("username", "zhangshimin@itdiy.top");
		senderCfg.put("password", "HH2016@zsm");
		try {
			mailSend("695267004@qq.com", "密码找回邮件", "请按照以下信息构建");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
	}
}
