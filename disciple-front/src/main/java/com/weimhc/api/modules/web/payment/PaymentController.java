/**
 * 
 */
package com.weimhc.api.modules.web.payment;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.thinkgem.javamg.common.config.Global;
import com.weimhc.framework.service.PaymentService;
import com.weimhc.framework.utils.AlipayUtil;
import com.weimhc.framework.utils.XMLUtil;
import com.weimhc.front.core.web.FrontBaseController;
import com.weimhc.modules.order.service.OrderInstalmentService;
import com.weimhc.modules.order.service.OrderService;
import com.weimhc.modules.payment.entity.PaymentTurnover;
import com.weimhc.modules.payment.entity.TradeType;
import com.weimhc.modules.payment.service.PaymentTurnoverService;
import com.weimhc.modules.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付Controller
 * 
 * @author zsm
 * @version 2017-06-22
 */
@ApiIgnore
@Controller
@RequestMapping(value = "${apiPath}/payment")
public class PaymentController extends FrontBaseController {

	@Resource(name = "wxPayService")
	private WxPayService wxPayService;

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private PaymentTurnoverService paymentTurnoverService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private OrderInstalmentService orderInstalmentService;

	public static String PC_URL = Global.getConfig("alipay.returnPcUrl");
	public static String WEBAPP_URL = Global.getConfig("alipay.returnApiUrl");

	/**
	 * 订单支付支付宝服务器异步通知
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/pay/alipay/notify/{paymentSn}", method = RequestMethod.POST)
	@ApiIgnore
	public void orderPayAlipayNotify(@PathVariable(value = "paymentSn") String paymentSn,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = iter.next();
			String[] values = requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);

		}
		// 乱码解决，这段代码在出现乱码时使用
		Boolean signVerified;
		try {
			// 校验签名是否正确
			signVerified = AlipaySignature.rsaCheckV1(params, AlipayUtil.ALIPAY_PUBLIC_KEY,
					AlipayConstants.CHARSET_UTF8, "RSA2");
			if (signVerified) {// 验证成功
				// 商户订单号
				String out_trade_no = new String(
						request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

				// 支付宝交易号
				String trade_no = new String(
						request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

				// 交易状态
				String trade_status = new String(
						request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
				if (trade_status.equals("TRADE_FINISHED")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序

					// 注意：
					// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				} else if (trade_status.equals("TRADE_SUCCESS")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					PaymentTurnover paymentTurnover = new PaymentTurnover();
					paymentTurnover = paymentTurnoverService.findBySn(out_trade_no);
					if (paymentTurnover.getTradeType().equals(TradeType.order)) {
						paymentService.paySuccess(paymentTurnover.getSn(),
								paymentTurnover.getOrder().getSn());
					}
					// 注意：
					// 付款完成后，支付宝系统发送该交易状态通知
				}

				// 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
				logger.info("订单支付成功：");
				response.getOutputStream().println("success");

			} else {// 验证失败
				// 验签失败则记录异常日志，并在response中返回failure.
				response.getOutputStream().println("fail");
				String sWord = AlipaySignature.getSignCheckContentV1(params);
				logger.info("订单支付失败：{}", sWord);

				// 调试用，写文本函数记录程序运行情况是否正常
				// String sWord =
				// AlipaySignature.getSignCheckContentV1(params);
				// AlipayConfig.logResult(sWord);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}

		// 获取到返回的所有参数 先判断是否交易成功trade_status 再做签名校验
		// 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		// 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		// 3、校验通知中的seller_id（或者seller_email)
		// 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
		// 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。

	}

	/**
	 * 订单支付微信服务器异步通知
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/pay/weixinPay/notify/{paymentSn}")
	@ApiIgnore
	public void orderPayTenpayNotify(@PathVariable String paymentSn, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			synchronized (this) {
				Map<String, String> kvm = XMLUtil.parseRequestXmlToMap(request);
				logger.debug(kvm.toString());
				if (SignUtils.checkSign(kvm, wxPayService.getConfig().getMchKey())) {
					if (kvm.get("result_code").equals("SUCCESS")) {
						// 商户订单号
						String out_trade_no = kvm.get("out_trade_no");
						PaymentTurnover paymentTurnover = new PaymentTurnover();
						paymentTurnover.setSn(out_trade_no);
						paymentTurnover = paymentTurnoverService.getEntity(paymentTurnover);
						if (paymentTurnover.getTradeType().equals(TradeType.order)) {
							paymentService.paySuccess(paymentTurnover.getSn(),
									paymentTurnover.getOrder().getSn());
						}
						// 微信服务器通知此回调接口支付成功后，通知给业务系统做处理
						logger.info("out_trade_no: " + kvm.get("out_trade_no") + " pay SUCCESS!");
						response.getWriter().write(
								"<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[ok]]></return_msg></xml>");
					} else {
						this.logger.error("out_trade_no: " + kvm.get("out_trade_no")
								+ " result_code is FAIL");
						response.getWriter().write(
								"<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[result_code is FAIL]]></return_msg></xml>");
					}
				} else {
					response.getWriter().write(
							"<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[check signature FAIL]]></return_msg></xml>");
					this.logger.error(
							"out_trade_no: " + kvm.get("out_trade_no") + " check signature FAIL");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			;
		}
	}

	public String isMobileDevice(String requestHeader) {
		/**
		 * android : 所有android设备 mac os : iphone ipad windows
		 * phone:Nokia等windows系统的手机
		 */
		String[] deviceArray = new String[] { "android", "mac os", "windows phone" };
		if (requestHeader == null)
			return PC_URL;
		requestHeader = requestHeader.toLowerCase();
		for (int i = 0; i < deviceArray.length; i++) {
			if (requestHeader.indexOf(deviceArray[i]) > 0) {
				return WEBAPP_URL;
			}
		}
		return PC_URL;
	}

	/**
	 * 订单支付
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/pay/auth", method = RequestMethod.GET)
	@ApiIgnore
	public void orderPayAuth(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String requestHeader = request.getHeader("user-agent");
		String url = isMobileDevice(requestHeader);
		response.setContentType("text/html;charset=utf-8");
		logger.debug(url);
		response.sendRedirect(url);
	}
}