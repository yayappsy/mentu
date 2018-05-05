/**
 * 
 */
package com.weimhc.admin.modules.web.company;

import java.util.List;

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

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.base.entity.PathType;
import com.weimhc.modules.company.entity.CompanyFile;
import com.weimhc.modules.company.service.CompanyFileService;

/**
 * 企业文件Controller
 * 
 * @author zsm
 * @version 2017-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/company/companyFile")
public class CompanyFileController extends AdminBaseController {

	@Autowired
	private CompanyFileService companyFileService;

	@ModelAttribute
	public CompanyFile get(@RequestParam(required = false) String id) {
		CompanyFile entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = companyFileService.get(id);
		}
		if (entity == null) {
			entity = new CompanyFile();
		}
		return entity;
	}

	@RequiresPermissions("company:companyFile:view")
	@RequestMapping(value = { "list", "" })
	public String list(CompanyFile companyFile, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<CompanyFile> page = companyFileService.findPage(
				new Page<CompanyFile>(request, response), companyFile);
		model.addAttribute("page", page);
		model.addAttribute("companyFile", companyFile);
		return "modules/company/companyFileList";
	}

	@RequiresPermissions("company:companyFile:view")
	@RequestMapping(value = "form")
	public String form(CompanyFile companyFile, Model model) {
		companyFile.setPathType(PathType.local);
		model.addAttribute("companyFile", companyFile);
		model.addAttribute("pathTypes", PathType.values());
		return "modules/company/companyFileForm";
	}

	@RequiresPermissions("company:companyFile:edit")
	@RequestMapping(value = "save")
	public String save(CompanyFile companyFile, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, companyFile)) {
			return form(companyFile, model);
		}
		companyFileService.save(companyFile);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/company/companyFile/?repage";
	}

	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("company:companyFile:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<CompanyFile> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			CompanyFile entity = new CompanyFile(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		companyFileService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/company/companyFile/?repage";
	}

	@RequiresPermissions("company:companyFile:edit")
	@RequestMapping(value = "delete")
	public String delete(CompanyFile companyFile,
			RedirectAttributes redirectAttributes) {
		companyFileService.deleteEntity(companyFile);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/company/companyFile/?repage";
	}

	@RequiresPermissions("company:companyFile:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		companyFileService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}