/**
 * 
 */
package com.weimhc.admin.core.web;

import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.thinkgem.javamg.common.web.BaseController;
import com.weimhc.admin.core.utils.AdminUserUtils;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.framework.web.utils.MessageSourceUtils;
import com.weimhc.modules.sys.entity.User;

/**
 * 控制器支持类
 * 
 * @version 2015-12-24
 */
public abstract class AdminBaseController extends BaseController {

	/** 错误消息 */
	protected static final ResultMessage ERROR_MESSAGE = ResultMessage
			.error("admin.resultMessage.error");
	/** 错误消息 */
	protected static final ResultMessage ERROR_MESSAGE_SYSTEM = ResultMessage
			.error("admin.resultMessage.systemError");

	/** 错误消息 */
	protected static final ResultMessage ERROR_MESSAGE_READERLOG = ResultMessage
			.error("admin.resultMessage.readerLogError");

	/** 成功消息 */
	protected static final ResultMessage SUCCESS_MESSAGE = ResultMessage
			.success("admin.resultMessage.success");

	/** "验证结果"参数名称 */
	private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

	/** "验证结果"存储对象 */
	private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_OJBECT_NAME = "errors";

	/**
	 * 默认语言
	 */
	@Value("${message.locale.default}")
	protected Locale defaultLocal;

	/**
	 * 获取国际化消息
	 * 
	 * @param code
	 *            代码
	 * @param args
	 *            参数
	 * @return 国际化消息
	 */
	protected String getMessage(String code, Object... args) {
		return MessageSourceUtils.getMessage(defaultLocal, code, args);
	}

	/**
	 * 添加Model消息
	 * 
	 * @param message
	 */
	@Override
	protected void addMessage(Model model, String... codes) {
		StringBuilder sb = new StringBuilder();
		for (String code : codes) {
			sb.append(getMessage(code)).append(codes.length > 1 ? "<br/>" : "");
		}
		model.addAttribute("resultMessage", ResultMessage.info(sb.toString()));
	}

	/**
	 * 添加Flash消息 添加国际化功能
	 * 
	 * @param message
	 */
	@Override
	protected void addMessage(RedirectAttributes redirectAttributes, String... codes) {
		StringBuilder sb = new StringBuilder();
		for (String code : codes) {
			sb.append(getMessage(code)).append(codes.length > 1 ? "<br/>" : "");
		}
		redirectAttributes.addFlashAttribute("resultMessage", ResultMessage.info(sb.toString()));
	}

	/**
	 * 添加Flash消息 添加国际化功能
	 * 
	 * @param resultMessage
	 */
	protected void addFlashMessage(RedirectAttributes redirectAttributes,
			ResultMessage resultMessage) {
		redirectAttributes.addFlashAttribute("resultMessage", resultMessage);
	}

	/**
	 * 添加Flash消息 添加国际化功能
	 * 
	 * @param resultMessage
	 */
	protected void addFlashMessage(Model model, ResultMessage resultMessage) {
		model.addAttribute("resultMessage", resultMessage);
	}

	/**
	 * 数据验证，实现类似于数据绑定验证的功能，
	 * <p>
	 * 可配合 &lt; form:errors / &gt; 使用，单独显示
	 * </p>
	 * 
	 * @param result
	 *            验证结果
	 * @param target
	 *            验证对象
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(BindingResult result, Object target, Class<?>... groups) {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
				// 添加验证信息到数据绑定中
				result.addError(new FieldError(CONSTRAINT_VIOLATIONS_ATTRIBUTE_OJBECT_NAME,
						constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage()));

			}
			return false;
		}
	}

	/**
	 * 数据验证，验证结果放置在request属性中（constraintViolations）
	 * 
	 * @param target
	 *            验证对象
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Object target, Class<?>... groups) {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME,
					constraintViolations, RequestAttributes.SCOPE_REQUEST);
			return false;
		}
	}

	/**
	 * 数据验证
	 * 
	 * @param type
	 *            类型
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Class<?> type, String property, Object value, Class<?>... groups) {
		Set<?> constraintViolations = validator.validateValue(type, property, value, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME,
					constraintViolations, RequestAttributes.SCOPE_REQUEST);
			return false;
		}
	}

	/**
	 * 获得当前登录对象
	 * 
	 * @return
	 */
	protected User getCurrentUser() {
		return AdminUserUtils.getUser();
	}

	/**
	 * 处理搜索类型，通过弹出层选择时使用
	 * 
	 * @param request
	 * @param model
	 */
	protected boolean dealWithSearchType(HttpServletRequest request, Model model) {
		String searchType = request.getParameter("searchType");
		if (StringUtils.isBlank(searchType)) {
			return false;
		}
		model.addAttribute("searchType", searchType);
		if (StringUtils.equals("selectLink", searchType)) {
			String isMultiple = request.getParameter("isMultiple");
			if (StringUtils.equals(Global.TRUE, isMultiple)) {
				model.addAttribute("isMultiple", Global.TRUE);
			}
			String isOther = request.getParameter("isOther");
			if (StringUtils.equals(Global.TRUE, isOther)) {
				model.addAttribute("isOther", Global.TRUE);
			}
		}
		return true;
	}
}
