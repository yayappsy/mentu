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
import com.weimhc.modules.product.entity.ProductInstalment;
import com.weimhc.modules.product.service.ProductInstalmentService;

/**
 * 商品分期信息Controller
 * @author lc
 * @version 2017-06-07
 */
@Controller
@RequestMapping(value = "${adminPath}/product/productInstalment")
public class ProductInstalmentController extends AdminBaseController {

	@Autowired
	private ProductInstalmentService productInstalmentService;
	
	@ModelAttribute
	public ProductInstalment get(@RequestParam(required=false) String id) {
		ProductInstalment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = productInstalmentService.get(id);
		}
		if (entity == null){
			entity = new ProductInstalment();
		}
		return entity;
	}
	
	@RequiresPermissions("product:productInstalment:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProductInstalment productInstalment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProductInstalment> page = productInstalmentService.findPage(new Page<ProductInstalment>(request, response), productInstalment); 
		model.addAttribute("page", page);
		model.addAttribute("productInstalment", productInstalment);
		return "modules/product/productInstalmentList";
	}

	@RequiresPermissions("product:productInstalment:view")
	@RequestMapping(value = "form")
	public String form(ProductInstalment productInstalment, Model model) {
		model.addAttribute("productInstalment", productInstalment);
		return "modules/product/productInstalmentForm";
	}

	@RequiresPermissions("product:productInstalment:edit")
	@RequestMapping(value = "save")
	public String save(ProductInstalment productInstalment, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, productInstalment)){
			return form(productInstalment, model);
		}
		productInstalmentService.save(productInstalment);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/product/productInstalment/?repage";
	}
	
	@RequiresPermissions("product:productInstalment:edit")
	@RequestMapping(value = "delete")
	public String delete(ProductInstalment productInstalment, RedirectAttributes redirectAttributes) {
		productInstalmentService.deleteEntity(productInstalment);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/product/productInstalment/?repage";
	}

	@RequiresPermissions("product:productInstalment:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		productInstalmentService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}