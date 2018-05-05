/**
 * 
 */
package com.weimhc.admin.modules.web.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.modules.sys.entity.UserLog;
import com.weimhc.modules.sys.service.UserLogService;

/**
 * 日志Controller
 * 
 * @version 2013-6-2
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/log")
public class UserLogController extends AdminBaseController {

	@Autowired
	private UserLogService logService;

	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = { "list", "" })
	public String list(UserLog log, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<UserLog> page = logService
				.findPage(new Page<UserLog>(request, response), log);
		model.addAttribute("page", page);
		return "modules/sys/logList";
	}

}
