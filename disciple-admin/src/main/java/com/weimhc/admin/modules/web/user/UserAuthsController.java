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
import com.weimhc.modules.user.entity.UserAuth;
import com.weimhc.modules.user.service.UserAuthService;

/**
 * 用户授权信息Controller
 * 
 * @author zsm
 * @version 2017-02-15
 */
@Controller
@RequestMapping(value = "${adminPath}/user/userAuths")
public class UserAuthsController extends AdminBaseController {

	@Autowired
	private UserAuthService userAuthsService;

	@ModelAttribute
	public UserAuth get(@RequestParam(required = false) String id) {
		UserAuth entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = userAuthsService.get(id);
		}
		if (entity == null) {
			entity = new UserAuth();
		}
		return entity;
	}

	@RequiresPermissions("user:userAuths:view")
	@RequestMapping(value = { "list", "" })
	public String list(UserAuth userAuths, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<UserAuth> page = userAuthsService
				.findPage(new Page<UserAuth>(request, response), userAuths);
		model.addAttribute("page", page);
		model.addAttribute("userAuths", userAuths);
		return "modules/user/userAuthsList";
	}

	@RequiresPermissions("user:userAuths:view")
	@RequestMapping(value = "form")
	public String form(UserAuth userAuths, Model model) {
		model.addAttribute("userAuths", userAuths);
		return "modules/user/userAuthsForm";
	}

	@RequiresPermissions("user:userAuths:edit")
	@RequestMapping(value = "save")
	public String save(UserAuth userAuths, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, userAuths)) {
			return form(userAuths, model);
		}
		userAuthsService.save(userAuths);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/user/userAuths/?repage";
	}

	@RequiresPermissions("user:userAuths:edit")
	@RequestMapping(value = "delete")
	public String delete(UserAuth userAuths,
			RedirectAttributes redirectAttributes) {
		userAuthsService.deleteEntity(userAuths);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/user/userAuths/?repage";
	}

	@RequiresPermissions("user:userAuths:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		userAuthsService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

}