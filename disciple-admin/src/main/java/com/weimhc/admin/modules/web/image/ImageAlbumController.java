/**
 * 
 */
package com.weimhc.admin.modules.web.image;

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

import com.weimhc.framework.web.ResultMessage;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.modules.image.entity.ImageAlbum;
import com.weimhc.modules.image.service.ImageAlbumService;

/**
 * 相册Controller
 * @author zsm
 * @version 2017-05-17
 */
@Controller
@RequestMapping(value = "${adminPath}/image/imageAlbum")
public class ImageAlbumController extends AdminBaseController {

	@Autowired
	private ImageAlbumService imageAlbumService;
	
	@ModelAttribute
	public ImageAlbum get(@RequestParam(required=false) String id) {
		ImageAlbum entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = imageAlbumService.get(id);
		}
		if (entity == null){
			entity = new ImageAlbum();
		}
		return entity;
	}
	
	@RequiresPermissions("image:imageAlbum:view")
	@RequestMapping(value = {"list", ""})
	public String list(ImageAlbum imageAlbum, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ImageAlbum> list = imageAlbumService.findList(imageAlbum); 
		model.addAttribute("list", list);
		model.addAttribute("imageAlbum", imageAlbum);
		return "modules/image/imageAlbumList";
	}

	@RequiresPermissions("image:imageAlbum:view")
	@RequestMapping(value = "form")
	public String form(ImageAlbum imageAlbum, Model model) {
		if (imageAlbum.getParent()!=null && StringUtils.isNotBlank(imageAlbum.getParent().getId())){
			imageAlbum.setParent(imageAlbumService.get(imageAlbum.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(imageAlbum.getId())){
				ImageAlbum imageAlbumChild = new ImageAlbum();
				imageAlbumChild.setParent(new ImageAlbum(imageAlbum.getParent().getId()));
				List<ImageAlbum> list = imageAlbumService.findList(imageAlbum); 
				if (list.size() > 0){
					imageAlbum.setSort(list.get(list.size()-1).getSort());
					if (imageAlbum.getSort() != null){
						imageAlbum.setSort(imageAlbum.getSort() + 30);
					}
				}
			}
		}
		if (imageAlbum.getSort() == null){
			imageAlbum.setSort(30);
		}
		model.addAttribute("imageAlbum", imageAlbum);
		return "modules/image/imageAlbumForm";
	}

	@RequiresPermissions("image:imageAlbum:edit")
	@RequestMapping(value = "save")
	public String save(ImageAlbum imageAlbum, BindingResult result, 
	    Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, imageAlbum)){
			return form(imageAlbum, model);
		}
		imageAlbumService.save(imageAlbum);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/image/imageAlbum/?repage";
	}
	
	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("image:imageAlbum:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<ImageAlbum> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			ImageAlbum entity = new ImageAlbum(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		imageAlbumService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/image/imageAlbum/?repage";
	}
	
	@RequiresPermissions("image:imageAlbum:edit")
	@RequestMapping(value = "delete")
	public String delete(ImageAlbum imageAlbum, RedirectAttributes redirectAttributes) {
		imageAlbumService.deleteEntity(imageAlbum);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/image/imageAlbum/?repage";
	}
	
	@RequiresPermissions("image:imageAlbum:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		imageAlbumService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<ImageAlbum> list = imageAlbumService.findList(new ImageAlbum());
		for (int i=0; i<list.size(); i++){
			ImageAlbum e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
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