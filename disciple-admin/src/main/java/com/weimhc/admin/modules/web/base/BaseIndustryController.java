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
import com.weimhc.modules.base.entity.BaseIndustry;
import com.weimhc.modules.base.service.BaseIndustryService;

/**
 * 行业Controller
 * 
 * @author lc
 * @version 2016-06-03
 */
@Controller
@RequestMapping(value = "${adminPath}/base/industry")
public class BaseIndustryController extends AdminBaseController {

	@Autowired
	private BaseIndustryService industryService;

	@ModelAttribute
	public BaseIndustry get(@RequestParam(required = false) String id) {
		BaseIndustry entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = industryService.get(id);
		}
		if (entity == null) {
			entity = new BaseIndustry();
		}
		return entity;
	}

	@RequiresPermissions("base:industry:view")
	@RequestMapping(value = { "list", "" })
	public String list(BaseIndustry industry, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<BaseIndustry> page = industryService
				.findPage(new Page<BaseIndustry>(request, response), industry);
		model.addAttribute("page", page);
		return "modules/base/industry/industryList";
	}

	@RequiresPermissions("base:industry:view")
	@RequestMapping(value = "form")
	public String form(BaseIndustry industry, Model model) {
		model.addAttribute("industry", industry);
		return "modules/base/industry/industryForm";
	}

	@RequiresPermissions("base:industry:edit")
	@RequestMapping(value = "save")
	public String save(BaseIndustry industry, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		industryService.save(industry);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/industry/?repage";
	}

	@RequiresPermissions("base:industry:edit")
	@RequestMapping(value = "delete")
	public String delete(BaseIndustry industry,
			RedirectAttributes redirectAttributes) {
		industryService.deleteEntity(industry);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/industry/?repage";
	}

	@RequiresPermissions("base:industry:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		industryService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}