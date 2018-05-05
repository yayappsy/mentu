/**
 * 
 */
package com.weimhc.admin.modules.web.image;

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
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.base.entity.PathType;
import com.weimhc.modules.image.entity.Image;
import com.weimhc.modules.image.service.ImageService;

/**
 * 图片Controller
 * 
 * @author zsm
 * @version 2017-05-17
 */
@Controller
@RequestMapping(value = "${adminPath}/image/image")
public class ImageController extends AdminBaseController {

	@Autowired
	private ImageService imageService;

	@ModelAttribute
	public Image get(@RequestParam(required = false) String id) {
		Image entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = imageService.get(id);
		}
		if (entity == null) {
			entity = new Image();
		}
		return entity;
	}

	@RequiresPermissions("image:image:view")
	@RequestMapping(value = { "manager" })
	public String manager(Image image, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<Image> page = imageService.findPage(new Page<Image>(request, response), image);
		model.addAttribute("page", page);
		model.addAttribute("image", image);
		return "modules/image/imageManager";
	}

	@RequiresPermissions("image:image:view")
	@RequestMapping(value = { "list", "" })
	public String list(Image image, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<Image> page = imageService.findPage(new Page<Image>(request, response), image);
		model.addAttribute("page", page);
		model.addAttribute("image", image);
		dealWithSearchType(request, model);
		return "modules/image/imageList";
	}

	@RequiresPermissions("image:image:view")
	@RequestMapping(value = "form")
	public String form(Image image, Model model, HttpServletRequest request) {
		if (null == image.getPathType()) {
			image.setPathType(PathType.local);
		}
		model.addAttribute("image", image);
		model.addAttribute("pathTypes", PathType.values());
		dealWithSearchType(request, model);
		return "modules/image/imageEdit";
	}

	@RequiresPermissions("image:image:edit")
	@RequestMapping(value = "save")
	public String save(Image image, BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (StringUtils.isBlank(image.getExtension())) {
			image.setExtension(".jpeg");
		}
		if (StringUtils.isBlank(image.getWidth())) {
			image.setWidth("100");
		}
		if (StringUtils.isBlank(image.getHeight())) {
			image.setHeight("100");
		}
		if (!isValid(result, image)) {
			return form(image, model, request);
		}
		if (PathType.local.equals(image.getPathType())) {
			image.setUrl(image.getStoragePath());
		} else {
			image.setStoragePath(image.getUrl());
		}
		imageService.save(image);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/image/image/?repage=&searchType="
				+ request.getParameter("searchType");
	}

	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("image:image:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts, RedirectAttributes redirectAttributes) {
		List<Image> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			Image entity = new Image(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		imageService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/image/image/?repage";
	}

	@RequiresPermissions("image:image:edit")
	@RequestMapping(value = "delete")
	public String delete(Image image, RedirectAttributes redirectAttributes) {
		imageService.deleteEntity(image);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/image/image/?repage";
	}

	@RequiresPermissions("image:image:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		imageService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}