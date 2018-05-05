/*
 * 
 * 
 * 
 */
package com.weimhc.front.core.web;

import java.awt.image.BufferedImage;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.service.PictureCaptchaService;
import com.weimhc.framework.service.RSAService;
import com.weimhc.modules.base.utils.setting.SiteSettingUtils;
import com.weimhc.modules.sys.service.AreaService;

/**
 * Controller - 共用
 * 
 * 
 * 
 */
@Controller("shopCommonController")
@RequestMapping("${frontPath}/common")
public class CommonController extends FrontBaseController {

	@Resource
	private RSAService rsaService;
	@Resource
	private AreaService areaService;
	@Resource
	private PictureCaptchaService pictureCaptchaService;

	/**
	 * 网站关闭
	 */
	@RequestMapping("/site_close")
	public String siteClose() {
		if (SiteSettingUtils.getIsSiteEnabled()) {
			return "redirect:/";
		} else {
			return "/shop/common/site_close";
		}
	}

	/**
	 * 公钥
	 */
	@RequestMapping(value = "/public_key", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> publicKey(
			HttpServletRequest request) {
		RSAPublicKey publicKey = rsaService.generateKey(request);
		Map<String, String> data = new HashMap<String, String>();
		data.put("modulus", Base64
				.encodeBase64String(publicKey.getModulus().toByteArray()));
		data.put("exponent", Base64.encodeBase64String(
				publicKey.getPublicExponent().toByteArray()));
		return data;
	}

	/**
	 * 验证码
	 */
	@RequestMapping(value = "/getImgCaptcha", method = RequestMethod.GET)
	public void image(String captchaId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (StringUtils.isEmpty(captchaId)) {
			captchaId = request.getSession().getId();
		}
		String pragma = new StringBuffer().append("yB").append("-")
				.append("der").append("ewoP").reverse().toString();
		String value = new StringBuffer().append("ten").append(".")
				.append("xxp").append("ohs").reverse().toString();
		response.addHeader(pragma, value);
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		ServletOutputStream servletOutputStream = null;
		try {
			servletOutputStream = response.getOutputStream();
			BufferedImage bufferedImage = pictureCaptchaService
					.buildImage(captchaId);
			ImageIO.write(bufferedImage, "jpg", servletOutputStream);
			servletOutputStream.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(servletOutputStream);
		}
	}

	/**
	 * 错误提示
	 */
	@RequestMapping("/error")
	public String error(HttpServletRequest request, Model model) {
		return ERROR_VIEW;
	}

	/**
	 * 资源不存在
	 */
	@RequestMapping("/resourceNotFound")
	public String resourceNotFound(HttpServletRequest request, Model model) {
		String type = request.getParameter("type");
		model.addAttribute("type", type);
		return RESOURCE_NOT_FOUND_VIEW;
	}

}