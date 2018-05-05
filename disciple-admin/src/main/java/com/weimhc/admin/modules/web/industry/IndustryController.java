/**
 * 
 */
package com.weimhc.admin.modules.web.industry;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.javamg.common.config.Global;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.modules.industry.entity.Industry;
import com.weimhc.modules.industry.service.IndustryService;


/**
 * 行业分类Controller
 * @author lc
 * @version 2017-11-13
 */
@Controller@RequestMapping(value = "${adminPath}/industry/industry")
public class IndustryController extends AdminBaseController {

	@Autowired
	private IndustryService industryService;
	
	@ModelAttribute
	public Industry get(@RequestParam(required=false) String id) {
		Industry entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = industryService.get(id);
		}
		if (entity == null){
			entity = new Industry();
		}
		return entity;
	}
	
	@RequiresPermissions("industry:industry:view")
	@RequestMapping(value = { "list", "" })
	public String list(Industry industry, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<Industry> list = industryService.findAllList(industry);
		model.addAttribute("list", list);
		model.addAttribute("industry", industry);
		return "modules/industry/industryList";
	}

	@RequiresPermissions("industry:industry:view")
	@RequestMapping(value = "form")
	public String form(Industry industry,HttpServletRequest request, Model model) {
		if (industry.getParent() != null
				&& StringUtils.isNotBlank(industry.getParent().getId())) {
			industry.setParent(
					industryService.get(industry.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(industry.getId())) {
				Industry industryChild = new Industry();
				industryChild.setParent(
						new Industry(industry.getParent().getId()));
				List<Industry> list = industryService.findList(industry);
				if (list.size() > 0) {
					industry.setSort(list.get(list.size() - 1).getSort());
					if (industry.getSort() != null) {
						industry.setSort(industry.getSort() + 30);
					}
				}
			}
		}
		if (industry.getSort() == null) {
			industry.setSort(30);
		}
		model.addAttribute("industry", industry);
		return "modules/industry/industryForm";
	}
	
	@RequiresPermissions("industry:industry:view")
	@RequestMapping(value = "view")
	public String view(Industry industry, Model model) {
		model.addAttribute("industry", industry);
		return "modules/industry/industryView";
	}

	@RequiresPermissions("industry:industry:edit")
	@RequestMapping(value = "save")
	public String save(Industry industry,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, industry)){
			return form(industry, request, model);
		}
		industryService.save(industry);
		addMessage(redirectAttributes, "保存行业分类成功");
		return "redirect:"+Global.getAdminPath()+"/industry/industry/?repage";
	}
	
	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("industry:industry:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "admin.alert.demoMode");
			return "redirect:" + adminPath + "/industry/industry/";
		}
		List<Industry> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			Industry industry = new Industry(ids[i]);
			industry.setSort(sorts[i]);
			list.add(industry);
		}
		industryService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + adminPath + "/industry/industry";
	}
	
	@RequiresPermissions("industry:industry:edit")
	@RequestMapping(value = "delete")
	public String delete(Industry industry, RedirectAttributes redirectAttributes) {
		industryService.delete(industry);
		addMessage(redirectAttributes, "删除行业分类成功");
		return "redirect:"+Global.getAdminPath()+"/industry/industry/?repage";
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(
			@RequestParam(required = false) String extId,
			HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		String topLevelId = searchQueryString(request, "topLevelId");

		Industry industry = new Industry();
		industry.setTopLevelId(topLevelId);
		List<Industry> list = industryService.findAllList(industry);
		for (int i = 0; i < list.size(); i++) {
			Industry e = list.get(i);
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