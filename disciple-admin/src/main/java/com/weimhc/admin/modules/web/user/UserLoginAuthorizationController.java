/**
 * 
 */
package com.weimhc.admin.modules.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.user.entity.UserLoginAuthorization;
import com.weimhc.modules.user.service.UserLoginAuthorizationService;

/**
 * 平台用户登录授权表Controller
 * 
 * @author zsm
 * @version 2017-09-05
 */
@Controller
@RequestMapping(value = "${adminPath}/user/userLoginAuthorization")
public class UserLoginAuthorizationController extends AdminBaseController {

	@Autowired
	private UserLoginAuthorizationService userLoginAuthorizationService;

	@ModelAttribute
	public UserLoginAuthorization get(
			@RequestParam(required = false) String id) {
		UserLoginAuthorization entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = userLoginAuthorizationService.get(id);
		}
		if (entity == null) {
			entity = new UserLoginAuthorization();
		}
		return entity;
	}

	@RequiresPermissions("user:userLoginAuthorization:view")
	@RequestMapping(value = { "list", "" })
	public String list(UserLoginAuthorization userLoginAuthorization,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<UserLoginAuthorization> page = userLoginAuthorizationService
				.findPage(new Page<UserLoginAuthorization>(request, response),
						userLoginAuthorization);
		model.addAttribute("page", page);
		model.addAttribute("userLoginAuthorization", userLoginAuthorization);
		return "modules/user/userLoginAuthorizationList";
	}

	@RequiresPermissions("user:userLoginAuthorization:view")
	@RequestMapping(value = "form")
	public String form(UserLoginAuthorization userLoginAuthorization,
			Model model) {
		model.addAttribute("userLoginAuthorization", userLoginAuthorization);
		return "modules/user/userLoginAuthorizationForm";
	}

	@RequiresPermissions("user:userLoginAuthorization:edit")
	@RequestMapping(value = "save")
	public String save(UserLoginAuthorization userLoginAuthorization,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, userLoginAuthorization)) {
			return form(userLoginAuthorization, model);
		}
		userLoginAuthorizationService.save(userLoginAuthorization);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/user/userLoginAuthorization/?repage";
	}

	@RequiresPermissions("user:userLoginAuthorization:delete")
	@RequestMapping(value = "delete")
	public String delete(UserLoginAuthorization userLoginAuthorization,
			RedirectAttributes redirectAttributes) {
		userLoginAuthorizationService.deleteEntity(userLoginAuthorization);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/user/userLoginAuthorization/?repage";
	}

	@RequiresPermissions("user:userLoginAuthorization:delete")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		userLoginAuthorizationService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

	@RequestMapping(value = "/changeLoginPrivilege", method = RequestMethod.POST)
	public @ResponseBody ResultMessage changeLoginPrivilege(
			UserLoginAuthorization userLoginAuthorization,
			RedirectAttributes redirectAttributes) {

		return SUCCESS_MESSAGE;
	}
}