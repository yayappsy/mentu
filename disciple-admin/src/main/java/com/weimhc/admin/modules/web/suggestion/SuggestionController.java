/**
 * 
 */
package com.weimhc.admin.modules.web.suggestion;

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
import com.weimhc.modules.suggestion.entity.Suggestion;
import com.weimhc.modules.suggestion.service.SuggestionService;
import com.weimhc.modules.suggestion.utils.SuggestionSubjectUtils;

/**
 * 建议Controller
 * 
 * @author lc
 * @version 2016-12-01
 */
@Controller
@RequestMapping(value = "${adminPath}/suggestion/suggestion")
public class SuggestionController extends AdminBaseController {

	@Autowired
	private SuggestionService suggestionService;

	@ModelAttribute
	public Suggestion get(@RequestParam(required = false) String id) {
		Suggestion entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = suggestionService.get(id);
		}
		if (entity == null) {
			entity = new Suggestion();
		}
		return entity;
	}

	@RequiresPermissions("suggestion:suggestion:view")
	@RequestMapping(value = { "list", "" })
	public String list(Suggestion suggestion, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Suggestion> page = suggestionService
				.findPage(new Page<Suggestion>(request, response), suggestion);
		model.addAttribute("page", page);
		model.addAttribute("suggestionSubjectList",
				SuggestionSubjectUtils.findAll());
		return "modules/suggestion/suggestionList";
	}

	@RequiresPermissions("suggestion:suggestion:view")
	@RequestMapping(value = "form")
	public String form(Suggestion suggestion, Model model) {
		model.addAttribute("suggestion", suggestion);
		model.addAttribute("suggestionSubjectList",
				SuggestionSubjectUtils.findAll());
		return "modules/suggestion/suggestionForm";
	}

	@RequiresPermissions("suggestion:suggestion:edit")
	@RequestMapping(value = "save")
	public String save(Suggestion suggestion, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, suggestion)) {
			return form(suggestion, model);
		}
		suggestionService.save(suggestion);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/suggestion/suggestion/?repage";
	}

	@RequiresPermissions("suggestion:suggestion:edit")
	@RequestMapping(value = "delete")
	public String delete(Suggestion suggestion,
			RedirectAttributes redirectAttributes) {
		suggestionService.deleteEntity(suggestion);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/suggestion/suggestion/?repage";
	}

	@RequiresPermissions("suggestion:suggestion:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		suggestionService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}