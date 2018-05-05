<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
<title><spring:message code="资讯详情" /></title>
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
			<li>${article.category.name}</li>
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
				
				
				<!-- JiaThis Button BEGIN -->
<div class="jiathis_style" style="padding-left:572px;position: absolute;
    bottom: 20px;"><span class="jiathis_txt" style=" font-size: 14px;">分享到：</span>
<a class="jiathis_button_qzone"></a>
<a class="jiathis_button_tsina"></a>
<a class="jiathis_button_tqq"></a>
<a class="jiathis_button_weixin"></a>
<a class="jiathis_button_renren"></a>
<a class="jiathis_button_kaixin001"></a>
<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank"></a>
<a class="jiathis_counter_style"></a>
</div>
<script type="text/javascript" >
var jiathis_config={
	summary:"",
	shortUrl:false,
	hideMore:false
}
</script>
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
<!-- JiaThis Button END -->
					
<!-- JiaThis Button BEGIN -->
<!-- <div class="jiathis_style_24x24" style="padding-left:572px;position: absolute;
    bottom: 20px;">
 <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank">分享到：</a>
	<a class="jiathis_button_qzone"></a>
	<a class="jiathis_button_tsina"></a>
	<a class="jiathis_button_tqq"></a>
	<a class="jiathis_button_weixin"></a>
	<a class="jiathis_button_renren"></a>
	<a class="jiathis_counter_style"></a>
</div>
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script> -->
<!-- JiaThis Button END -->

				<ul class="keyword clearfix">
					<li>关键词：</li>
					<li>${article.keywords}</li>
				</ul>
			
			</div>
			<!--内容 end-->

			<!--相关文章-->
			<div class="related">
				<div class="title1">相关文章</div>
				<c:if
					test="${relatedArticle ne null and relatedArticle.id ne null }">
				<div class="item clearfix" style="border: none;">
						<a href="${ctx}/news/detail?articleId=${relatedArticle.id}"
							class="thumb left"><img
							src="${imgURL}${relatedArticle.image}" /></a>
						<div class="info right">
							<p class="p1">
								<a href="${ctx}/news/detail?articleId=${relatedArticle.id}"
									class="title">${relatedArticle.title}</a>
							</p>
							<p class="desc">${article.description}<a
									href="${ctx}/news/detail?articleId=${relatedArticle.id}">阅读全文&gt;</a>
							</p>
							<p class="time" style="margin-bottom: 0px;">
								(
								<fmt:formatDate value="${relatedArticle.createDate }"
									pattern="YYYY-MM-dd" />
								)
							</p>
						</div>
					</div>
					<!--item end-->
				</c:if>
			</div>
			<!--相关文章 end-->
		</div>
		<!--left end-->

	</div>
	<!--main end-->



</body>
</html>
