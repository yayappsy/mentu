/**
 * 
 */
package com.weimhc.admin.modules.web.base;

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
import com.weimhc.modules.base.entity.FriendLink;
import com.weimhc.modules.base.service.FriendLinkService;

/**
 * 友情链接Controller
 * 
 * @author lc
 * @version 2016-08-18
 */
@Controller
@RequestMapping(value = "${adminPath}/base/friendLink")
public class FriendLinkController extends AdminBaseController {

	@Autowired
	private FriendLinkService friendLinkService;

	@ModelAttribute
	public FriendLink get(@RequestParam(required = false) String id) {
		FriendLink entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = friendLinkService.get(id);
		}
		if (entity == null) {
			entity = new FriendLink();
		}
		return entity;
	}

	@RequiresPermissions("base:friendLink:view")
	@RequestMapping(value = { "list", "" })
	public String list(FriendLink friendLink, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<FriendLink> page = friendLinkService
				.findPage(new Page<FriendLink>(request, response), friendLink);
		model.addAttribute("page", page);
		return "modules/base/friendLinkList";
	}

	@RequiresPermissions("base:friendLink:view")
	@RequestMapping(value = "form")
	public String form(FriendLink friendLink, Model model) {
		model.addAttribute("friendLink", friendLink);
		return "modules/base/friendLinkForm";
	}

	@RequiresPermissions("base:friendLink:edit")
	@RequestMapping(value = "save")
	public String save(FriendLink friendLink, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, friendLink)) {
			return form(friendLink, model);
		}
		friendLinkService.save(friendLink);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/friendLink/?repage";
	}

	@RequiresPermissions("base:friendLink:edit")
	@RequestMapping(value = "delete")
	public String delete(FriendLink friendLink,
			RedirectAttributes redirectAttributes) {
		friendLinkService.deleteEntity(friendLink);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/friendLink/?repage";
	}

	@RequiresPermissions("base:friendLink:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		friendLinkService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}