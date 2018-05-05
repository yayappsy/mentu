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
import com.weimhc.modules.user.entity.UserInfo;
import com.weimhc.modules.user.service.UserInfoService;

/**
 * 用户可登录系统管理
 * 
 * @author zsm
 * @version 2017-01-04
 */
@Controller
@RequestMapping(value = "${adminPath}/user/userApp")
public class UserAppController extends AdminBaseController {

	@Autowired
	private UserInfoService userInfoService;

	@ModelAttribute
	public UserInfo get(@RequestParam(required = false) String id) {
		UserInfo entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = userInfoService.get(id);
		}
		if (entity == null) {
			entity = new UserInfo();
		}
		return entity;
	}

	@RequiresPermissions("user:userInfo:view")
	@RequestMapping(value = { "list", "" })
	public String list(UserInfo userInfo, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<UserInfo> page = userInfoService
				.findPage(new Page<UserInfo>(request, response), userInfo);
		model.addAttribute("page", page);
		model.addAttribute("userInfo", userInfo);
		return "modules/user/userInfoList";
	}

	@RequiresPermissions("user:userInfo:view")
	@RequestMapping(value = "form")
	public String form(UserInfo userInfo, Model model) {
		model.addAttribute("userInfo", userInfo);
		return "modules/user/userInfoForm";
	}

	@RequiresPermissions("user:userInfo:edit")
	@RequestMapping(value = "save")
	public String save(UserInfo userInfo, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, userInfo)) {
			return form(userInfo, model);
		}
		userInfoService.save(userInfo);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/user/userInfo/?repage";
	}

	@RequiresPermissions("user:userInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(UserInfo userInfo,
			RedirectAttributes redirectAttributes) {
		userInfoService.deleteEntity(userInfo);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/user/userInfo/?repage";
	}

	@RequiresPermissions("user:userInfo:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		userInfoService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}