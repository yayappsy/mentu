/**
 * 
 */
package com.weimhc.framework.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest.Builder;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.laozhaishaozuo.wxpay.service.impl.WxTradeType;
import com.google.common.collect.Maps;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.utils.ZxingHandler;
import com.thoughtworks.xstream.XStream;
import com.weimhc.framework.service.impl.BaseServiceImpl;
import com.weimhc.framework.utils.AccessUtils;
import com.weimhc.framework.utils.AlipayUtil;
import com.weimhc.framework.utils.RequestUtils;
import com.weimhc.framework.utils.UploadUtils;
import com.weimhc.modules.base.entity.SnType;
import com.weimhc.modules.base.service.SnService;
import com.weimhc.modules.order.entity.Order;
import com.weimhc.modules.order.entity.OrderInstalment;
import com.weimhc.modules.order.entity.PaymentStatus;
import com.weimhc.modules.order.service.OrderInstalmentService;
import com.weimhc.modules.order.service.OrderService;
import com.weimhc.modules.payment.entity.PaymentFrom;
import com.weimhc.modules.payment.entity.PaymentMethod;
import com.weimhc.modules.payment.entity.PaymentTurnover;
import com.weimhc.modules.payment.entity.TradeType;
import com.weimhc.modules.payment.service.PaymentTurnoverService;

import me.chanjar.weixin.common.util.xml.XStreamInitializer;

/**
 * @author laozh
 *
 */
@Service
public class PaymentService extends BaseServiceImpl {

	@Resource(name = "wxPayService")
	private WxPayService wxPayService;

	@Autowired
	private SnService snService;

	@Autowired
	private PaymentTurnoverService paymentTurnoverService;

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderInstalmentService orderInstalmentService;
	public static String ALIPAY_RETURNURL = Global
			.getConfig("alipay.returnUrl");

	/**
	 * 根据订单创建支付流水信息
	 * 
	 * @param order
	 * @return
	 */
	@Transactional(readOnly = false)
	public Map<String, String> createPaymentTurnover(Order order,
			PaymentFrom paymentFrom) {
		Map<String, String> payInfo = Maps.newHashMap();
		PaymentTurnover paymentTurnover = new PaymentTurnover();
		if (order.getPaymentMethod() == null || order.getPaymentMethod()
				.getMethod() != PaymentMethod.Method.online) {
			logger.debug("PaymentMethod is null");
			return null;
		}
		if (order.getPaymentStatus() != PaymentStatus.unpaid
				&& order.getPaymentStatus() != PaymentStatus.partialPayment) {
			logger.debug("PaymentStatus 不是未支付");
			return null;
		}
		if (order.getAmountPayable().compareTo(new BigDecimal(0)) <= 0) {
			logger.debug("AmountPayable < 0");
			return null;
		}
		paymentTurnover.setBuyer(order.getMember());
		paymentTurnover.setBuyerNickname(order.getMemberNickname());
		paymentTurnover.setSn(snService.generate(SnType.payment, null));
		paymentTurnover.setStatus(PaymentTurnover.Status.wait);
		paymentTurnover.setAmount(order.getAmountPayable());
		paymentTurnover.setOrder(order);
		paymentTurnover.setPaymentFrom(paymentFrom);
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, 60);
		paymentTurnover.setExpire(nowTime.getTime());
		paymentTurnover.setPaymentMethod(order.getPaymentMethod());
		paymentTurnover.setTradeType(TradeType.order);
		paymentTurnoverService.save(paymentTurnover);

		if ("2".equals(order.getPaymentMethod().getId())) {
			payInfo.put("payStr",
					createAlipayOrderStr(paymentTurnover, order, paymentFrom));
		} else {
			payInfo = createTenpayStr(paymentTurnover, order, paymentFrom);
		}
		return payInfo;
	}

	/**
	 * 根据订单创建支付流水信息
	 * 
	 * @param order
	 * @return
	 */
	@Transactional(readOnly = false)
	public Map<String, String> createInstalmentPaymentTurnover(
			OrderInstalment orderInstalment, PaymentFrom paymentFrom) {
		Map<String, String> payInfo = Maps.newHashMap();
		orderInstalment = orderInstalmentService.get(orderInstalment.getId());
		Order order = orderService.get(orderInstalment.getOrder().getId());
		if (order == null) {
			logger.debug("该订单不存在");
		}
		PaymentTurnover paymentTurnover = new PaymentTurnover();
		if (order.getPaymentMethod() == null || order.getPaymentMethod()
				.getMethod() != PaymentMethod.Method.online) {
			logger.debug("PaymentMethod is null");
			return null;
		}
		if (orderInstalment.getIsOver()) {
			logger.debug("PaymentStatus 不是未支付");
			return null;
		}
		if (order.getAmountPayable().compareTo(new BigDecimal(0)) <= 0) {
			logger.debug("AmountPayable < 0");
			return null;
		}
		paymentTurnover.setBuyer(order.getMember());
		paymentTurnover.setBuyerNickname(order.getMemberNickname());
		paymentTurnover.setSn(snService.generate(SnType.payment, null));
		paymentTurnover.setStatus(PaymentTurnover.Status.wait);
		paymentTurnover.setAmount(orderInstalment.getPrice());
		paymentTurnover.setOrder(order);
		paymentTurnover.setPaymentFrom(paymentFrom);
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, 60);
		paymentTurnover.setExpire(nowTime.getTime());
		paymentTurnover.setPaymentMethod(order.getPaymentMethod());

		paymentTurnover.setTradeType(TradeType.order);

		paymentTurnoverService.save(paymentTurnover);

		if ("2".equals(order.getPaymentMethod().getId())) {
			payInfo.put("payStr",
					createAlipayOrderStr(paymentTurnover, order, paymentFrom));
		} else {
			payInfo = createTenpayStr(paymentTurnover, order, paymentFrom);
		}
		return payInfo;
	}

	/**
	 * 根据订单创建分期一次还清支付流水信息
	 * 
	 * @param order
	 * @return
	 */
	@Transactional(readOnly = false)
	public Map<String, String> createOneInstalmentPaymentTurnover(Order order,
			List<OrderInstalment> orderInstalments, PaymentFrom paymentFrom) {
		Map<String, String> payInfo = Maps.newHashMap();
		if (order == null) {
			logger.debug("该订单不存在");
		}
		PaymentTurnover paymentTurnover = new PaymentTurnover();
		if (order.getPaymentMethod() == null || order.getPaymentMethod()
				.getMethod() != PaymentMethod.Method.online) {
			logger.debug("PaymentMethod is null");
			return null;
		}
		BigDecimal amount = BigDecimal.valueOf(0);
		for (OrderInstalment orderInstalment : orderInstalments) {
			orderInstalment = orderInstalmentService
					.get(orderInstalment.getId());
			amount = amount.add(orderInstalment.getPrice());
		}
		if (amount.compareTo(new BigDecimal(0)) <= 0) {
			logger.debug("AmountPayable < 0");
			return null;
		}
		paymentTurnover.setBuyer(order.getMember());
		paymentTurnover.setBuyerNickname(order.getMemberNickname());
		paymentTurnover.setSn(snService.generate(SnType.payment, null));
		paymentTurnover.setStatus(PaymentTurnover.Status.wait);
		paymentTurnover.setAmount(amount);
		paymentTurnover.setOrder(order);
		paymentTurnover.setPaymentFrom(paymentFrom);
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, 60);
		paymentTurnover.setExpire(nowTime.getTime());
		paymentTurnover.setPaymentMethod(order.getPaymentMethod());
		paymentTurnoverService.save(paymentTurnover);

		if ("2".equals(order.getPaymentMethod().getId())) {
			payInfo.put("payStr",
					createAlipayOrderStr(paymentTurnover, order, paymentFrom));
		} else {
			payInfo = createTenpayStr(paymentTurnover, order, paymentFrom);
		}
		return payInfo;
	}

	/**
	 * 支付成功
	 * 
	 * @param paymentSn
	 *            支付流水号
	 * @param thirdTradeNo
	 *            第三方支付编号
	 */
	@Transactional(readOnly = false)
	public void paySuccess(String paymentSn, String thirdTradeNo) {

		PaymentTurnover paymentTurnover = paymentTurnoverService
				.findBySn(paymentSn);

		paymentTurnover.setThirdTradeNo(thirdTradeNo);
		paymentTurnover.setStatus(PaymentTurnover.Status.success);
		paymentTurnoverService.save(paymentTurnover);

		orderService.payOrder(paymentTurnover.getOrder(), paymentTurnover);

	}

	/**
	 * 支付失败
	 * 
	 * @param paymentSn
	 *            支付流水号
	 * @param thirdTradeNo
	 *            第三方支付编号
	 */
	@Transactional(readOnly = false)
	public void payFail(String paymentSn, String thirdTradeNo) {

		PaymentTurnover paymentTurnover = paymentTurnoverService
				.findBySn(paymentSn);

		paymentTurnover.setThirdTradeNo(thirdTradeNo);
		paymentTurnover.setStatus(PaymentTurnover.Status.failure);

		paymentTurnoverService.save(paymentTurnover);

	}

	/**
	 * 创建通知函数
	 * 
	 * @param tag
	 * @param paymentSn
	 * @return
	 */
	private String createNotifyUrl(String tag, String paymentSn) {
		String notifyUrl = RequestUtils.getContextHttpUri(
				RequestUtils.getHttpServletRequest()) + Global.getApiPath()
				+ "/payment/pay/" + tag + "/notify/" + paymentSn;
		return notifyUrl;
	}

	/**
	 * 构建阿里支付字符串 可以直接使用
	 * 
	 * @param paymentTurnover
	 *            支付流水
	 * @param order
	 *            订单信息
	 * @param paymentFrom
	 *            支付发起来源
	 */
	private String createAlipayOrderStr(PaymentTurnover paymentTurnover,
			Order order, PaymentFrom paymentFrom) {

		AlipayClient alipayClient = AlipayUtil.getAlipayClient();
		String orderString = null;
		try {

			if (PaymentFrom.APP.equals(paymentFrom)) {
				AlipayTradeAppPayRequest alipayRequest = new AlipayTradeAppPayRequest();
				AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
				model.setSubject(order.getSubject() + paymentTurnover.getSn());
				model.setOutTradeNo(paymentTurnover.getSn());
				model.setTimeoutExpress("60m");
				model.setTotalAmount(
						paymentTurnover.getAmount().toPlainString());
				model.setProductCode("QUICK_MSECURITY_PAY");

				alipayRequest.setBizModel(model);
				alipayRequest.setNotifyUrl(
						createNotifyUrl("alipay", paymentTurnover.getSn()));
				alipayRequest.setReturnUrl(ALIPAY_RETURNURL);
				AlipayTradeAppPayResponse alipayResponse = alipayClient
						.sdkExecute(alipayRequest);
				orderString = alipayResponse.getBody();// 可以直接给客户端请求，无需再做处理。
			} else if (PaymentFrom.PC.equals(paymentFrom)) {
				AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
				AlipayTradePagePayModel model = new AlipayTradePagePayModel();
				model.setSubject(order.getSubject());
				model.setOutTradeNo(paymentTurnover.getSn());
				model.setTimeoutExpress("60m");
				model.setTotalAmount("0.01");
				model.setProductCode("FAST_INSTANT_TRADE_PAY");

				alipayRequest.setBizModel(model);
				alipayRequest.setNotifyUrl(
						createNotifyUrl("alipay", paymentTurnover.getSn()));
				alipayRequest.setReturnUrl(ALIPAY_RETURNURL);
				orderString = alipayClient.pageExecute(alipayRequest).getBody();// 可以直接给客户端请求，无需再做处理。

				// orderString = orderString.replace(".submit()", "");
			} else if (PaymentFrom.MobileBrowser.equals(paymentFrom)) {
				AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
				AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
				model.setSubject(order.getSubject());
				model.setOutTradeNo(paymentTurnover.getSn());
				model.setTimeoutExpress("60m");
				model.setTotalAmount("0.01");
				model.setProductCode("FAST_INSTANT_TRADE_PAY");

				alipayRequest.setBizModel(model);
				alipayRequest.setNotifyUrl(
						createNotifyUrl("alipay", paymentTurnover.getSn()));
				alipayRequest.setReturnUrl(ALIPAY_RETURNURL);
				orderString = alipayClient.pageExecute(alipayRequest).getBody();// 可以直接给客户端请求，无需再做处理。

				// orderString = orderString.replace(".submit()", "");
			}
			logger.debug(orderString);// 就是orderString

		} catch (

		AlipayApiException e)

		{
			logger.error(e.getMessage());
		}
		return orderString;

	}

	/**
	 * 构建微信支付信息
	 * 
	 * @param paymentTurnover
	 *            支付流水
	 * @param order
	 *            订单信息
	 * @param paymentFrom
	 *            支付发起来源
	 */
	private Map<String, String> createTenpayStr(PaymentTurnover paymentTurnover,
			Order order, PaymentFrom paymentFrom) {

		Builder wxUnifiedOrderRequestBuilder = WxPayUnifiedOrderRequest
				.newBuilder().outTradeNo(paymentTurnover.getSn())
				.feeType("CNY");

		wxUnifiedOrderRequestBuilder
				.body(order.getSubject() + paymentTurnover.getSn());

		Double totalFee = paymentTurnover.getAmount().doubleValue() * 100;

		wxUnifiedOrderRequestBuilder.totalFee(1);

		if (PaymentFrom.PC.equals(paymentFrom)) {
			wxUnifiedOrderRequestBuilder.tradeType(WxTradeType.NATIVE.name());
		} else if (PaymentFrom.WeixinBrowser.equals(paymentFrom)) {
			wxUnifiedOrderRequestBuilder.tradeType(WxTradeType.JSAPI.name());
		} else if (PaymentFrom.MobileBrowser.equals(paymentFrom)) {
			wxUnifiedOrderRequestBuilder.tradeType(WxTradeType.MWEB.name());
		} else if (PaymentFrom.APP.equals(paymentFrom)) {
			wxUnifiedOrderRequestBuilder.tradeType(WxTradeType.APP.name());
		}
		wxUnifiedOrderRequestBuilder.productId(paymentTurnover.getSn());// 为了创建扫码支付
		wxUnifiedOrderRequestBuilder.spbillCreateIp(AccessUtils.getIpAddress());
		wxUnifiedOrderRequestBuilder.notifyURL(
				createNotifyUrl("weixinPay", paymentTurnover.getSn()));
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxPayUnifiedOrderRequest.class);
		xstream.processAnnotations(WxPayUnifiedOrderResult.class);
		WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = wxUnifiedOrderRequestBuilder
				.build();
		wxPayUnifiedOrderRequest.setNonceStr(System.currentTimeMillis() + "");

		Map<String, String> payInfo = null;
		try {
			String codeUrl = "";
			if (PaymentFrom.MobileBrowser.equals(paymentFrom)) {
				payInfo = new HashMap<>();
				WxPayUnifiedOrderResult wxPayUnifiedOrderResult = wxPayService
						.unifiedOrder(wxUnifiedOrderRequestBuilder.build());
				logger.debug(
						"mweb_url:" + wxPayUnifiedOrderResult.getMwebUrl());
				codeUrl = wxPayUnifiedOrderResult.getMwebUrl();
				payInfo.put("codeUrl", codeUrl
						+ "&redirect_url=http%3a%2f%2fm.shentancang.cn%2fhtml%2fperson%2fperson.html");
			} else {
				payInfo = wxPayService
						.getPayInfo(wxUnifiedOrderRequestBuilder.build());
				codeUrl = payInfo.get("codeUrl");
				String codeSn = snService.generate(SnType.code);
				ZxingHandler.getQRCodeImge(codeUrl, 168,
						UploadUtils
								.getUserfilesBaseDir(order.getMember().getId())
								+ "/wxpay/" + codeSn);
				payInfo.put("codeUrl",
						UploadUtils
								.getUserfilesBaseURL(order.getMember().getId())
								+ "/wxpay/" + codeSn);
			}

			logger.debug("codeUrl:" + payInfo.get("codeUrl"));
			logger.debug("payInfo:" + payInfo.toString());
		} catch (WxPayException e) {
			logger.error(e.getMessage());
		}
		return payInfo;

	}

}
