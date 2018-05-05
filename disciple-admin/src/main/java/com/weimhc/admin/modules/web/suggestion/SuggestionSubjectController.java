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
import com.weimhc.modules.suggestion.entity.SuggestionSubject;
import com.weimhc.modules.suggestion.service.SuggestionSubjectService;

/**
 * 建议主题Controller
 * 
 * @author lc
 * @version 2016-12-01
 */
@Controller
@RequestMapping(value = "${adminPath}/suggestion/suggestionSubject")
public class SuggestionSubjectController extends AdminBaseController {

	@Autowired
	private SuggestionSubjectService suggestionSubjectService;

	@ModelAttribute
	public SuggestionSubject get(@RequestParam(required = false) String id) {
		SuggestionSubject entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = suggestionSubjectService.get(id);
		}
		if (entity == null) {
			entity = new SuggestionSubject();
		}
		return entity;
	}

	@RequiresPermissions("suggestion:suggestionSubject:view")
	@RequestMapping(value = { "list", "" })
	public String list(SuggestionSubject suggestionSubject,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<SuggestionSubject> page = suggestionSubjectService.findPage(
				new Page<SuggestionSubject>(request, response),
				suggestionSubject);
		model.addAttribute("page", page);
		return "modules/suggestion/suggestionSubjectList";
	}

	@RequiresPermissions("suggestion:suggestionSubject:view")
	@RequestMapping(value = "form")
	public String form(SuggestionSubject suggestionSubject, Model model) {
		model.addAttribute("suggestionSubject", suggestionSubject);
		return "modules/suggestion/suggestionSubjectForm";
	}

	@RequiresPermissions("suggestion:suggestionSubject:edit")
	@RequestMapping(value = "save")
	public String save(SuggestionSubject suggestionSubject,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		/*if (!isValid(result, suggestionSubject)){
			return form(suggestionSubject, model);
		}*/
		suggestionSubjectService.save(suggestionSubject);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/suggestion/suggestionSubject/?repage";
	}

	@RequiresPermissions("suggestion:suggestionSubject:edit")
	@RequestMapping(value = "delete")
	public String delete(SuggestionSubject suggestionSubject,
			RedirectAttributes redirectAttributes) {
		suggestionSubjectService.deleteEntity(suggestionSubject);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/suggestion/suggestionSubject/?repage";
	}

	@RequiresPermissions("suggestion:suggestionSubject:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		suggestionSubjectService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}