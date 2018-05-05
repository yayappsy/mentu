/**
 * 
 */
package com.weimhc.admin.modules.web.article;

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
import com.weimhc.framework.utils.UploadFolder;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.article.entity.SystemDocument;
import com.weimhc.modules.article.service.SystemDocumentService;

/**
 * 系统文章，主要用于注册协议或其他行为提示Controller
 * 
 * @author zsm
 * @version 2016-02-18
 */
@Controller
@RequestMapping(value = "${adminPath}/article/systemDocument")
public class SystemDocumentController extends AdminBaseController {

	@Autowired
	private SystemDocumentService systemDocumentService;

	@ModelAttribute
	public SystemDocument get(@RequestParam(required = false) String id) {
		SystemDocument entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = systemDocumentService.get(id);
		}
		if (entity == null) {
			entity = new SystemDocument();
		}
		return entity;
	}

	@RequiresPermissions("article:systemDocument:view")
	@RequestMapping(value = { "list", "" })
	public String list(SystemDocument systemDocument,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<SystemDocument> page = systemDocumentService.findPage(
				new Page<SystemDocument>(request, response), systemDocument);
		model.addAttribute("page", page);
		return "admin/changheng/article/systemDocumentList";
	}

	@RequiresPermissions("article:systemDocument:view")
	@RequestMapping(value = "form")
	public String form(SystemDocument systemDocument, Model model) {
		model.addAttribute("systemDocument", systemDocument);
		model.addAttribute("uploadFolder", UploadFolder.article);
		return "admin/changheng/article/systemDocumentForm";
	}

	@RequiresPermissions("article:systemDocument:edit")
	@RequestMapping(value = "save")
	public String save(SystemDocument systemDocument, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, systemDocument)) {
			return form(systemDocument, model);
		}
		systemDocumentService.save(systemDocument);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/article/systemDocument/?repage";
	}

	@RequiresPermissions("article:systemDocument:edit")
	@RequestMapping(value = "delete")
	public String delete(SystemDocument systemDocument,
			RedirectAttributes redirectAttributes) {
		systemDocumentService.deleteEntity(systemDocument);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/article/systemDocument/?repage";
	}

	@RequiresPermissions("article:systemDocument:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		systemDocumentService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}