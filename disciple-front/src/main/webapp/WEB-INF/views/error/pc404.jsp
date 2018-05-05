<%
response.setStatus(404);

// 如果是异步请求或是手机端，则直接返回信息
if (Servlets.isAjaxRequest(request)) {
	out.print("页面不存在.");
}

//输出异常信息页面
else {
%>
<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<%@page import="com.thinkgem.javamg.common.web.Servlets"%>
<%@page import="com.thinkgem.javamg.common.config.Global"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>404</title>
<link href="${ctxStatic}/modules/center/css/base.css" rel="stylesheet" type="text/css" />
<meta name="decorator" content="default" />
</head>
<body>
<% if("true".equals(Global.getConfig("debugMode"))){ %>
	<div class="container-fluid">
		<div class="page-header"><h1>页面不存在.</h1></div>
		<div><a href="javascript:" onclick="history.go(-1);" class="btn">返回上一页</a></div>
		<script>try{top.$.jBox.closeTip();}catch(e){}</script>
	</div>
<% }else{ %>		
	<!--mian-->
	<div class="m-main clearfix yahei"
		style="width: 990px; margin: 0 auto; margin-top: 50px;">
		<div class="left" style="width: 455px; text-align: center;">
			<img src="${ctxStatic}/images/pc404.jpg" width="245" height="245" />
		</div>
		<div class="right" style="width: 535px; padding-top: 40px;">
			<p style="font-size: 20px;">
				<span style="font-size: 25px; color: #1752A5; padding-right: 10px;">Error
					404</span>抱歉，您要访问的页面不存在
			</p>
			<p
				style="line-height: 30px; border-bottom: 1px dotted #ccc; padding-bottom: 5px; margin-bottom: 10px;">有可能我们的网页正在维护或者您输入的网址不正确</p>
			<p style="margin-top: 20px;">
				您可以：<a href="${ctx }"
					style="padding: 4px 14px; background: #1752A5; color: #fff; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">去首页</a>
				或 <a href="${ctx }" style="color: #1752A5;">随便看看</a>
			</p>
			<p style="line-height: 50px;">
				若您长时间无法正常使用，您可以 <a href="${ctx }" style="color: #1752A5;">联系客服</a>
			</p>
		</div>
	</div>
	<!--m-main end-->
<% }%>		
</body>
</html>
<%
out.print("<!--"+request.getAttribute("javax.servlet.forward.request_uri")+"-->");
} out = pageContext.pushBody();
%>