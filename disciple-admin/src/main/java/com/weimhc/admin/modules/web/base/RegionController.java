/**
 * 
 */
package com.weimhc.admin.modules.web.base;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.base.entity.Region;
import com.weimhc.modules.base.service.RegionService;
import com.weimhc.modules.sys.entity.Area;
import com.weimhc.modules.sys.utils.AreaUtils;

/**
 * 服务人员Controller
 * 
 * @author zsm
 * @version 2016-12-06
 */
@Controller
@RequestMapping(value = "${adminPath}/base/region")
public class RegionController extends AdminBaseController {

	@Autowired
	private RegionService regionService;

	@ModelAttribute
	public Region get(@RequestParam(required = false) String id) {
		Region entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = regionService.get(id);
		}
		if (entity == null) {
			entity = new Region();
		}
		return entity;
	}

	@RequiresPermissions("base:region:view")
	@RequestMapping(value = { "list", "" })
	public String list(Region region, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<Region> page = regionService.findPage(new Page<Region>(request, response), region);
		model.addAttribute("page", page);
		return "modules/base/regionList";
	}

	@RequiresPermissions("base:region:view")
	@RequestMapping(value = "form")
	public String form(Region region, Model model) {
		model.addAttribute("region", region);
		return "modules/base/regionForm";
	}

	@RequiresPermissions("base:region:edit")
	@RequestMapping(value = "save")
	public String save(Region region, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, region)) {
			return form(region, model);
		}
		regionService.save(region);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/region/?repage";
	}

	@RequiresPermissions("base:region:edit")
	@RequestMapping(value = "delete")
	public String delete(Region region, RedirectAttributes redirectAttributes) {
		regionService.deleteEntity(region);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/region/?repage";
	}

	@RequiresPermissions("base:region:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		regionService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

	@SuppressWarnings("unchecked")
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String areaType, HttpServletResponse response) {
		int inType = NumberUtils.toInt(areaType) == 0 ? 4 : NumberUtils.toInt(areaType);
		// AreaUtils.removeCache("treeData_" + inType);
		List<Map<String, Object>> mapList = (List<Map<String, Object>>) AreaUtils
				.getCache("treeData_" + inType);
		if (mapList == null) {
			mapList = Lists.newArrayList();
			List<Area> list = AreaUtils.findAll();
			for (int i = 0; i < list.size(); i++) {
				Area e = list.get(i);
				if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId())
						&& e.getParentIds().indexOf("," + extId + ",") == -1)) {
					if (NumberUtils.toInt(e.getType()) <= inType) {
						Map<String, Object> map = Maps.newHashMap();
						map.put("id", e.getId());
						map.put("pId", e.getParentId());
						map.put("name", e.getName());
						map.put("mergerName", e.getMergerName());
						map.put("type", e.getType());
						mapList.add(map);
					}
				}
			}
			AreaUtils.putCache("treeData_" + inType, mapList);
		}
		return mapList;
	}
}