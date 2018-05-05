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
import com.weimhc.modules.base.entity.Constellation;
import com.weimhc.modules.base.service.ConstellationService;

/**
 * 星座Controller
 * 
 * @author lc
 * @version 2016-06-24
 */
@Controller
@RequestMapping(value = "${adminPath}/base/constellation")
public class ConstellationController extends AdminBaseController {

	@Autowired
	private ConstellationService constellationService;

	@ModelAttribute
	public Constellation get(@RequestParam(required = false) String id) {
		Constellation entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = constellationService.get(id);
		}
		if (entity == null) {
			entity = new Constellation();
		}
		return entity;
	}

	@RequiresPermissions("base:constellation:view")
	@RequestMapping(value = { "list", "" })
	public String list(Constellation constellation, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Constellation> page = constellationService.findPage(
				new Page<Constellation>(request, response), constellation);
		model.addAttribute("page", page);
		return "modules/base/constellation/constellationList";
	}

	@RequiresPermissions("base:constellation:view")
	@RequestMapping(value = "form")
	public String form(Constellation constellation, Model model) {
		model.addAttribute("constellation", constellation);
		return "modules/base/constellation/constellationForm";
	}

	@RequiresPermissions("base:constellation:edit")
	@RequestMapping(value = "save")
	public String save(Constellation constellation, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, constellation)) {
			return form(constellation, model);
		}
		constellationService.save(constellation);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/base/constellation/?repage";
	}

	@RequiresPermissions("base:constellation:edit")
	@RequestMapping(value = "delete")
	public String delete(Constellation constellation,
			RedirectAttributes redirectAttributes) {
		constellationService.deleteEntity(constellation);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/base/constellation/?repage";
	}

	@RequiresPermissions("base:constellation:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		constellationService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}