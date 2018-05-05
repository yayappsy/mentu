<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!--资讯中心-->
<section class="m-block m-news">
	<div class="inner clearfix">
		<div class="m-title">
			资讯中心
			<div class="line"></div>
			<div class="en">NEWS</div>
		</div>
		<div class="main clearfix">
			<c:forEach items="${rfns:getArticleCategory('1')}"
				var="articleCategory" varStatus="s" end="2">
				<c:if test="${not s.last}">
					<div class="item">
						
						<a href="${ctx}/news/index?categoryId=${articleCategory.id}"><p class="typename">${articleCategory.name}<span>${articleCategory.keywords}</span>
						</p></a>
						<div class="thumb">
							<img src="${imgURL}${articleCategory.image}" />
						</div>
						<ul>
							<c:forEach
								items="${rfns:getArticleByCategory(articleCategory.id)}"
								var="article">
								<li><a href="${ctx}/news/detail?articleId=${article.id}">${fns:abbr(article.title,40)}</a><span><fmt:formatDate
											value="${article.createDate}" pattern="yyyy-MM-dd" /></span></li>
							</c:forEach>
						</ul>
						<a href="${ctx}/news/index?categoryId=${articleCategory.id}"
							class="more">查看更多</a>
					</div>
				</c:if>
				<c:if test="${s.last}">
					<div class="item" style="margin-right: 0px;">
					<a href="${ctx}/news/index?categoryId=${articleCategory.id}"><p class="typename">${articleCategory.name}<span>${articleCategory.keywords}</span>
						</p></a>
						<div class="thumb">
							<img src="${imgURL}${articleCategory.image}" />
						</div>				
						<ul>
							<c:forEach
								items="${rfns:getArticleByCategory(articleCategory.id)}"
								var="article">
								<li><a href="${ctx}/news/detail?articleId=${article.id}">${article.title }</a><span><fmt:formatDate
											value="${article.createDate}" pattern="yyyy-MM-dd" /></span></li>
							</c:forEach>
						</ul>
						<a href="${ctx}/news/index?categoryId=${articleCategory.id}"
							class="more">查看更多</a>
					</div>
				</c:if>
			</c:forEach>
			<!--item end-->
		</div>
	</div>
</section>
<div class="m-shade"></div>
<!--资讯中心 end-->

