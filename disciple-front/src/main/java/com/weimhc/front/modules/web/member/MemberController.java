/**
 * 
 */
package com.weimhc.front.modules.web.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weimhc.front.core.web.FrontBaseController;

/**
 * 会员中心Controller
 * 
 * @author zsm
 * @version 2016-01-12
 */
@Controller
@RequestMapping(value = "${frontPath}/member")
public class MemberController extends FrontBaseController {

	/** 最新订单数 */
	private static final int NEW_ORDER_COUNT = 6;

	/**
	 * 首页
	 */
	@RequestMapping(value = { "/index", "" }, method = RequestMethod.GET)
	public String index(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		return "/modules/member/index";
	}

}