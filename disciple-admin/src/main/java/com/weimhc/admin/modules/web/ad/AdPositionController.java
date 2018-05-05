/**
 * 
 */
package com.weimhc.admin.modules.web.ad;

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
import com.weimhc.modules.ad.entity.AdPosition;
import com.weimhc.modules.ad.entity.AdPositionCode;
import com.weimhc.modules.ad.service.AdPositionService;

/**
 * 广告位Controller
 * 
 * @author lc
 * @version 2016-06-07
 */
@Controller
@RequestMapping(value = "${adminPath}/ad/adPosition")
public class AdPositionController extends AdminBaseController {

	@Autowired
	private AdPositionService adPositionService;

	@ModelAttribute
	public AdPosition get(@RequestParam(required = false) String id) {
		AdPosition entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = adPositionService.get(id);
		}
		if (entity == null) {
			entity = new AdPosition();
		}
		return entity;
	}

	@RequiresPermissions("ad:adPosition:view")
	@RequestMapping(value = { "list", "" })
	public String list(AdPosition adPosition, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<AdPosition> page = adPositionService
				.findPage(new Page<AdPosition>(request, response), adPosition);
		model.addAttribute("page", page);
		return "modules/ad/adPositionList";
	}

	@RequiresPermissions("ad:adPosition:view")
	@RequestMapping(value = "form")
	public String form(AdPosition adPosition, Model model) {
		model.addAttribute("adPosition", adPosition);
		model.addAttribute("keyword", AdPositionCode.values());

		return "modules/ad/adPositionForm";
	}

	@RequiresPermissions("ad:adPosition:edit")
	@RequestMapping(value = "save")
	public String save(AdPosition adPosition, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		/*if (!isValid(result, adPosition)) {
			return form(adPosition, model);
		}*/
		adPositionService.save(adPosition);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/ad/adPosition/?repage";
	}

	@RequiresPermissions("ad:adPosition:edit")
	@RequestMapping(value = "delete")
	public String delete(AdPosition adPosition,
			RedirectAttributes redirectAttributes) {
		adPositionService.deleteEntity(adPosition);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/ad/adPosition/?repage";
	}

	@RequiresPermissions("ad:adPosition:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		adPositionService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}