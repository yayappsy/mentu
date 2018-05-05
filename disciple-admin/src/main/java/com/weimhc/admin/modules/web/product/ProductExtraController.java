/**
 * 
 */
package com.weimhc.admin.modules.web.product;

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
import com.weimhc.modules.product.entity.ProductExtra;
import com.weimhc.modules.product.service.ProductExtraService;

/**
 * 商品附加信息Controller
 * @author lc
 * @version 2017-06-07
 */
@Controller
@RequestMapping(value = "${adminPath}/product/productExtra")
public class ProductExtraController extends AdminBaseController {

	@Autowired
	private ProductExtraService productExtraService;
	
	@ModelAttribute
	public ProductExtra get(@RequestParam(required=false) String id) {
		ProductExtra entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = productExtraService.get(id);
		}
		if (entity == null){
			entity = new ProductExtra();
		}
		return entity;
	}
	
	@RequiresPermissions("product:productExtra:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProductExtra productExtra, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProductExtra> page = productExtraService.findPage(new Page<ProductExtra>(request, response), productExtra); 
		model.addAttribute("page", page);
		model.addAttribute("productExtra", productExtra);
		return "modules/product/productExtraList";
	}

	@RequiresPermissions("product:productExtra:view")
	@RequestMapping(value = "form")
	public String form(ProductExtra productExtra, Model model) {
		model.addAttribute("productExtra", productExtra);
		return "modules/product/productExtraForm";
	}

	@RequiresPermissions("product:productExtra:edit")
	@RequestMapping(value = "save")
	public String save(ProductExtra productExtra, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, productExtra)){
			return form(productExtra, model);
		}
		productExtraService.save(productExtra);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/product/productExtra/?repage";
	}
	
	@RequiresPermissions("product:productExtra:edit")
	@RequestMapping(value = "delete")
	public String delete(ProductExtra productExtra, RedirectAttributes redirectAttributes) {
		productExtraService.deleteEntity(productExtra);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/product/productExtra/?repage";
	}

	@RequiresPermissions("product:productExtra:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		productExtraService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}