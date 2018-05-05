<%
response.setStatus(500);

// 获取异常类
Throwable ex = Exceptions.getThrowable(request);
if (ex != null){
	LoggerFactory.getLogger("500.jsp").error(ex.getMessage(), ex);
}
%>
<%@page import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<%@page import="com.thinkgem.javamg.common.web.Servlets"%>
<%@page import="com.thinkgem.javamg.common.utils.Exceptions"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!doctype html>
<html>
<head>

<title>错误页面</title>
<link href="${ctxStatic }/modules/blh/weixin/css/base.css"
	rel="stylesheet" type="text/css" />
	
<link href="${ctxStatic }/weui/0.4.2/style/weui.min.css"
	rel="stylesheet" type="text/css" />
<link href="${ctxStatic }/weui_custome/0.4.2/style/weui_custome.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">

body {
	height: 100%;
	widht:100%;
	background: url(${ctxStatic }/images/wx_under_construction.png) no-repeat;
	background-size: cover; 
}

.error_img {
	position: absolute;
	top: 0;
	left: 0;
	max-width: 640px;
	height: auto;
}

.title {
	position: absolute;
	bottom: 350px;
}

.weui_cells_title {
	font-size: 45px;
}

.weui_btn_area {
	position: absolute;
	bottom: 200px;
}

.weui_btn {
	font-size: 25px;
	margin-left: 20px;
	display: inline-block;
}
</style>
</head>

<body>
	<div class="title">
		<div class="weui_cells_title">你访问的该功能正在建设中，你可以</div>
	</div>
	<%-- <div class="error_img">
		<img alt="" src="${ctxStatic }/images/wx_under_construction.png">
	</div> --%>
	<div class="weui_btn_area">
		<a type="button" id="upgrade" class="weui_btn weui_btn_primary"
			href="${ctxWx }/index">返回首页</a>
		<a type="button" id="upgrade" class="weui_btn weui_btn_primary" onclick="history.go(-1)"
			href="javascript:;">返回上一页</a>
	</div>
</body>
</html>
