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
<style type="text/css">
html {
	height: 100%;
}

body {
	height: 100%;
	background: url(${ctxStatic }/images/wx404.png) no-repeat;
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
	font-size: 45px;
	margin-left: 100px;
}
</style>
</head>

<body>
	<%-- <div class="error_img">
		<img src="${ctxStatic }/images/wx404.png"
			style="width: 100%; height: 100%">

	</div> --%>
	<div class="title">
		<div class="weui_cells_title">你要查找的页面飞走了，你可以：</div>
	</div>
	<div class="weui_btn_area">
		<a type="button" id="upgrade" class="weui_btn weui_btn_primary"
			href="${ctxWx }/">返回首页</a>
	</div>
</body>
</html>
