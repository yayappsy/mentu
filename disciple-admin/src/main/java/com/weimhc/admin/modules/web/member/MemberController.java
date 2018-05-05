/**
 * 
 */
package com.weimhc.admin.modules.web.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.beanvalidator.BeanValidators;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.DateUtils;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.thinkgem.javamg.common.utils.excel.ExportExcel;
import com.thinkgem.javamg.common.utils.excel.ImportExcel;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.security.PasswordUtils;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.base.utils.BaseIndustryUtils;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.member.service.MemberService;
import com.weimhc.modules.user.service.UserInfoCommonService;

/**
 * 会员Controller
 * 
 * @author lc
 * @version 2016-11-30
 */
@Controller
@RequestMapping(value = "${adminPath}/member/member")
public class MemberController extends AdminBaseController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private UserInfoCommonService userInfoCommonService;

	@ModelAttribute
	public Member get(@RequestParam(required = false) String id) {
		Member entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = memberService.get(id);
		}
		if (entity == null) {
			entity = new Member();
		}
		return entity;
	}

	@RequiresPermissions("member:member:view")
	@RequestMapping(value = { "list", "" })
	public String list(Member member, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<Member> page = memberService.findPage(new Page<Member>(request, response), member);
		model.addAttribute("page", page);
		if (dealWithSearchType(request, model)) {
			return "modules/member/memberSearchList";
		}
		return "modules/member/memberList";
	}

	@RequiresPermissions("member:member:view")
	@RequestMapping(value = "form")
	public String form(Member member, Model model) {
		model.addAttribute("member", member);
		model.addAttribute("industryList", BaseIndustryUtils.findAll());
		return "modules/member/memberForm";
	}

	@RequiresPermissions("member:member:edit")
	@RequestMapping(value = "save")
	public String save(Member member, HttpServletRequest request, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, member)) {
			return form(member, model);
		}
		userInfoCommonService.updateUserBaseInfo(member.getLatestUserInfo());
		// 如果新密码为空，则不更换密码
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/member/member/?repage";
	}

	@RequiresPermissions("member:member:edit")
	@RequestMapping(value = "delete")
	public String delete(Member member, RedirectAttributes redirectAttributes) {
		userInfoCommonService.deleteUserLoginAuthorization(member.getLatestUserInfo());
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/member/member/?repage";
	}

	@RequiresPermissions("member:member:edit")
	@RequestMapping(value = "createImUser")
	public String createImUser(Member member, RedirectAttributes redirectAttributes) {
		userInfoCommonService.createImUser(member.getLatestUserInfo());
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/member/member/?repage";
	}

	@RequiresPermissions("member:member:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		memberService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 导出用户数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("member:member:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(Member member, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "用户数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<Member> page = memberService.findPage(new Page<Member>(request, response, -1),
					member);
			new ExportExcel("用户数据", Member.class).setDataList(page.getList())
					.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/member/member/list?repage";
	}

	/**
	 * 验证登录名是否有效
	 * 
	 * @param oldUsername
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("member:member:edit")
	@RequestMapping(value = "checkUsername")
	private boolean checkUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			return false;
		}
		if (memberService.usernameDisabled(username) || memberService.usernameExists(username)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 导入用户数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("member:member:edit")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "admin.alert.demoMode");
			return "redirect:" + adminPath + "/member/member/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Member> list = ei.getDataList(Member.class);
			for (Member member : list) {
				try {
					if (checkUsername(member.getUsername()) && !memberService.snExists(member)) {
						member.setNewPassword(PasswordUtils.entryptPassword("123456"));
						BeanValidators.validateWithException(validator, member);
						memberService.saveMember(member);
						successNum++;
					} else {
						failureMsg.append("<br/>登录名或会员编号 已存在; ");
						failureNum++;
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>登录名 " + member.getUsername() + " 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex,
							": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append(
							"<br/>登录名 " + member.getUsername() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条用户" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/member/member/list?repage";
	}

	/**
	 * 下载导入用户数据模板
	 * 
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("member:member:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "用户数据导入模板.xlsx";
			List<Member> list = Lists.newArrayList();
			list.add(memberService.get("59c5d307f25347abadb0a6534376458d"));
			new ExportExcel("用户数据", Member.class, 2).setDataList(list).write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/member/member/list?repage";
	}
}