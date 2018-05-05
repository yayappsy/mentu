/**
 * 
 */
package com.weimhc.admin.modules.web.sales;

import java.util.List;
import java.util.Map;

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
import com.google.common.collect.Maps;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.base.utils.RegionUtils;
import com.weimhc.modules.sales.entity.SalesNetwork;
import com.weimhc.modules.sales.service.SalesNetworkService;
import com.weimhc.modules.sales.utils.SalesNetworkUtils;

/**
 * 营业网点Controller
 * 
 * @author zsm
 * @version 2017-04-28
 */
@Controller
@RequestMapping(value = "${adminPath}/sales/salesNetwork")
public class SalesNetworkController extends AdminBaseController {

	@Autowired
	private SalesNetworkService salesNetworkService;

	@ModelAttribute
	public SalesNetwork get(@RequestParam(required = false) String id) {
		SalesNetwork entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = salesNetworkService.get(id);
		}
		if (entity == null) {
			entity = new SalesNetwork();
		}
		return entity;
	}

	@RequiresPermissions("sales:salesNetwork:view")
	@RequestMapping(value = { "list", "" })
	public String list(SalesNetwork salesNetwork, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<SalesNetwork> list = salesNetworkService.findList(salesNetwork);
		model.addAttribute("list", list);
		model.addAttribute("salesNetwork", salesNetwork);
		model.addAttribute("salesNetworkTypeList",
				SalesNetworkUtils.findSalesNetworkTypeAll());
		return "modules/sales/salesNetworkList";
	}

	@RequiresPermissions("sales:salesNetwork:view")
	@RequestMapping(value = "form")
	public String form(SalesNetwork salesNetwork, Model model) {
		if (salesNetwork.getParent() != null
				&& StringUtils.isNotBlank(salesNetwork.getParent().getId())) {
			salesNetwork.setParent(
					salesNetworkService.get(salesNetwork.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(salesNetwork.getId())) {
				SalesNetwork salesNetworkChild = new SalesNetwork();
				salesNetworkChild.setParent(
						new SalesNetwork(salesNetwork.getParent().getId()));
				List<SalesNetwork> list = salesNetworkService
						.findList(salesNetwork);
				if (list.size() > 0) {
					salesNetwork.setSort(list.get(list.size() - 1).getSort());
					if (salesNetwork.getSort() != null) {
						salesNetwork.setSort(salesNetwork.getSort() + 30);
					}
				}
			}
		}
		if (salesNetwork.getSort() == null) {
			salesNetwork.setSort(30);
		}
		salesNetwork
				.setLocation(RegionUtils.getRegion(salesNetwork.getLocation()));
		model.addAttribute("salesNetwork", salesNetwork);
		model.addAttribute("salesNetworkTypeList",
				SalesNetworkUtils.findSalesNetworkTypeAll());
		return "modules/sales/salesNetworkForm";
	}

	@RequiresPermissions("sales:salesNetwork:edit")
	@RequestMapping(value = "save")
	public String save(SalesNetwork salesNetwork, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, salesNetwork)) {
			return form(salesNetwork, model);
		}
		salesNetworkService.save(salesNetwork);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/sales/salesNetwork/?repage";
	}

	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("sales:salesNetwork:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<SalesNetwork> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			SalesNetwork entity = new SalesNetwork(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		salesNetworkService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/sales/salesNetwork/?repage";
	}

	@RequiresPermissions("sales:salesNetwork:edit")
	@RequestMapping(value = "delete")
	public String delete(SalesNetwork salesNetwork,
			RedirectAttributes redirectAttributes) {
		salesNetworkService.deleteEntity(salesNetwork);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/sales/salesNetwork/?repage";
	}

	@RequiresPermissions("sales:salesNetwork:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		salesNetworkService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(
			@RequestParam(required = false) String extId,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<SalesNetwork> list = salesNetworkService
				.findList(new SalesNetwork());
		for (int i = 0; i < list.size(); i++) {
			SalesNetwork e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId != null
					&& !extId.equals(e.getId())
					&& e.getParentIds().indexOf("," + extId + ",") == -1)) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}

}