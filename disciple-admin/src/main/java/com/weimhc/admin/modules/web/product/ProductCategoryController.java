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
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.product.entity.ProductCategory;
import com.weimhc.modules.product.service.ProductCategoryService;

/**
 * 商品分类Controller
 * 
 * @author lc
 * @version 2017-01-03
 */
@Controller
@RequestMapping(value = "${adminPath}/product/productCategory")
public class ProductCategoryController extends AdminBaseController {

	@Autowired
	private ProductCategoryService productCategoryService;

	@ModelAttribute
	public ProductCategory get(@RequestParam(required = false) String id) {
		ProductCategory entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = productCategoryService.get(id);
		}
		if (entity == null) {
			entity = new ProductCategory();
		}
		return entity;
	}

	@RequiresPermissions("product:productCategory:view")
	@RequestMapping(value = { "list", "" })
	public String list(ProductCategory productCategory, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<ProductCategory> list = productCategoryService.findList(productCategory);
		model.addAttribute("list", list);
		model.addAttribute("productCategory", productCategory);
		return "modules/product/productCategoryList";
	}

	@RequiresPermissions("product:productCategory:view")
	@RequestMapping(value = "form")
	public String form(ProductCategory productCategory, Model model) {
		if (productCategory.getParent() != null && StringUtils.isNotBlank(productCategory.getParent().getId())) {
			productCategory.setParent(productCategoryService.get(productCategory.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(productCategory.getId())) {
				ProductCategory productCategoryChild = new ProductCategory();
				productCategoryChild.setParent(new ProductCategory(productCategory.getParent().getId()));
				List<ProductCategory> list = productCategoryService.findList(productCategory);
				if (list.size() > 0) {
					productCategory.setSort(list.get(list.size() - 1).getSort());
					if (productCategory.getSort() != null) {
						productCategory.setSort(productCategory.getSort() + 30);
					}
				}
			}
		}
		if (productCategory.getSort() == null) {
			productCategory.setSort(30);
		}
		model.addAttribute("productCategory", productCategory);
		return "modules/product/productCategoryForm";
	}

	@RequiresPermissions("product:productCategory:edit")
	@RequestMapping(value = "save")
	public String save(ProductCategory productCategory, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, productCategory)) {
			return form(productCategory, model);
		}
		productCategoryService.save(productCategory);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/product/productCategory/?repage";
	}

	@RequiresPermissions("product:productCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(ProductCategory productCategory, RedirectAttributes redirectAttributes) {
		productCategoryService.deleteEntity(productCategory);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/product/productCategory/?repage";
	}

	@RequiresPermissions("product:productCategory:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids, RedirectAttributes redirectAttributes) {
		productCategoryService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<ProductCategory> list = productCategoryService.findList(new ProductCategory());
		for (int i = 0; i < list.size(); i++) {
			ProductCategory e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId())
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