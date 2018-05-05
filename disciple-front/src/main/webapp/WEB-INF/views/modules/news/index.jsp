<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
<title><spring:message code="资讯中心" /></title>
<meta name="decorator" content="front_home" />

<link href="${ctxStatic }/modules/rysh/front/css/news.css?ver=2220"
	rel="stylesheet" type="text/css" />

<script type="text/javascript">
	$(function() {
		if ("${categoryId}" == "") {
			$("#all").addClass("current");
		} else {
			$("#" + "${categoryId}").addClass("current");
		}

	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
        $('.l_box .type li').mouseover(function(){
			$('.l_box .type li a').removeClass('current');
			$(this).children('a').addClass('current');
			
			/* var index = $(this).index();
			$('.news_box').hide();
			$(".l_box .news_box:eq("+index+")").show();
			}); */
    });
</script>

<script type="text/javascript"
	src="${ctxStatic}/jcarousellite/jcarousellite.min.js"></script>
<script type="text/javascript">
	$(function(){
	$(".rollpicshow").jCarouselLite({
		auto: 3000, /*自动播放间隔时间*/
		speed: 500, /*速度*/
		btnNext:".next",/*向前滚动*/
		btnPrev:".prev",/*向后滚动*/
		visible:1 /*显示数量*/
	});
});
</script>

</head>

<body>
	<!--banner-->
	<%@include file="/WEB-INF/views/modules/news/newsAd.jsp"%>
	<!--banner end-->
	<!--当前位置-->
	<div class="m-position_buttom">
		<ul class="clearfix">
			<li>当前位置</li>
			<li>&gt;</li>
			<li>公司动态</li>
			<li>&gt;</li>
			<li>全部</li>
		</ul>
	</div>
	<!--当前位置 end-->
	<!--main-->
	<form:form id="inputForm" action="${ctx}/news/index" method="post">
		<input name="categoryId" type="hidden" value="${categoryId}" />
		<input id="pageNo" name="pageNo" type="hidden"
			value="${page.pageable.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageable.pageSize}" />
	</form:form>
	<div class="m-main clearfix">
		<!--left--->
		<div class="l_box left">
			<div class="top">
				<div class="name left">公司咨询 NEWS</div>


				<ul class="type right">
					<li><a id="all" href="${ctx}/news/index">全部</a></li>
					<c:forEach items="${rfns:getArticleCategory('1')}"
						var="articleCategory">
						<li><a id="${articleCategory.id}"
							href="${ctx}/news/index?categoryId=${articleCategory.id}">${articleCategory.name}</a></li>
					</c:forEach>
				</ul>

			</div>

			<div class="news_box" style="display: block;">
				<c:forEach items="${page.list}" var="article">
					<div class="item clearfix">
						<a href="${ctx}/news/detail?articleId=${article.id}"
							class="thumb left"><img src="${imgURL}${article.image}" /></a>
						<div class="info right">
							<p class="p1">
								<a href="#" class="typename">[${article.category.name}]</a><a
									href="${ctx}/news/detail?articleId=${article.id}" class="title">${article.title}</a>
							</p>
							<p class="time">
								(
								<fmt:formatDate value="${article.createDate }"
									pattern="YYYY-MM-dd" />
								)
							</p>
							<p class="desc">${article.description}<a
									href="${ctx}/news/detail?articleId=${article.id}">阅读全文&gt;&gt;</a>
							</p>
						</div>
					</div>
				</c:forEach>
				<!--item end-->
			</div>
			<!--page-->
			<!---分页-->
			<div class="m-page right">

				<rysh:pagination pageable="${page.pageable }" paginationSize="" />

			</div>
			<!--分页 end-->

			<%-- <div class="m-page-box">
				<div class="m-page">
					<rysh:pagination pageable="${page.pageable }" paginationSize=""/>
				</div>
			</div> --%>
			<!--page end-->
		</div>
		<!--left end-->

	

	</div>

	<!--main end-->


</body>
</html>
