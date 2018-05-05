<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
<title><spring:message code="招贤纳士" /></title>
<meta name="decorator" content="front_home" />
<link href="${ctxStatic }/modules/rysh/front/css/job.css"
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

		$('.l_box .menu dd a').click(function() {
			$('.l_box .menu dd a').removeClass('current');
			$(this).addClass('current')
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
			<li>招贤纳士</li>
		</ul>
	</div>
	<!--当前位置 end-->

	<!--main-->
	<div class="m-main clearfix">
		<%@include file="/WEB-INF/views/modules/recruit/left.jsp"%>
		<!--right-->
		<div class="r_box right">
			<!--职位-->
			<div class="s_job">
				
				
					<c:forEach items="${rfns:getArticleByCategory('6')}"
						var="article">
	                    <p id="${article.id}" class="job_name">${article.title}</p>
						<div class="job_content">
							${fns:unescapeHtml(article.content)}</div>

					</c:forEach>
				

				<!--item end-->

				<p class="job_name">联系方式</p>
				<div class="job_content">
					<div class="j_box" style="font-size: 16px">
						电话：010-58205391<br />邮箱：hr@shihua.com
					</div>

				</div>
				<!--item end-->
			</div>
			<!--职位 end-->
		</div>
		<!--right end-->
	</div>
	<!--main end-->
</body>
</html>
