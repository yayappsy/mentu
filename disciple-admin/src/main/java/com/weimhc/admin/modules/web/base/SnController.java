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
import com.weimhc.modules.base.entity.Sn;
import com.weimhc.modules.base.service.SnService;

/**
 * 编号Controller
 * 
 * @author zsm
 * @version 2016-02-01
 */
@Controller
@RequestMapping(value = "${adminPath}/sn/sn")
public class SnController extends AdminBaseController {

	@Autowired
	private SnService snService;

	@ModelAttribute
	public Sn get(@RequestParam(required = false) String id) {
		Sn entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = snService.get(id);
		}
		if (entity == null) {
			entity = new Sn();
		}
		return entity;
	}

	@RequiresPermissions("sn:sn:view")
	@RequestMapping(value = { "list", "" })
	public String list(Sn sn, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Sn> page = snService.findPage(new Page<Sn>(request, response), sn);
		model.addAttribute("page", page);
		return "modules/base/sn/snList";
	}

	@RequiresPermissions("sn:sn:view")
	@RequestMapping(value = "form")
	public String form(Sn sn, Model model) {
		model.addAttribute("sn", sn);
		return "modules/base/sn/snForm";
	}

	@RequiresPermissions("sn:sn:edit")
	@RequestMapping(value = "save")
	public String save(Sn sn, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, sn)) {
			return form(sn, model);
		}
		snService.save(sn);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/sn/sn/?repage";
	}

	@RequiresPermissions("sn:sn:edit")
	@RequestMapping(value = "delete")
	public String delete(Sn sn, RedirectAttributes redirectAttributes) {
		snService.deleteEntity(sn);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/sn/sn/?repage";
	}

	@RequiresPermissions("ad:AdClick:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		snService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}