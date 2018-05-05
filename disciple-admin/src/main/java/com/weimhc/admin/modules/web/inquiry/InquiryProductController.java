/**
 * 
 */
package com.weimhc.admin.modules.web.inquiry;

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


import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;

import com.weimhc.framework.web.ResultMessage;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.modules.inquiry.entity.InquiryProduct;
import com.weimhc.modules.inquiry.service.InquiryProductService;

/**
 * 询价产品Controller
 * @author zsm
 * @version 2017-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/inquiry/inquiryProduct")
public class InquiryProductController extends AdminBaseController {

	@Autowired
	private InquiryProductService inquiryProductService;
	
	@ModelAttribute
	public InquiryProduct get(@RequestParam(required=false) String id) {
		InquiryProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = inquiryProductService.get(id);
		}
		if (entity == null){
			entity = new InquiryProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("inquiry:inquiryProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(InquiryProduct inquiryProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<InquiryProduct> page = inquiryProductService.findPage(new Page<InquiryProduct>(request, response), inquiryProduct); 
		model.addAttribute("page", page);
		model.addAttribute("inquiryProduct", inquiryProduct);
		return "modules/inquiry/inquiryProductList";
	}

	@RequiresPermissions("inquiry:inquiryProduct:view")
	@RequestMapping(value = "form")
	public String form(InquiryProduct inquiryProduct, Model model) {
		model.addAttribute("inquiryProduct", inquiryProduct);
		return "modules/inquiry/inquiryProductForm";
	}

	@RequiresPermissions("inquiry:inquiryProduct:edit")
	@RequestMapping(value = "save")
	public String save(InquiryProduct inquiryProduct, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, inquiryProduct)){
			return form(inquiryProduct, model);
		}
		inquiryProductService.save(inquiryProduct);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/inquiry/inquiryProduct/?repage";
	}
	
	@RequiresPermissions("inquiry:inquiryProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(InquiryProduct inquiryProduct, RedirectAttributes redirectAttributes) {
		inquiryProductService.deleteEntity(inquiryProduct);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/inquiry/inquiryProduct/?repage";
	}

	@RequiresPermissions("inquiry:inquiryProduct:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		inquiryProductService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}