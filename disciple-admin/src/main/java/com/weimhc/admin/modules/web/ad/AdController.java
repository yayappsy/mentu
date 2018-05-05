/**
 * 
 */
package com.weimhc.admin.modules.web.ad;

import java.util.Date;
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
import com.weimhc.framework.utils.UploadFolder;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.ad.entity.Ad;
import com.weimhc.modules.ad.entity.AdPosition;
import com.weimhc.modules.ad.entity.AdType;
import com.weimhc.modules.ad.service.AdService;
import com.weimhc.modules.ad.utils.AdUtils;

/**
 * 广告基本信息Controller
 * 
 * @author zsm
 * @version 2017-04-01
 */
@Controller
@RequestMapping(value = "${adminPath}/ad/ad")
public class AdController extends AdminBaseController {

	@Autowired
	private AdService adService;

	@ModelAttribute
	public Ad get(@RequestParam(required = false) String id) {
		Ad entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = adService.get(id);
		}
		if (entity == null) {
			entity = new Ad();
		}
		return entity;
	}

	@RequiresPermissions("ad:ad:view")
	@RequestMapping(value = { "list", "" })
	public String list(Ad ad, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		AdPosition adPosition = retrieveAdPosition(ad, request);
		ad.setAdPosition(adPosition);

		Page<Ad> page = adService.findPage(new Page<Ad>(request, response), ad);

		model.addAttribute("page", page);
		model.addAttribute("ad", ad);
		model.addAttribute("adType", ad.getAdType());
		if (retrievedispalyMoreAdPosition(request)) {
		model.addAttribute("adPositionList",
					AdUtils.findAdPositionListByTopAdType(
							retrieveAdType(ad, request)));
	}
		model.addAttribute("dispalyMoreAdPosition",
				retrievedispalyMoreAdPosition(request));

		return "modules/ad/adListNormal";
	}

	private AdPosition retrieveAdPosition(Ad ad, HttpServletRequest request) {

		AdPosition adPosition = null;
		if (ad.getAdPosition() == null) {
			String adPositionId = request.getParameter("adPositionId");
			if (StringUtils.isNotBlank(adPositionId)) {
				adPosition = AdUtils.findAdPositionById(adPositionId);
			} else {
				adPosition = AdUtils.findAdPositionListByTopAdType(
						retrieveAdType(ad, request)).get(0);
			}
		} else {
			adPosition = AdUtils.findAdPositionById(ad.getAdPosition().getId());
		}
		return adPosition;

	}

	private AdType retrieveAdType(Ad ad, HttpServletRequest request) {
		if (ad.getAdType() == null) {
			String adType = request.getParameter("adType");
			if (StringUtils.isBlank(adType)) {
				adType = "normal";
		}
			return AdType.valueOf(adType);
		}
		return ad.getAdType();

	}

	private boolean retrievedispalyMoreAdPosition(HttpServletRequest request) {
		String displayMoreAdPosition = request
				.getParameter("displayMoreAdPosition");
		if (StringUtils.equalsIgnoreCase(Global.FALSE, displayMoreAdPosition)) {
			return false;
		}
		return true;

	}

	@RequiresPermissions("ad:ad:view")
	@RequestMapping(value = "form")
	public String form(Ad ad, Model model, HttpServletRequest request) {

		AdPosition adPosition = retrieveAdPosition(ad, request);
		ad.setAdPosition(adPosition);
		setDefaultValue(ad);
		model.addAttribute("ad", ad);
		model.addAttribute("dispalyMoreAdPosition",
				retrievedispalyMoreAdPosition(request));
		return "modules/ad/adFormNormal";
	}

	/**
	 * 设置广告添加的默认值
	 * 
	 * @param ad
	 */
	private void setDefaultValue(Ad ad) {
		if (ad.getIsForever() == null) {
			ad.setIsForever(Boolean.TRUE);
		}
		if (ad.getIsAutoplay() == null) {
			ad.setIsAutoplay(Boolean.FALSE);
		}
		if (ad.getBeginDate() == null) {
			ad.setBeginDate(new Date());
		}
	}

	@RequiresPermissions("ad:adDetail:view")
	@RequestMapping(value = "detailForm")
	public String detailForm(Ad ad, Model model, HttpServletRequest request) {
		AdPosition adPosition = AdUtils.findAdPosition(ad.getAdPosition());
		ad.setAdPosition(adPosition);
		model.addAttribute("uploadFolder", UploadFolder.ad);
		model.addAttribute("ad", ad);
		if (adPosition != null) {
			if (AdType.normal.equals(adPosition.getAdType().getGroup())) {
				return "modules/ad/details/" + AdType.normal + "/"
						+ adPosition.getAdType() + "Form";
			} else {
				return "modules/ad/details/" + AdType.slider + "/sliderForm";
			}
		}
		return "modules/ad/details/informationForm";
	}

	@RequiresPermissions("ad:ad:edit")
	@RequestMapping(value = "save")
	public String save(Ad ad, BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!isValid(result, ad)) {
			return form(ad, model, request);
		}
		adService.save(ad);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		model.addAttribute("dispalyMoreAdPosition",
				retrievedispalyMoreAdPosition(request));
		return "redirect:" + Global.getAdminPath() + "/ad/ad/?repage";
	}

	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("ad:ad:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<Ad> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			Ad entity = new Ad(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		adService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/ad/ad/?repage";
	}

	@RequiresPermissions("ad:ad:edit")
	@RequestMapping(value = "delete")
	public String delete(Ad ad, RedirectAttributes redirectAttributes) {
		adService.deleteEntity(ad);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/ad/ad/?repage";
	}

	@RequiresPermissions("ad:ad:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		adService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}