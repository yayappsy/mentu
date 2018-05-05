/**
 * 
 */
package com.weimhc.admin.modules.web.company;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.modules.company.entity.CompanyLabel;
import com.weimhc.modules.company.service.CompanyLabelService;

/**
 * 企业标签Controller
 * @author lc
 * @version 2017-11-15
 */
@Controller
@RequestMapping(value = "${adminPath}/company/companyLabel")
public class CompanyLabelController extends AdminBaseController {

	@Autowired
	private CompanyLabelService companyLabelService;
	
	@ModelAttribute
	public CompanyLabel get(@RequestParam(required=false) String id) {
		CompanyLabel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = companyLabelService.get(id);
		}
		if (entity == null){
			entity = new CompanyLabel();
		}
		return entity;
	}
	
	@RequiresPermissions("company:companyLabel:view")
	@RequestMapping(value = {"list", ""})
	public String list(CompanyLabel companyLabel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CompanyLabel> page = companyLabelService.findPage(new Page<CompanyLabel>(request, response), companyLabel); 
		model.addAttribute("page", page);
		return "modules/company/companyLabelList";
	}

	@RequiresPermissions("company:companyLabel:view")
	@RequestMapping(value = "form")
	public String form(CompanyLabel companyLabel, Model model) {
		model.addAttribute("companyLabel", companyLabel);
		return "modules/company/companyLabelForm";
	}
	
	@RequiresPermissions("company:companyLabel:view")
	@RequestMapping(value = "view")
	public String view(CompanyLabel companyLabel, Model model) {
		model.addAttribute("companyLabel", companyLabel);
		return "modules/company/companyLabelView";
	}

	@RequiresPermissions("company:companyLabel:edit")
	@RequestMapping(value = "save")
	public String save(CompanyLabel companyLabel, Model model,BindingResult result, RedirectAttributes redirectAttributes) {
		if (!isValid(result, companyLabel)) {
			return form(companyLabel, model);
		}
		companyLabelService.save(companyLabel);
		addMessage(redirectAttributes, "保存企业标签成功");
		return "redirect:"+Global.getAdminPath()+"/company/companyLabel/?repage";
	}
	
	@RequiresPermissions("company:companyLabel:edit")
	@RequestMapping(value = "delete")
	public String delete(CompanyLabel companyLabel, RedirectAttributes redirectAttributes) {
		companyLabelService.delete(companyLabel);
		addMessage(redirectAttributes, "删除企业标签成功");
		return "redirect:"+Global.getAdminPath()+"/company/companyLabel/?repage";
	}

}