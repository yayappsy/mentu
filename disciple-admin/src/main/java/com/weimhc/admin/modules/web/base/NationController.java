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
import com.weimhc.modules.base.entity.Nation;
import com.weimhc.modules.base.service.NationService;

/**
 * 民族Controller
 * 
 * @author lc
 * @version 2017-01-05
 */
@Controller
@RequestMapping(value = "${adminPath}/base/nation")
public class NationController extends AdminBaseController {

	@Autowired
	private NationService nationService;

	@ModelAttribute
	public Nation get(@RequestParam(required = false) String id) {
		Nation entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = nationService.get(id);
		}
		if (entity == null) {
			entity = new Nation();
		}
		return entity;
	}

	@RequiresPermissions("base:nation:view")
	@RequestMapping(value = { "list", "" })
	public String list(Nation nation, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Nation> page = nationService
				.findPage(new Page<Nation>(request, response), nation);
		model.addAttribute("page", page);
		model.addAttribute("nation", nation);
		return "modules/base/nation/nationList";
	}

	@RequiresPermissions("base:nation:view")
	@RequestMapping(value = "form")
	public String form(Nation nation, Model model) {
		model.addAttribute("nation", nation);
		return "modules/base/nation/nationForm";
	}

	@RequiresPermissions("base:nation:edit")
	@RequestMapping(value = "save")
	public String save(Nation nation, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, nation)) {
			return form(nation, model);
		}
		nationService.save(nation);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/nation/?repage";
	}

	@RequiresPermissions("base:nation:delete")
	@RequestMapping(value = "delete")
	public String delete(Nation nation, RedirectAttributes redirectAttributes) {
		nationService.deleteEntity(nation);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/nation/?repage";
	}

	@RequiresPermissions("base:nation:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		nationService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}