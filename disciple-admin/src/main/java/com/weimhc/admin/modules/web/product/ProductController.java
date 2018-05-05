/**
 * 
 */
package com.weimhc.admin.modules.web.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
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

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.base.utils.RegionUtils;
import com.weimhc.modules.product.entity.Product;
import com.weimhc.modules.product.entity.ProductExtra;
import com.weimhc.modules.product.service.ProductExtraService;
import com.weimhc.modules.product.service.ProductService;
import com.weimhc.modules.property.utils.PropertyTemplateUtils;

/**
 * 商品Controller
 * 
 * @author lc
 * @version 2017-01-03
 */
@Controller
@RequestMapping(value = "${adminPath}/product/product")
public class ProductController extends AdminBaseController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductExtraService productExtraService;

	@ModelAttribute
	public Product get(@RequestParam(required = false) String id) {
		Product entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = productService.get(id);
		}
		if (entity == null) {
			entity = new Product();
		}
		return entity;
	}

	@RequiresPermissions("product:product:view")
	@RequestMapping(value = { "list", "" })
	public String list(Product product, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Product> page = productService
				.findPage(new Page<Product>(request, response), product);
		model.addAttribute("page", page);
		model.addAttribute("product", product);
		if (dealWithSearchType(request, model)) {
			return "modules/product/productSearchList";
		}
		return "modules/product/productList";
	}

	@RequiresPermissions("product:product:view")
	@RequestMapping(value = "form")
	public String form(Product product, Model model) {
		if (product != null && StringUtils.isNotBlank(product.getId())) {
			ProductExtra productExtra = new ProductExtra();
			productExtra.setProduct(product);
			productExtra = productExtraService.getEntity(productExtra);
			if (productExtra != null) {
				BeanUtils.copyProperties(productExtra, product);
			}
			product.setProductExtra(productExtra);
		}
		product.setArea(RegionUtils.getRegion(product.getArea()));
		model.addAttribute("product", product);
		model.addAttribute("propertyTemplate",
				PropertyTemplateUtils.findById("1"));
		return "modules/product/productForm";
	}

	@RequiresPermissions("product:product:edit")
	@RequestMapping(value = "save")
	public String save(Product product, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		/*if (!isValid(result, product)) {
			return form(product, model);
		}*/
		productService.save(product);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/product/product/?repage";
	}

	@RequiresPermissions("product:product:edit")
	@RequestMapping(value = "delete")
	public String delete(Product product,
			RedirectAttributes redirectAttributes) {
		productService.deleteEntity(product);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/product/product/?repage";
	}

	@RequiresPermissions("product:product:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		productService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}