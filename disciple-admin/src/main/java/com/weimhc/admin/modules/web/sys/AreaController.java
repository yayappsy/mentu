/**
 * 
 */
package com.weimhc.admin.modules.web.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
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
import com.weimhc.modules.sys.entity.Area;
import com.weimhc.modules.sys.service.AreaService;
import com.weimhc.modules.sys.utils.AreaUtils;

/**
 * 地区Controller
 * 
 * @author zsm
 * @version 2016-02-18
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/area")
public class AreaController extends AdminBaseController {

	@Autowired
	private AreaService areaService;

	@ModelAttribute("area")
	public Area get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return areaService.get(id);
		} else {
			return new Area();
		}
	}

	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = { "list", "" })
	public String list(Area area, Model model) {
		model.addAttribute("list", areaService.findAll());
		return "modules/sys/areaList";
	}

	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = "form")
	public String form(Area area, Model model) {
		if (area.getParent() != null
				&& StringUtils.isNotBlank(area.getParent().getId())) {
			area.setParent(areaService.get(area.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(area.getId())) {
				Area areaChild = new Area();
				areaChild.setParent(new Area(area.getParent().getId()));
				List<Area> list = areaService.findList(area);
				if (list.size() > 0) {
					area.setSort(list.get(list.size() - 1).getSort());
					if (area.getSort() != null) {
						area.setSort(area.getSort() + 30);
					}
				}
			}
		}
		if (area.getSort() == null) {
			area.setSort(30);
		}
		model.addAttribute("area", area);
		return "modules/sys/areaForm";
	}

	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "save")
	public String save(Area area, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, area)) {
			return form(area, model);
		}
		areaService.save(area);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/sys/area/?repage";
	}

	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "delete")
	public String delete(Area area, RedirectAttributes redirectAttributes) {
		areaService.deleteEntity(area);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/sys/area/?repage";
	}

	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		areaService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

	@SuppressWarnings("unchecked")
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(
			@RequestParam(required = false) String extId,
			@RequestParam(required = false) String areaType,
			HttpServletResponse response) {
		int inType = NumberUtils.toInt(areaType) == 0 ? 4
				: NumberUtils.toInt(areaType);
		// AreaUtils.removeCache("treeData_" + inType);
		List<Map<String, Object>> mapList = (List<Map<String, Object>>) AreaUtils
				.getCache("treeData_" + inType);
		if (mapList == null) {
			mapList = Lists.newArrayList();
			List<Area> list = AreaUtils.findAll();
			for (int i = 0; i < list.size(); i++) {
				Area e = list.get(i);
				if (StringUtils.isBlank(extId) || (extId != null
						&& !extId.equals(e.getId())
						&& e.getParentIds().indexOf("," + extId + ",") == -1)) {
					if (NumberUtils.toInt(e.getType()) <= inType) {
						Map<String, Object> map = Maps.newHashMap();
						map.put("id", e.getId());
						map.put("pId", e.getParentId());
						map.put("name", e.getName());
						map.put("type", e.getType());
						mapList.add(map);
					}
				}
			}
			AreaUtils.putCache("treeData_" + inType, mapList);
		}
		logger.debug("over");

		return mapList;
	}
	
	@SuppressWarnings("unchecked")
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeDataCity")
	public List<Map<String, Object>> treeDataCity(
			@RequestParam(required = false) String extId,
			@RequestParam(required = false) String areaType,
			HttpServletResponse response) {
		int inType = NumberUtils.toInt(areaType) == 0 ? 3
				: NumberUtils.toInt(areaType);
		// AreaUtils.removeCache("treeData_" + inType);
		List<Map<String, Object>> mapList = (List<Map<String, Object>>) AreaUtils
				.getCache("treeData_" + inType);
		if (mapList == null) {
			mapList = Lists.newArrayList();
			List<Area> list = AreaUtils.findAll();
			for (int i = 0; i < list.size(); i++) {
				Area e = list.get(i);
				if (StringUtils.isBlank(extId) || (extId != null
						&& !extId.equals(e.getId())
						&& e.getParentIds().indexOf("," + extId + ",") == -1)) {
					if (NumberUtils.toInt(e.getType()) <= inType) {
						Map<String, Object> map = Maps.newHashMap();
						map.put("id", e.getId());
						map.put("pId", e.getParentId());
						map.put("name", e.getName());
						map.put("type", e.getType());
						mapList.add(map);
					}
				}
			}
			AreaUtils.putCache("treeData_" + inType, mapList);
		}
		logger.debug("over");

		return mapList;
	}
}
