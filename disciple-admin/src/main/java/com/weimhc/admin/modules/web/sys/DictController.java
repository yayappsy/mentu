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
import com.weimhc.modules.sys.entity.Dict;
import com.weimhc.modules.sys.service.DictService;

/**
 * 字典Controller
 * 
 * @author zsm
 * @version 2016-02-18
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dict")
public class DictController extends AdminBaseController {

	@Autowired
	private DictService dictService;

	@ModelAttribute
	public Dict get(@RequestParam(required = false) String id) {
		Dict entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = dictService.get(id);
		}
		if (entity == null) {
			entity = new Dict();
		}
		return entity;
	}

	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = { "list", "" })
	public String list(Dict dict, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<String> typeList = dictService.findTypeList();
		model.addAttribute("typeList", typeList);
		Page<Dict> page = dictService
				.findPage(new Page<Dict>(request, response), dict);
		model.addAttribute("page", page);
		return "modules/sys/dictList";
	}

	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = "form")
	public String form(Dict dict, Model model) {
		model.addAttribute("dict", dict);
		return "modules/sys/dictForm";
	}

	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "save")
	public String save(Dict dict, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, dict)) {
			return form(dict, model);
		}
		dictService.save(dict);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/sys/dict/?repage";
	}

	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "delete")
	public String delete(Dict dict, RedirectAttributes redirectAttributes) {
		dictService.deleteEntity(dict);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/sys/dict/?repage";
	}

	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		dictService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(
			@RequestParam(required = false) String type,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Dict dict = new Dict();
		dict.setType(type);
		List<Dict> list = dictService.findList(dict);
		for (int i = 0; i < list.size(); i++) {
			Dict e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			map.put("name", StringUtils.replace(e.getLabel(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}

	@ResponseBody
	@RequestMapping(value = "listData")
	public List<Dict> listData(@RequestParam(required = false) String type) {
		Dict dict = new Dict();
		dict.setType(type);
		return dictService.findList(dict);
	}
}
