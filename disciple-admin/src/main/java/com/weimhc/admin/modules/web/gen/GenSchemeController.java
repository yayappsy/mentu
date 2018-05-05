/**
 * 
 */
package com.weimhc.admin.modules.web.gen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.thinkgem.javamg.common.web.BaseController;
import com.weimhc.modules.gen.entity.GenScheme;
import com.weimhc.modules.gen.service.GenSchemeService;
import com.weimhc.modules.gen.service.GenTableService;
import com.weimhc.modules.gen.util.GenUtils;

/**
 * 生成方案Controller
 * 
 * @version 2013-10-15
 */
@Controller
@RequestMapping(value = "${adminPath}/gen/genScheme")
public class GenSchemeController extends BaseController {

	@Autowired
	private GenSchemeService genSchemeService;

	@Autowired
	private GenTableService genTableService;

	@ModelAttribute
	public GenScheme get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return genSchemeService.get(id);
		} else {
			return new GenScheme();
		}
	}

	@RequiresPermissions("gen:genScheme:view")
	@RequestMapping(value = { "list", "" })
	public String list(GenScheme genScheme, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<GenScheme> page = genSchemeService
				.find(new Page<GenScheme>(request, response), genScheme);
		model.addAttribute("page", page);

		return "modules/gen/genSchemeList";
	}

	@RequiresPermissions("gen:genScheme:view")
	@RequestMapping(value = "form")
	public String form(GenScheme genScheme, Model model) {
		if (StringUtils.isBlank(genScheme.getPackageName())) {
			genScheme.setPackageName("com.weimhc.modules");
		}
		// if (StringUtils.isBlank(genScheme.getFunctionAuthor())){
		// genScheme.setFunctionAuthor(AdminUserUtils.getUser().getName());
		// }
		model.addAttribute("genScheme", genScheme);
		model.addAttribute("config", GenUtils.getConfig());
		model.addAttribute("tableList", genTableService.findAll());
		return "modules/gen/genSchemeForm";
	}

	@RequiresPermissions("gen:genScheme:edit")
	@RequestMapping(value = "save")
	public String save(GenScheme genScheme, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, genScheme)) {
			return form(genScheme, model);
		}

		String result = genSchemeService.save(genScheme);
		addMessage(redirectAttributes,
				"操作生成方案'" + genScheme.getName() + "'成功<br/>" + result);
		return "redirect:" + adminPath + "/gen/genScheme/?repage";
	}

	@RequiresPermissions("gen:genScheme:edit")
	@RequestMapping(value = "delete")
	public String delete(GenScheme genScheme,
			RedirectAttributes redirectAttributes) {
		genSchemeService.delete(genScheme);
		addMessage(redirectAttributes, "删除生成方案成功");
		return "redirect:" + adminPath + "/gen/genScheme/?repage";
	}

}
