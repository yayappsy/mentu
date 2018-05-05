/**
 * 
 */
package com.weimhc.admin.modules.web.member;

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
import com.weimhc.modules.member.entity.MemberRank;
import com.weimhc.modules.member.service.MemberRankService;
import com.weimhc.admin.core.web.AdminBaseController;

/**
 * 会员等级Controller
 * @author lc
 * @version 2016-11-30
 */
@Controller
@RequestMapping(value = "${adminPath}/member/memberRank")
public class MemberRankController extends AdminBaseController {

	@Autowired
	private MemberRankService memberRankService;
	
	@ModelAttribute
	public MemberRank get(@RequestParam(required=false) String id) {
		MemberRank entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memberRankService.get(id);
		}
		if (entity == null){
			entity = new MemberRank();
		}
		return entity;
	}
	
	@RequiresPermissions("member:memberRank:view")
	@RequestMapping(value = {"list", ""})
	public String list(MemberRank memberRank, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MemberRank> page = memberRankService.findPage(new Page<MemberRank>(request, response), memberRank); 
		model.addAttribute("page", page);
		return "modules/member/memberRankList";
	}

	@RequiresPermissions("member:memberRank:view")
	@RequestMapping(value = "form")
	public String form(MemberRank memberRank, Model model) {
		model.addAttribute("memberRank", memberRank);
		return "modules/member/memberRankForm";
	}

	@RequiresPermissions("member:memberRank:edit")
	@RequestMapping(value = "save")
	public String save(MemberRank memberRank, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, memberRank)){
			return form(memberRank, model);
		}
		memberRankService.save(memberRank);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/member/memberRank/?repage";
	}
	@RequiresPermissions("member:memberRank:edit")
	@RequestMapping(value = "delete")
	public String delete(MemberRank memberRank, RedirectAttributes redirectAttributes) {
		memberRankService.deleteEntity(memberRank);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/member/memberRank/?repage";
	}

	@RequiresPermissions("member:memberRank:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		memberRankService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}