/**
 * 
 */
package com.weimhc.admin.modules.web.product;

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
import com.weimhc.modules.product.entity.ProductBrand;
import com.weimhc.modules.product.entity.ProductCategory;
import com.weimhc.modules.product.service.ProductBrandService;

/**
 * 品牌系列Controller
 * 
 * @author zsm
 * @version 2016-02-01
 */
@Controller
@RequestMapping(value = "${adminPath}/product/productBrand")
public class ProductBrandController extends AdminBaseController {

	@Autowired
	private ProductBrandService productBrandService;

	@ModelAttribute
	public ProductBrand get(@RequestParam(required = false) String id) {
		ProductBrand entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = productBrandService.get(id);
		}
		if (entity == null) {
			entity = new ProductBrand();
		}
		return entity;
	}

	@RequiresPermissions("product:productBrand:view")
	@RequestMapping(value = { "list", "" })
	public String list(ProductBrand productBrand, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<ProductBrand> page = productBrandService.findPage(
				new Page<ProductBrand>(request, response), productBrand);
		model.addAttribute("page", page);
		return "modules/product/productBrandList";
	}

	@RequiresPermissions("product:productBrand:view")
	@RequestMapping(value = "form")
	public String form(ProductBrand productBrand, Model model) {
		model.addAttribute("productBrand", productBrand);
		return "modules/product/productBrandForm";
	}

	@RequiresPermissions("product:productBrand:edit")
	@RequestMapping(value = "save")
	public String save(ProductBrand productBrand, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, productBrand)) {
			return form(productBrand, model);
		}
		productBrandService.save(productBrand);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/product/productBrand/?repage";
	}

	@RequiresPermissions("product:productBrand:edit")
	@RequestMapping(value = "delete")
	public String delete(ProductBrand productBrand,
			RedirectAttributes redirectAttributes) {
		productBrandService.deleteEntity(productBrand);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/product/productBrand/?repage";
	}

	@RequiresPermissions("ad:AdClick:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		productBrandService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(
			@RequestParam(required = false) String extId,
			HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		ProductBrand productBrand = new ProductBrand();
		String productCategoryId = request.getParameter("productCategoryId");
		if (StringUtils.isNotBlank(productCategoryId)) {
			productBrand
					.setProductCategory(new ProductCategory(productCategoryId));
		}
		List<ProductBrand> list = productBrandService
				.findAllList(new ProductBrand());
		for (int i = 0; i < list.size(); i++) {
			ProductBrand e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", '0');
			map.put("name", e.getName());
			mapList.add(map);
		}
		return mapList;
	}
}