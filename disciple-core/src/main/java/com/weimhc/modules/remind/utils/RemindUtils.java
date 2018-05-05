/**
 * 
 */
package com.weimhc.modules.remind.utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.github.mustachejava.MustacheParser;
import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.framework.mail.MailSendUtils;
import com.weimhc.framework.mail.dto.MailSendDto;
import com.weimhc.framework.sms.SMSSendUtils;
import com.weimhc.framework.sms.dto.req.DefaultSMSDto;
import com.weimhc.modules.message.entity.InternalMessage;
import com.weimhc.modules.message.entity.InternalMessageType;
import com.weimhc.modules.message.utils.InternalMessageUtils;
import com.weimhc.modules.remind.dao.RemindDao;
import com.weimhc.modules.remind.dao.RemindParameterDao;
import com.weimhc.modules.remind.dao.RemindTemplateDao;
import com.weimhc.modules.remind.dao.RemindWayDao;
import com.weimhc.modules.remind.entity.BusinessAction;
import com.weimhc.modules.remind.entity.Remind;
import com.weimhc.modules.remind.entity.RemindParameter;
import com.weimhc.modules.remind.entity.RemindTemplate;
import com.weimhc.modules.remind.entity.TargetType;
import com.weimhc.modules.user.entity.UserInfo;

/**
 * 消息提醒工具类
 *
 * @author laozh
 * @version 2017年4月17日
 */
public abstract class RemindUtils {

	private static RemindDao remindDao = SpringContextHolder
			.getBean(RemindDao.class);

	private static RemindTemplateDao remindTemplateDao = SpringContextHolder
			.getBean(RemindTemplateDao.class);

	private static RemindWayDao remindWayDao = SpringContextHolder
			.getBean(RemindWayDao.class);

	private static RemindParameterDao remindParameterDao = SpringContextHolder
			.getBean(RemindParameterDao.class);

	private static Logger logger = LoggerFactory.getLogger(RemindUtils.class);

	/**
	 * 默认模板参数开始标记
	 */
	public static final String DEFAULT_START_MARK = "<(";

	/**
	 * 默认模板参数结束标记
	 */
	public static final String DEFAULT_END_MARK = ")>";

	/**
	 * 提醒相关缓存 暂时未使用
	 */
	public final static String REMIND_CACHE = "remindCache";

	/** 提醒相关缓存列表 */
	public final static String CACHE_REMIND_LIST = "remind_list";

	/** 提醒相关缓存列表 */
	public final static String CACHE_REMIND_BY_ID = "remind_id";

	/**
	 * 获取提醒相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	/**
	 * 获取相关缓存 从默认缓存中
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key, Object defaultValue) {
		Object obj = CacheUtils.get(key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * 设置提醒相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 清除缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void removeCache(String key) {
		CacheUtils.remove(key);
	}

	/**
	 * 清除所有缓存缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void clearCache() {
		CacheUtils.remove(CACHE_REMIND_LIST);
		CacheUtils.remove(CACHE_REMIND_BY_ID);
	}

	/**
	 * 获取所有提醒参数列表
	 * 
	 * @return
	 */
	public static List<RemindParameter> findRemindParameterAll() {
		List<RemindParameter> list = Lists.newArrayList();
		if (list == null || list.isEmpty()) {
			list = remindParameterDao.findAllList(new RemindParameter());
		}
		return list;
	}

	/**
	 * 根据业务代码获取需要的提醒模板
	 * 
	 * @param businessAction
	 * @return
	 */
	public static List<RemindTemplate> findByBusinessCode(
			BusinessAction businessAction) {
		Remind remind = new Remind();
		remind.setBusinessAction(businessAction);
		RemindTemplate remindTemplate = new RemindTemplate(remind);
		List<RemindTemplate> remindTemplates = remindTemplateDao
				.findAllList(remindTemplate);

		return remindTemplates;
	}

	/**
	 * 发送提醒
	 * 
	 * @param remind
	 */
	public static void sendRemind(BusinessAction businessAction,
			Map<String, Object> dataSource) {
		List<RemindTemplate> remindTemplates = findByBusinessCode(
				businessAction);
		for (RemindTemplate remindTemplate : remindTemplates) {
			sendRemind(remindTemplate, dataSource);
		}
	}

	/**
	 * 发送提醒
	 * 
	 * @param remind
	 * @param dataSource
	 */
	private static void sendRemind(RemindTemplate remindTemplate,
			Map<String, Object> dataSource) {
		if (StringUtils.equals("1", remindTemplate.getRemindWay().getId())) {
			sendEmail(remindTemplate, dataSource);
		} else if (StringUtils.equals("2",
				remindTemplate.getRemindWay().getId())) {
			sendSms(remindTemplate, dataSource);
		} else {
			sendInternalMessage(remindTemplate, dataSource);
		}

	}

	/**
	 * 发送邮件
	 * 
	 * @param remindTemplate
	 * @param dataSource
	 */
	private static boolean sendEmail(RemindTemplate remindTemplate,
			Map<String, Object> dataSource) {
		TargetType targetType = remindTemplate.getRemind().getTargetType();
		MailSendDto mailSendDto = new MailSendDto();
		if (TargetType.member.equals(targetType)) {
			UserInfo userInfo = (UserInfo) dataSource.get("member");
			if (StringUtils.isBlank(userInfo.getEmail())) {
				return false;
			}
			mailSendDto.setTo(ArrayUtils.toArray(userInfo.getEmail()));
		} else if (TargetType.teacher.equals(targetType)) {
			UserInfo userInfo = (UserInfo) dataSource.get("teacher");
			if (StringUtils.isBlank(userInfo.getEmail())) {
				return false;
			}
			mailSendDto.setTo(ArrayUtils.toArray(userInfo.getEmail()));
		} else {
			if (StringUtils.isBlank(remindTemplate.getAddressee())) {
				return false;
			}
			mailSendDto
					.setTo(ArrayUtils.toArray(remindTemplate.getAddressee()));
		}
		String templateTitleName = remindTemplate.getRemind().getName()
				+ targetType + "_title_email";
		String templateContentName = remindTemplate.getRemind().getName()
				+ targetType + "_content_email";
		mailSendDto.setSubject(renderTemplate(templateTitleName,
				remindTemplate.getActualTitle(), dataSource));
		mailSendDto.setContent(renderTemplate(templateContentName,
				remindTemplate.getActualContent(), dataSource));

		try {
			MailSendUtils.mailSend(mailSendDto);
		} catch (MessagingException e) {
			logger.error(e.getMessage());
		}
		return true;
	}

	/**
	 * 发送短信
	 * 
	 * @param remindTemplate
	 * @param dataSource
	 */
	private static boolean sendSms(RemindTemplate remindTemplate,
			Map<String, Object> dataSource) {
		TargetType targetType = remindTemplate.getRemind().getTargetType();
		DefaultSMSDto smsSendDto = new DefaultSMSDto();
		if (TargetType.member.equals(targetType)) {
			UserInfo userInfo = (UserInfo) dataSource.get("member");
			if (StringUtils.isBlank(userInfo.getMobile())) {
				return false;
			}
			smsSendDto.setDestination(userInfo.getMobile());
		} else if (TargetType.teacher.equals(targetType)) {
			UserInfo userInfo = (UserInfo) dataSource.get("teacher");
			if (StringUtils.isBlank(userInfo.getMobile())) {
				return false;
			}
			smsSendDto.setDestination(userInfo.getMobile());
		} else {
			if (StringUtils.isBlank(remindTemplate.getAddressee())) {
				return false;
			}
			smsSendDto.setDestination(remindTemplate.getAddressee());
		}
		String templateContentName = remindTemplate.getRemind().getName()
				+ targetType + "_content_sms";
		smsSendDto.setContent(renderTemplate(templateContentName,
				remindTemplate.getActualContent(), dataSource));

		SMSSendUtils.putSMSInfo(smsSendDto.getDestination(),
				smsSendDto.getContent(), null);
		return true;
	}

	/**
	 * 发送站内信
	 * 
	 * @param remindTemplate
	 * @param dataSource
	 */
	private static void sendInternalMessage(RemindTemplate remindTemplate,
			Map<String, Object> dataSource) {
		TargetType targetType = remindTemplate.getRemind().getTargetType();

		List<String> recipientIds = Lists.newArrayList();
		if (TargetType.member.equals(targetType)) {
			UserInfo userInfo = (UserInfo) dataSource.get("member");
			if (userInfo == null || StringUtils.isBlank(userInfo.getId())) {
				return;
			}
			recipientIds.add(userInfo.getId());
		} else if (TargetType.teacher.equals(targetType)) {
			UserInfo userInfo = (UserInfo) dataSource.get("teacher");
			if (userInfo == null || StringUtils.isBlank(userInfo.getId())) {
				return;
			}
			recipientIds.add(userInfo.getId());
		}
		String templateTitleName = remindTemplate.getRemind().getName()
				+ targetType + "_title_internal_message";

		String title = renderTemplate(templateTitleName,
				remindTemplate.getActualTitle(), dataSource);

		String templateContentName = remindTemplate.getRemind().getName()
				+ targetType + "_content_internal_message";

		String content = renderTemplate(templateContentName,
				remindTemplate.getActualContent(), dataSource);

		InternalMessage internalMessage = new InternalMessage();
		internalMessage.setMessageType(InternalMessageType.valueOf(
				remindTemplate.getRemind().getBusinessAction().name()));
		internalMessage.setTitle(title);
		internalMessage.setContent(content);
		internalMessage.setSenderId("2");
		internalMessage.setRecipientIds(recipientIds);

		InternalMessageUtils.sendInternalMessage(internalMessage);
	}

	/**
	 * 渲染模板
	 * 
	 * @param templateName
	 * @param templateText
	 * @param dataSource
	 * @return
	 * @throws ParseException
	 */
	private static String renderTemplate(String templateName,
			String templateText, Map<String, Object> dataSource) {
		if (null == templateText) {
			return "";
		}
		// System.out.println("dataSource：" + dataSource);
		StringWriter stringWriter = new StringWriter();
		MustacheFactory mf = new DefaultMustacheFactory();
		Mustache mustache = mf.compile(new StringReader(templateText),
				templateName);
		mustache.execute(stringWriter, dataSource);
		return stringWriter.toString();
	}

	/**
	 * 
	 * @param templateText
	 * @param parameter
	 */
	public static String transform2MustacheParameter(String templateText,
			List<RemindParameter> parameters) {
		if (StringUtils.isBlank(templateText)) {
			return "";
		}
		if (parameters == null) {
			return templateText;
		}
		String result = templateText;
		String target = null;
		String replacement = null;
		for (RemindParameter remindParameter : parameters) {
			target = DEFAULT_START_MARK + remindParameter.getName()
					+ DEFAULT_END_MARK;
			replacement = MustacheParser.DEFAULT_SM
					+ remindParameter.getEntityName() + "."
					+ remindParameter.getPropertyName()
					+ MustacheParser.DEFAULT_EM;
			result = result.replace(target, replacement);
		}

		return result;
	}

}
