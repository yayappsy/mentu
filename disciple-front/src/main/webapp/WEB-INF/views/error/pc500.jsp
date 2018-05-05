<%
response.setStatus(500);

// 获取异常类
Throwable ex = Exceptions.getThrowable(request);
if (ex != null){
	LoggerFactory.getLogger("500.jsp").error(ex.getMessage(), ex);
}

// 编译错误信息
StringBuilder sb = new StringBuilder("错误信息：\n");
if (ex != null) {
	sb.append(Exceptions.getStackTraceAsString(ex));
} else {
	sb.append("未知错误.\n\n");
}

// 如果是异步请求或是手机端，则直接返回信息
if (Servlets.isAjaxRequest(request)) {
	out.print(sb);
}

// 输出异常信息页面
else {
%>
<%@page import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<%@page import="com.thinkgem.javamg.common.web.Servlets"%>
<%@page import="com.thinkgem.javamg.common.utils.Exceptions"%>
<%@page import="com.thinkgem.javamg.common.utils.StringUtils"%>
<%@page import="com.thinkgem.javamg.common.config.Global"%>
<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>500 - 系统内部错误</title>
	<link href="${ctxStatic}/modules/blh/front/css/base.css" rel="stylesheet" type="text/css" />
	<%@include file="/WEB-INF/views/include/front/head.jsp" %>
    <meta name="decorator" content="default" />
</head>
<body>
<% if("true".equals(Global.getConfig("debugMode"))){ %>
	<div class="container-fluid">
		<div class="page-header"><h1>系统内部错误.</h1></div>
		<div class="errorMessage">
			错误信息：<%=ex==null?"未知错误.":StringUtils.toHtml(ex.getMessage())%> <br/> <br/>
			请点击“查看详细信息”按钮，将详细错误信息发送给系统管理员，谢谢！<br/> <br/>
			<a href="javascript:" onclick="history.go(-1);" class="btn">返回上一页</a> &nbsp;
			<a href="javascript:" onclick="$('.errorMessage').toggle();" class="btn">查看详细信息</a>
		</div>
		<div class="errorMessage hide">
			<%=StringUtils.toHtml(sb.toString())%> <br/>
			<a href="javascript:" onclick="history.go(-1);" class="btn">返回上一页</a> &nbsp;
			<a href="javascript:" onclick="$('.errorMessage').toggle();" class="btn">隐藏详细信息</a>
			<br/> <br/>
		</div>
		<script>try{top.$.jBox.closeTip();}catch(e){}</script>
	</div>
<% }else{ %>	
   <!--mian-->
	<div class="m-main clearfix yahei"
		style="width: 990px; margin: 0 auto; margin-top:250px;">
		<div class="left" style="width: 455px; text-align: center;">
			<img src="${ctxStatic}/images/pc_under_construction.png" width="245" height="245" />
		</div>
		<div class="right" style="width: 535px; padding-top: 40px;">
			<p style="font-size: 20px;">
			<!-- 	<span style="font-size: 25px; color: #1752A5; padding-right: 10px;">Error
					500</span >-->抱歉，该功能正在建设中，敬请期待
			</p>
			<!-- <p
				style="line-height: 30px; border-bottom: 1px dotted #ccc; padding-bottom: 5px; margin-bottom: 10px;">有可能我们的网页正在维护或者您输入的网址不正确</p> -->
			<p style="margin-top: 20px;">
				您可以：<a href="${ctx }/"
					style="padding: 4px 14px; background: #1752A5; color: #fff; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">去首页</a>
				或 <a href="${ctx }/" style="color: #1752A5;">随便看看</a>
			</p>
			<!-- <p style="line-height: 50px;">
				若您长时间无法正常使用，您可以 <a href="#" style="color: #1752A5;">联系客服</a>
			</p> -->
		</div>
	</div>
	<!--m-main end-->
<% }%>	
</body>
</html>
<%
} out = pageContext.pushBody();
%>