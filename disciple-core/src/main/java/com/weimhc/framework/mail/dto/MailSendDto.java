/**
 * 
 */
package com.weimhc.framework.mail.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.weimhc.framework.dto.AbstractDto;

/**
 * 邮件发送申请
 *
 * @author laozh
 * @version 2017年4月17日
 */
public class MailSendDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 944309030890988334L;
	/**
	 * 发件人地址
	 */
	private String from;
	/**
	 * 收件人地址列表
	 */
	private String[] to;
	/**
	 * 抄送地址列表
	 */
	private String[] cc;
	/**
	 * 邮件主题
	 */
	private String subject;

	/**
	 * 邮件正文
	 */
	private String content;
	/**
	 * 附件列表
	 */
	private List<File> attachments;

	/**
	 * 返回 TODO
	 * 
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * 设置 发送地址列表
	 * 
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * 返回 发送地址列表
	 * 
	 * @return the to
	 */
	public String[] getTo() {
		return to;
	}

	/**
	 * 设置 TODO
	 * 
	 * @param to
	 *            the to to set
	 */
	public void setTo(String[] to) {
		this.to = to;
	}

	/**
	 * 返回 抄送地址列表
	 * 
	 * @return the cc
	 */
	public String[] getCc() {
		return cc;
	}

	/**
	 * 设置 抄送地址列表
	 * 
	 * @param cc
	 *            the cc to set
	 */
	public void setCc(String[] cc) {
		this.cc = cc;
	}

	/**
	 * 返回 邮件主题
	 * 
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 设置 邮件主题
	 * 
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 返回 邮件正文
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置 邮件正文
	 * 
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 返回 附件列表
	 * 
	 * @return the attachments
	 */
	public List<File> getAttachments() {
		return attachments;
	}

	/**
	 * 设置 附件列表
	 * 
	 * @param attachments
	 *            the attachments to set
	 */
	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}

	/**
	 * 邮件发送实体构造函数
	 * 
	 * @author laozh
	 *
	 */
	public static class MailSendDtoBuilder {
		/**
		 * 发件人地址
		 */
		private String from;
		/**
		 * 收件人地址列表
		 */
		private List<String> toList = Lists.newArrayList();
		/**
		 * 抄送地址列表
		 */
		private List<String> ccList = Lists.newArrayList();
		/**
		 * 邮件主题
		 */
		private String subject;

		/**
		 * 邮件正文
		 */
		private String content;
		/**
		 * 附件列表
		 */
		private List<File> attachments = new ArrayList<>();

		/**
		 * 创建新的邮件发送实体构造函数
		 * 
		 * @return
		 */
		public static MailSendDtoBuilder newBuilder() {
			return new MailSendDtoBuilder();
		}

		/**
		 * 添加 发件人地址
		 * 
		 * @param from
		 * @return
		 */
		public MailSendDtoBuilder addFrom(String from) {
			this.from = from;
			return this;
		}

		/**
		 * 添加收件人列表
		 * 
		 * @param to
		 * @return
		 */
		public MailSendDtoBuilder addTo(String... to) {
			this.toList.addAll(Lists.newArrayList(to));
			return this;
		}

		/**
		 * 添加抄送列表
		 * 
		 * @param cc
		 * @return
		 */
		public MailSendDtoBuilder addCc(String... cc) {
			this.ccList.addAll(Lists.newArrayList(cc));
			return this;
		}

		/**
		 * 添加 发送主题
		 * 
		 * @param subject
		 * @return
		 */
		public MailSendDtoBuilder addSubject(String subject) {
			this.subject = subject;
			return this;
		}

		/**
		 * 添加发送正文
		 * 
		 * @param content
		 * @return
		 */
		public MailSendDtoBuilder addContent(String content) {
			this.content = content;
			return this;
		}

		/**
		 * 添加 附件
		 * 
		 * @param attachments
		 * @return
		 */
		public MailSendDtoBuilder addAttachment(File... attachment) {
			this.attachments.addAll(Lists.newArrayList(attachment));
			return this;
		}

		public MailSendDto build() {
			MailSendDto mailSendDto = new MailSendDto();
			mailSendDto.setFrom(from);
			String[] to = new String[toList.size()];
			toList.toArray(to);
			mailSendDto.setTo(to);
			String[] cc = new String[ccList.size()];
			ccList.toArray(cc);
			mailSendDto.setCc(cc);
			mailSendDto.setSubject(subject);
			mailSendDto.setContent(content);
			mailSendDto.setAttachments(attachments);
			return mailSendDto;
		}
	}
}
