/**
 * 
 */
package com.weimhc.admin.modules.web.sales;

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

import com.weimhc.framework.web.ResultMessage;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.modules.sales.entity.SalesNetworkType;
import com.weimhc.modules.sales.service.SalesNetworkTypeService;

/**
 * 营业网点类型Controller
 * @author zsm
 * @version 2017-04-28
 */
@Controller
@RequestMapping(value = "${adminPath}/sales/salesNetworkType")
public class SalesNetworkTypeController extends AdminBaseController {

	@Autowired
	private SalesNetworkTypeService salesNetworkTypeService;
	
	@ModelAttribute
	public SalesNetworkType get(@RequestParam(required=false) String id) {
		SalesNetworkType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = salesNetworkTypeService.get(id);
		}
		if (entity == null){
			entity = new SalesNetworkType();
		}
		return entity;
	}
	
	@RequiresPermissions("sales:salesNetworkType:view")
	@RequestMapping(value = {"list", ""})
	public String list(SalesNetworkType salesNetworkType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SalesNetworkType> page = salesNetworkTypeService.findPage(new Page<SalesNetworkType>(request, response), salesNetworkType); 
		model.addAttribute("page", page);
		model.addAttribute("salesNetworkType", salesNetworkType);
		return "modules/sales/salesNetworkTypeList";
	}

	@RequiresPermissions("sales:salesNetworkType:view")
	@RequestMapping(value = "form")
	public String form(SalesNetworkType salesNetworkType, Model model) {
		model.addAttribute("salesNetworkType", salesNetworkType);
		return "modules/sales/salesNetworkTypeForm";
	}

	@RequiresPermissions("sales:salesNetworkType:edit")
	@RequestMapping(value = "save")
	public String save(SalesNetworkType salesNetworkType, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, salesNetworkType)){
			return form(salesNetworkType, model);
		}
		salesNetworkTypeService.save(salesNetworkType);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/sales/salesNetworkType/?repage";
	}
	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("sales:salesNetworkType:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<SalesNetworkType> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			SalesNetworkType entity = new SalesNetworkType(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		salesNetworkTypeService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/sales/salesNetworkType/?repage";
	}
	
	@RequiresPermissions("sales:salesNetworkType:edit")
	@RequestMapping(value = "delete")
	public String delete(SalesNetworkType salesNetworkType, RedirectAttributes redirectAttributes) {
		salesNetworkTypeService.deleteEntity(salesNetworkType);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/sales/salesNetworkType/?repage";
	}

	@RequiresPermissions("sales:salesNetworkType:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		salesNetworkTypeService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}