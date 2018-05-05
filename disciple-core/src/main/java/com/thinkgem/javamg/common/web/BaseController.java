/**
 * 
 */
package com.thinkgem.javamg.common.web;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.javamg.common.beanvalidator.BeanValidators;
import com.thinkgem.javamg.common.mapper.JsonMapper;
import com.thinkgem.javamg.common.utils.DateUtils;
import com.thinkgem.javamg.common.utils.StringUtils;

/**
 * 控制器支持类
 * 
 * @version 2013-3-23
 */
public abstract class BaseController {

	public static final String ERROR_PAGE_400 = "error/400";
	public static final String ERROR_PAGE_404 = "error/404";
	public static final String ERROR_PAGE_403 = "error/403";
	public static final String ERROR_PAGE_500 = "error/500";

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 管理基础路径
	 */
	@Value("${adminPath}")
	protected String adminPath;

	/**
	 * 前端基础路径
	 */
	@Value("${frontPath}")
	protected String frontPath;

	
	/**
	 * 前端基础路径
	 */
	@Value("${apiPath}")
	protected String apiPath;

	/**
	 * 前端URL后缀
	 */
	@Value("${urlSuffix}")
	protected String urlSuffix;

	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	protected Validator validator;

	/**
	 * 服务端参数有效性验证
	 * 
	 * @param object
	 *            验证的实体对象
	 * @param groups
	 *            验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 resultMessage 中
	 */
	protected boolean beanValidator(Model model, Object object,
			Class<?>... groups) {
		try {
			BeanValidators.validateWithException(validator, object, groups);
		} catch (ConstraintViolationException ex) {
			List<String> list = BeanValidators
					.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(model, list.toArray(new String[] {}));
			return false;
		}
		return true;
	}

	/**
	 * 服务端参数有效性验证
	 * 
	 * @param object
	 *            验证的实体对象
	 * @param groups
	 *            验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 flash resultMessage 中
	 */
	protected boolean beanValidator(RedirectAttributes redirectAttributes,
			Object object, Class<?>... groups) {
		try {
			BeanValidators.validateWithException(validator, object, groups);
		} catch (ConstraintViolationException ex) {
			List<String> list = BeanValidators
					.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(redirectAttributes, list.toArray(new String[] {}));
			return false;
		}
		return true;
	}

	/**
	 * 服务端参数有效性验证
	 * 
	 * @param object
	 *            验证的实体对象
	 * @param groups
	 *            验证组，不传入此参数时，同@Valid注解验证
	 * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
	 */
	protected void beanValidator(Object object, Class<?>... groups) {
		BeanValidators.validateWithException(validator, object, groups);
	}

	/**
	 * 添加Model消息
	 * 
	 * @param resultMessage
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String resultMessage : messages) {
			sb.append(resultMessage).append(messages.length > 1 ? "<br/>" : "");
		}
		model.addAttribute("resultMessage", sb.toString());
	}

	/**
	 * 添加Flash消息
	 * 
	 * @param resultMessage
	 */
	protected void addMessage(RedirectAttributes redirectAttributes,
			String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String resultMessage : messages) {
			sb.append(resultMessage).append(messages.length > 1 ? "<br/>" : "");
		}
		redirectAttributes.addFlashAttribute("resultMessage", sb.toString());
	}

	/**
	 * 客户端返回JSON字符串
	 * 
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderString(HttpServletResponse response, Object object) {
		return renderString(response, JsonMapper.toJsonString(object),
				"application/json");
	}

	/**
	 * 客户端返回字符串
	 * 
	 * @param response
	 * @param string
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string,
			String type) {
		try {
			response.reset();
			response.setContentType(type);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 参数绑定异常
	 */
	@ExceptionHandler({ BindException.class, ConstraintViolationException.class,
			ValidationException.class })
	public String bindException() {
		return ERROR_PAGE_404;
	}

	/**
	 * 授权登录异常
	 */
	@ExceptionHandler({ AuthenticationException.class })
	public String authenticationException() {
		return ERROR_PAGE_403;
	}

	/**
	 * 初始化数据绑定 1. 将所有传递进来的String进行HTML编码，防止XSS攻击 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {

			@Override
			public void setAsText(String text) {
				setValue(text == null ? null
						: StringEscapeUtils.escapeHtml4(text.trim()));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {

			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
			// @Override
			// public String getAsText() {
			// Object value = getValue();
			// return value != null ? DateUtils.formatDateTime((Date)value) :
			// "";
			// }
		});
	}

	/**
	 * 获取请求中的特定参数7
	 * 
	 * @param request
	 * @return 首先从header中获取，其次从parameter中获取，最后从atribute中获取，都没有返回null
	 */
	protected String searchQueryString(HttpServletRequest request,
			String queryString) {
		String result = request.getHeader(queryString);
		if (StringUtils.isNotBlank(result)) {
			return result;
		}
		result = request.getParameter(queryString);
		if (StringUtils.isNotBlank(result)) {
			return result;
		}
		result = (String) request.getAttribute(queryString);
		if (StringUtils.isNotBlank(result)) {
			return result;
		}
		return null;
	}
}
