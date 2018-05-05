/**
 * 
 */
package com.weimhc.admin.modules.web.collect;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.modules.collect.entity.Collect;
import com.weimhc.modules.collect.entity.Collect.CollectCategory;
import com.weimhc.modules.collect.service.CollectService;

/**
 * 收藏Controller
 * @author lc
 * @version 2017-11-15
 */
@Controller
@RequestMapping(value = "${adminPath}/collect/collect")
public class CollectController extends AdminBaseController {

	@Autowired
	private CollectService collectService;
	
	@ModelAttribute
	public Collect get(@RequestParam(required=false) String id) {
		Collect entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = collectService.get(id);
		}
		if (entity == null){
			entity = new Collect();
		}
		return entity;
	}
	
	@RequiresPermissions("collect:collect:view")
	@RequestMapping(value = {"list", ""})
	public String list(Collect collect, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Collect> page = collectService.findPage(new Page<Collect>(request, response), collect); 
		model.addAttribute("page", page);
		return "modules/collect/collectList";
	}

	@RequiresPermissions("collect:collect:view")
	@RequestMapping(value = "form")
	public String form(Collect collect, Model model) {
		model.addAttribute("collect", collect);
		model.addAttribute("collectCategorys", CollectCategory.values());
		return "modules/collect/collectForm";
	}
	
	@RequiresPermissions("collect:collect:view")
	@RequestMapping(value = "view")
	public String view(Collect collect, Model model) {
		model.addAttribute("collect", collect);
		return "modules/collect/collectView";
	}

	@RequiresPermissions("collect:collect:edit")
	@RequestMapping(value = "save")
	public String save(Collect collect, Model model, BindingResult result,RedirectAttributes redirectAttributes) {
		if (!isValid(result, collect)) {
			return form(collect, model);
		}
		collectService.save(collect);
		addMessage(redirectAttributes, "保存收藏成功");
		return "redirect:"+Global.getAdminPath()+"/collect/collect/?repage";
	}
	
	@RequiresPermissions("collect:collect:edit")
	@RequestMapping(value = "delete")
	public String delete(Collect collect, RedirectAttributes redirectAttributes) {
		collectService.delete(collect);
		addMessage(redirectAttributes, "删除收藏成功");
		return "redirect:"+Global.getAdminPath()+"/collect/collect/?repage";
	}

}