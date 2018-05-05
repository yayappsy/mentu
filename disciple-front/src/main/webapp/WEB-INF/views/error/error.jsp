<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<%@page import="com.thinkgem.javamg.common.web.Servlets"%>
<%@page import="com.thinkgem.javamg.common.config.Global"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="shop.error.title" /></title>
<link href="${ctxStatic}/modules/center/css/base.css" rel="stylesheet"
	type="text/css" />
<meta name="decorator" content="default" />
</head>
<body>
	<!--mian-->
	<div class="m-main clearfix yahei"
		style="width: 990px; margin: 0 auto; margin-top: 50px;">
		<div style="width: 535px; padding: 40px 20% 0px; height: 245px;">
			<p style="font-size: 20px;">
				<span style="font-size: 25px; color: #1752A5; padding-right: 10px;"></span>
				${message}
			</p>
			<p
				style="line-height: 30px; border-bottom: 1px dotted #ccc; padding-bottom: 5px; margin-bottom: 10px;">
				<spring:message code="shop.error.message" />
			</p>
			<p style="margin-top: 20px;">
				您可以：<a href="${ctx }"
					style="padding: 4px 14px; background: #1752A5; color: #fff; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
					<spring:message code="shop.error.home" />
				</a> 或 <a href="${ctx }" style="color: #1752A5;">随便看看</a>
			</p>
			<p style="line-height: 50px;">
				若您长时间无法正常使用，您可以 <a href="${ctx }" style="color: #1752A5;">联系客服</a>
			</p>
		</div>
	</div>

</body>
</html>
