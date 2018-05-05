<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
<title><spring:message code="健康专题" /></title>
<meta name="decorator" content="front_home" />

<link href="${ctxStatic }/modules/rysh/front/css/news.css?ver=2213"
	rel="stylesheet" type="text/css" />
</head>

<body>

	<!--当前位置-->
	<div class="m-position">
		<ul class="clearfix">
			<li>当前位置</li>
			<li>&gt;</li>
			<li>资讯中心</li>
			<li>&gt;</li>
			<li>健康专题</li>
		</ul>
	</div>
	<!--当前位置 end-->
	<!--main-->
	<div class="m-main clearfix"
		style="margin-top: 10px; padding-top: 0px;">
		<!--left--->
		<div class="l_box left">
			<div class="content_box">
				<p class="title">${article.title}</p>
				<div class="time">
					<span>发布时间：<fmt:formatDate value="${article.createDate }"
							pattern="YYYY-MM-dd" /></span><span>来源：${article.articleSource}</span><span>作者：${article.author}</span>
				</div>

				<div class="content">${fns:unescapeHtml(article.content)}</div>

				<ul class="keyword clearfix">
					<li>关键词：</li>
					<li>${article.keywords}</li>
				</ul>

			</div>
			<!--内容 end-->

			
		</div>
		<!--left end-->

		
		<!--right end-->
	</div>
	<!--main end-->



</body>
</html>
