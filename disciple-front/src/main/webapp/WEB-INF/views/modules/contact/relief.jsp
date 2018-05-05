<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
<title><spring:message code="用户协议" /></title>
<meta name="decorator" content="front_home" />
<link href="${ctxStatic }/modules/rysh/front/css/singlepage.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(document).ready(function() {
		$('.l_box .type li').mouseover(function() {
			$('.l_box .type li a').removeClass('current');
			$(this).children('a').addClass('current');

			var index = $(this).index();
			$('.news_box').hide();
			$(".l_box .news_box:eq(" + index + ")").show();
		});
	});
</script>
</head>

<body>
	<div class="m-banner"></div>
	<!--当前位置-->
	<div class="m-position">
		<ul class="clearfix">
			<li>当前位置</li>
			<li>&gt;</li>
			<li>首页</li>
			<li>&gt;</li>
			<li>${article.category.name}</li>
		</ul>
	</div>
	<!--当前位置 end-->
	<!--main-->
	<div class="m-main clearfix">
		<div class="s_name">${article.title}</div>
		<!--免责申明-->
		<div class="s_box3">${fns:unescapeHtml(article.content)}</div>
		<!--免责申明 end-->
	</div>
	<!--main end-->
</body>
</html>
