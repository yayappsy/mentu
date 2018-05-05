/**
 * 
 */
package com.weimhc.admin.modules.web.navigation;

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
import com.weimhc.modules.navigation.entity.Navigation;
import com.weimhc.modules.navigation.service.NavigationService;

/**
 * 导航栏Controller
 * 
 * @author zsm
 * @version 2017-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/navigation/navigation")
public class NavigationController extends AdminBaseController {

	@Autowired
	private NavigationService navigationService;

	@ModelAttribute
	public Navigation get(@RequestParam(required = false) String id) {
		Navigation entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = navigationService.get(id);
		}
		if (entity == null) {
			entity = new Navigation();
		}
		return entity;
	}

	@RequiresPermissions("navigation:navigation:view")
	@RequestMapping(value = { "list", "" })
	public String list(Navigation navigation, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<Navigation> list = navigationService.findAllList(navigation);
		model.addAttribute("list", list);
		model.addAttribute("navigation", navigation);
		return "modules/navigation/navigationList";
	}

	@RequiresPermissions("navigation:navigation:view")
	@RequestMapping(value = "form")
	public String form(Navigation navigation, HttpServletRequest request,
			Model model) {
		if (navigation.getParent() != null
				&& StringUtils.isNotBlank(navigation.getParent().getId())) {
			navigation.setParent(
					navigationService.get(navigation.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(navigation.getId())) {
				Navigation navigationChild = new Navigation();
				navigationChild.setParent(
						new Navigation(navigation.getParent().getId()));
				List<Navigation> list = navigationService.findList(navigation);
				if (list.size() > 0) {
					navigation.setSort(list.get(list.size() - 1).getSort());
					if (navigation.getSort() != null) {
						navigation.setSort(navigation.getSort() + 30);
					}
				}
			}
		}
		if (navigation.getSort() == null) {
			navigation.setSort(30);
		}
		model.addAttribute("navigation", navigation);
		return "modules/navigation/navigationForm";
	}

	@RequiresPermissions("navigation:navigation:edit")
	@RequestMapping(value = "save")
	public String save(Navigation navigation, BindingResult result, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (!isValid(result, navigation)) {
			return form(navigation, request, model);
		}
		navigationService.save(navigation);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/navigation/navigation/?repage";
	}

	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("navigation:navigation:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "admin.alert.demoMode");
			return "redirect:" + adminPath + "/navigation/navigation/";
		}
		List<Navigation> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			Navigation navigation = new Navigation(ids[i]);
			navigation.setSort(sorts[i]);
			list.add(navigation);
		}
		navigationService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + adminPath + "/navigation/navigation";
	}

	@RequiresPermissions("navigation:navigation:edit")
	@RequestMapping(value = "delete")
	public String delete(Navigation navigation,
			RedirectAttributes redirectAttributes) {
		navigationService.deleteEntity(navigation);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/navigation/navigation/?repage";
	}

	@RequiresPermissions("navigation:navigation:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		navigationService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(
			@RequestParam(required = false) String extId,
			HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		String topLevelId = searchQueryString(request, "topLevelId");

		Navigation navigation = new Navigation();
		navigation.setTopLevelId(topLevelId);
		List<Navigation> list = navigationService.findAllList(navigation);
		for (int i = 0; i < list.size(); i++) {
			Navigation e = list.get(i);
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