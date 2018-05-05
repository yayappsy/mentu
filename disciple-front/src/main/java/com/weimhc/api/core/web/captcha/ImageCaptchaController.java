/**
 * 
 */
package com.weimhc.api.core.web.captcha;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.javamg.common.utils.JedisUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.framework.service.PictureCaptchaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 验证码信息Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api(value = "imageCaptcha")
@Controller("apiImageCaptchaController")
@RequestMapping(value = "${apiPath}/imageCaptcha")
public class ImageCaptchaController extends ApiBaseController {

	@Resource
	private PictureCaptchaService pictureCaptchaService;

	@ApiOperation(value = "生成图形验证码", notes = "生成图形验证码", tags = { "注册", "忘记密码" })
	@RequestMapping(value = "/getImgCaptcha", method = RequestMethod.GET)
	public void image(String captchaId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (StringUtils.isEmpty(captchaId)) {
			captchaId = request.getSession().getId();
		}
		String pragma = new StringBuffer().append("yB").append("-").append("der").append("ewoP")
				.reverse().toString();
		String value = new StringBuffer().append("ten").append(".").append("xxp").append("ohs")
				.reverse().toString();
		response.addHeader(pragma, value);
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		ServletOutputStream servletOutputStream = null;
		try {
			servletOutputStream = response.getOutputStream();
			BufferedImage bufferedImage = pictureCaptchaService.buildImage(captchaId);
			ImageIO.write(bufferedImage, "jpg", servletOutputStream);
			servletOutputStream.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(servletOutputStream);
		}
	}

}