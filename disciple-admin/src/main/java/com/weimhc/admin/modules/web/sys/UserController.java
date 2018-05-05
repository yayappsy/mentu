/**
 * 
 */
package com.weimhc.admin.modules.web.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.javamg.common.beanvalidator.BeanValidators;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.DateUtils;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.thinkgem.javamg.common.utils.excel.ExportExcel;
import com.thinkgem.javamg.common.utils.excel.ImportExcel;
import com.weimhc.admin.core.service.SystemService;
import com.weimhc.admin.core.utils.AdminUserUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.security.PasswordUtils;
import com.weimhc.framework.utils.AccessUtils;
import com.weimhc.framework.utils.UploadFolder;
import com.weimhc.modules.sys.entity.Role;
import com.weimhc.modules.sys.entity.User;
import com.weimhc.modules.sys.service.UserService;
import com.weimhc.modules.user.entity.IdentityType;
import com.weimhc.modules.user.entity.UserAuth;
import com.weimhc.modules.user.service.UserAuthService;
import com.weimhc.modules.user.utils.UserAuthUtils;

/**
 * 用户Controller
 * 
 * @author zsm
 * @version 2016-02-18
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class UserController extends AdminBaseController {

	@Autowired
	private SystemService systemService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserAuthService userAuthService;

	@ModelAttribute
	public User get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return systemService.getUser(id);
		} else {
			return new User();
		}
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = { "index" })
	public String index(User user, Model model) {
		model.addAttribute("uploadFolder", UploadFolder.avatar);
		return "modules/sys/userIndex";
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = { "list", "" })
	public String list(User user, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		user.setCurrentUser(AdminUserUtils.getUser());
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
		model.addAttribute("page", page);
		model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/sys/userList";
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "form")
	public String form(User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/sys/userForm";
	}

	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "save")
	public String save(User user, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "admin.alert.demoMode");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		if (!beanValidator(model, user)) {
			return form(user, model);
		}
		if (!"true".equals(checkUsername(user.getOldUsername(), user.getUsername()))) {
			addMessage(model, "保存用户'" + user.getUsername() + "'失败，登录名已存在");
			return form(user, model);
		}
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		for (Role r : systemService.findAllRole()) {
			if (roleIdList.contains(r.getId())) {
				roleList.add(r);
			}
		}
		user.setRegisterIp(AccessUtils.getIpAddress(request));
		user.setRoleList(roleList);
		// 保存用户信息
		systemService.saveUser(user);
		// 清除当前用户缓存
		if (user.getUsername().equals(AdminUserUtils.getUser().getUsername())) {
			AdminUserUtils.clearCache();
			// UserUtils.getCacheMap().clear();
		}
		addMessage(redirectAttributes, "保存用户'" + user.getUsername() + "'成功");
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "delete")
	public String delete(User user, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "admin.alert.demoMode");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		if (AdminUserUtils.getUser().getId().equals(user.getId())) {
			addMessage(redirectAttributes, "删除用户失败, 不允许删除当前用户");
		} else if (User.isAdmin(user.getId())) {
			addMessage(redirectAttributes, "删除用户失败, 不允许删除超级管理员用户");
		} else {
			systemService.deleteUserLoginAuthorization(user.getUserLoginAuthorization());
			addMessage(redirectAttributes, "删除用户成功");
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
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
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(User user, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "用户数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<User> page = systemService.findUser(new Page<User>(request, response, -1), user);
			new ExportExcel("用户数据", User.class).setDataList(page.getList())
					.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 导入用户数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "admin.alert.demoMode");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<User> list = ei.getDataList(User.class);
			for (User user : list) {
				try {
					if ("true".equals(checkUsername("", user.getUsername()))) {
						user.setNewPassword(PasswordUtils.entryptPassword("123456"));
						BeanValidators.validateWithException(validator, user);
						systemService.saveUser(user);
						successNum++;
					} else {
						failureMsg.append("<br/>登录名 " + user.getUsername() + " 已存在; ");
						failureNum++;
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>登录名 " + user.getUsername() + " 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex,
							": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg
							.append("<br/>登录名 " + user.getUsername() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条用户" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 下载导入用户数据模板
	 * 
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "用户数据导入模板.xlsx";
			List<User> list = Lists.newArrayList();
			list.add(AdminUserUtils.getUser());
			new ExportExcel("用户数据", User.class, 2).setDataList(list).write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 验证登录名是否有效
	 * 
	 * @param oldUsername
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "checkUsername")
	public String checkUsername(String oldUsername, String loginName) {
		if (loginName != null && loginName.equals(oldUsername)) {
			return "true";
		} else if (loginName != null && systemService.getUserByUsername(loginName) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 用户信息显示及保存
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "info")
	public String info(User user, HttpServletResponse response, Model model) {
		User currentUser = AdminUserUtils.getUser();
		if (StringUtils.isNotBlank(user.getName())) {
			if (Global.isDemoMode()) {
				model.addAttribute("message", "admin.alert.demoMode");
				return "modules/sys/userInfo";
			}
			currentUser.setEmail(user.getEmail());
			currentUser.setName(user.getName());
			currentUser.setPhone(user.getPhone());
			currentUser.setMobile(user.getMobile());
			currentUser.setRemarks(user.getRemarks());
			currentUser.setAvatar(user.getAvatar());
			currentUser.setNickname(user.getNickname());
			systemService.updateUserBaseInfo(currentUser.getLatestUserInfo());
			model.addAttribute("message", "保存用户信息成功");

			/*UserInfo userInfo = new UserInfo("1");
			userInfo.setEmail("1778078767@qq.com");
			userInfo.setMobile("123456");
			Map<String, Object> dataSource = Maps.newHashMap();
			dataSource.put("teacher", currentUser.getLatestUserInfo());
			dataSource.put("member", userInfo);
			RemindUtils.sendRemind(BusinessAction.makeAnAppointment,
					dataSource);*/
		}
		model.addAttribute("uploadFolder", UploadFolder.avatar);
		model.addAttribute("user", currentUser);
		model.addAttribute("Global", new Global());
		return "modules/sys/userInfo";
	}

	/**
	 * 返回用户信息
	 * 
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "infoData")
	public User infoData() {
		return AdminUserUtils.getUser();
	}

	/**
	 * 修改个人用户密码
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "modifyPwd")
	public String modifyPwd(String oldPassword, String newPassword, Model model) {
		User user = AdminUserUtils.getUser();
		if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)) {
			if (Global.isDemoMode()) {
				model.addAttribute("message", "admin.alert.demoMode");
				return "modules/sys/userModifyPwd";
			}
			UserAuth searchUserAuth = UserAuthUtils.get(user.getUsername(), IdentityType.username);
			if (PasswordUtils.validatePassword(oldPassword, searchUserAuth.getCredential())) {
				user.setNewPassword(newPassword);
				systemService.updatePassword(user.getLatestUserInfo(), IdentityType.username);
				model.addAttribute("message", "修改密码成功");
			} else {
				model.addAttribute("message", "修改密码失败，旧密码错误");
			}
		}
		model.addAttribute("user", user);
		return "modules/sys/userModifyPwd";
	}

	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			HttpServletRequest request, HttpServletResponse response) {
		String roleId = request.getParameter("roleId");
		String roleIds = request.getParameter("roleIds");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		User user = new User();
		if (StringUtils.isNotBlank(roleIds)) {
			user.getSqlMap().put("searchType", "roleIds");
			user.setRoleIdList(Lists.newArrayList(StringUtils.split(roleIds, ",")));
		} else if (StringUtils.isNotBlank(roleId)) {
			user.setRole(new Role(roleId));
		}
		List<User> list = userService.findAllUser(user);
		for (int i = 0; i < list.size(); i++) {
			User e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId()))) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
}
