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
import com.weimhc.modules.base.entity.ThirdVideoCloud;
import com.weimhc.modules.base.service.ThirdVideoCloudService;

/**
 * 血型Controller
 * 
 * @author lc
 * @version 2016-06-24
 */
@Controller
@RequestMapping(value = "${adminPath}/base/thirdVideoCloud")
public class ThirdVideoCloudController extends AdminBaseController {

	@Autowired
	private ThirdVideoCloudService thirdVideoCloudService;

	@ModelAttribute
	public ThirdVideoCloud get(@RequestParam(required = false) String id) {
		ThirdVideoCloud entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = thirdVideoCloudService.get(id);
		}
		if (entity == null) {
			entity = new ThirdVideoCloud();
		}
		return entity;
	}

	@RequiresPermissions("base:thirdVideoCloud:view")
	@RequestMapping(value = { "list", "" })
	public String list(ThirdVideoCloud thirdVideoCloud,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<ThirdVideoCloud> page = thirdVideoCloudService.findPage(
				new Page<ThirdVideoCloud>(request, response), thirdVideoCloud);
		model.addAttribute("page", page);
		return "modules/base/thirdVideoCloudList";
	}

	@RequiresPermissions("base:thirdVideoCloud:view")
	@RequestMapping(value = "form")
	public String form(ThirdVideoCloud thirdVideoCloud, Model model) {
		model.addAttribute("thirdVideoCloud", thirdVideoCloud);
		return "modules/base/thirdVideoCloudForm";
	}

	@RequiresPermissions("base:thirdVideoCloud:edit")
	@RequestMapping(value = "save")
	public String save(ThirdVideoCloud thirdVideoCloud, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, thirdVideoCloud)) {
			return form(thirdVideoCloud, model);
		}
		thirdVideoCloudService.save(thirdVideoCloud);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/base/thirdVideoCloud/?repage";
	}

	@RequiresPermissions("base:thirdVideoCloud:edit")
	@RequestMapping(value = "delete")
	public String delete(ThirdVideoCloud thirdVideoCloud,
			RedirectAttributes redirectAttributes) {
		thirdVideoCloudService.deleteEntity(thirdVideoCloud);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/base/thirdVideoCloud/?repage";
	}

	@RequiresPermissions("base:thirdVideoCloud:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		thirdVideoCloudService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}