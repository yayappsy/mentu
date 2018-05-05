/**
 * 
 */
package com.weimhc.admin.modules.web.sys;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.service.SystemService;
import com.weimhc.admin.core.utils.AdminUserUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.modules.sys.entity.Menu;

/**
 * 菜单Controller
 * 
 * @author zsm
 * @version 2016-02-18
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/menu")
public class MenuController extends AdminBaseController {

	@Autowired
	private SystemService systemService;

	@ModelAttribute("menu")
	public Menu get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return systemService.getMenu(id);
		} else {
			return new Menu();
		}
	}

	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = { "list", "" })
	public String list(Model model) {
		List<Menu> list = Lists.newArrayList();
		List<Menu> sourcelist = systemService.findAllMenu();
		Menu.sortList(sourcelist, list, Menu.getRootId(), true);
		model.addAttribute("list", list);
		return "modules/sys/menuList";
	}

	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = "form")
	public String form(Menu menu, Model model) {
		if (menu.getParent() == null || menu.getParent().getId() == null) {
			menu.setParent(new Menu(Menu.getRootId()));
		}
		if (menu.getIsShow() == null) {
			menu.setIsShow(true);
		}
		menu.setParent(systemService.getMenu(menu.getParent().getId()));
		// 获取排序号，最末节点排序号+30
		if (StringUtils.isBlank(menu.getId())) {
			List<Menu> list = Lists.newArrayList();
			List<Menu> sourcelist = systemService.findAllMenu();
			Menu.sortList(sourcelist, list, menu.getParentId(), false);
			if (list.size() > 0) {
				menu.setSort(list.get(list.size() - 1).getSort() + 30);
			}
		}
		model.addAttribute("menu", menu);
		return "modules/sys/menuForm";
	}

	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value = "save")
	public String save(Menu menu, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!AdminUserUtils.getUser().isAdmin()) {
			addMessage(redirectAttributes, "越权操作，只有超级管理员才能添加或修改数据！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
		/*if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "admin.alert.demoMode");
			return "redirect:" + adminPath + "/sys/menu/";
		}*/
		if (!isValid(result, menu)) {
			return form(menu, model);
		}
		systemService.saveMenu(menu);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/sys/menu/?repage";
	}

	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value = "delete")
	public String delete(Menu menu, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "admin.alert.demoMode");
			return "redirect:" + adminPath + "/sys/menu/";
		}
		// if (Menu.isRoot(id)){
		// addMessage(redirectAttributes, "删除菜单失败, 不允许删除顶级菜单或编号为空");
		// }else{
		menu.getSqlMap().put("handle", request.getParameter("handle"));
		systemService.deleteMenu(menu);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);

		// }
		return "redirect:" + Global.getAdminPath() + "/sys/menu/?repage";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "tree")
	public String tree() {
		return "modules/sys/menuTree";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "treeselect")
	public String treeselect(String parentId, Model model) {
		model.addAttribute("parentId", parentId);
		return "modules/sys/menuTreeselect";
	}

	/**
	 * 批量修改菜单排序
	 */
	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "admin.alert.demoMode");
			return "redirect:" + adminPath + "/sys/menu/";
		}
		for (int i = 0; i < ids.length; i++) {
			Menu menu = new Menu(ids[i]);
			menu.setSort(sorts[i]);
			systemService.updateMenuSort(menu);
		}
		addMessage(redirectAttributes, "保存菜单排序成功!");
		return "redirect:" + adminPath + "/sys/menu/";
	}

	/**
	 * isShowHide是否显示隐藏菜单
	 * 
	 * @param extId
	 * @param isShowHidden
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String isShowHide, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Menu> list = systemService.findAllMenu();
		for (int i = 0; i < list.size(); i++) {
			Menu e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId())
					&& e.getParentIds().indexOf("," + extId + ",") == -1)) {
				if (isShowHide != null && isShowHide.equals("0") && !e.getIsShow()) {
					continue;
				}
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
