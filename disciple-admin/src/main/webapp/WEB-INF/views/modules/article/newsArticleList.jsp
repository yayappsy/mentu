<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.article" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(function(){

	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/article/news/?category.parent.id=${articles.category.parent.id}"><spring:message
					code="admin.article.list" /></a></li>
		<c:if test="${searchType ne 'selectLink' }">
			<shiro:hasPermission name="article:article:edit">
				<li><a
					href="${ctx}/article/news/form?category.parent.id=${articles.category.parent.id}">
						<spring:message code="admin.article.add" />
				</a></li>
			</shiro:hasPermission>
		</c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="article"
		action="${ctx}/article/news/?category.parent.id=${articles.category.parent.id}"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden"
			value="${page.pageable.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageable.pageSize}" />
		<input id="searchType" name="searchType" type="hidden"
			value="${searchType }" />
		<ul class="ul-form">
			<li><label> <spring:message code="Article.title" />：
			</label> <form:input path="title" htmlEscape="false" maxlength="255"
					class="input-medium" /></li>
			<li><label> <spring:message code="Article.categoryId" />：
			</label> <sys:treeselect id="category" name="category.id"
					value="${article.category.id}" labelName=""
					labelValue="${article.category.name}" title="文章类别"
					url="/article/articleCategory/treeData"
					extId="${article.category.parent.id}" cssClass="required"
					allowClear="true" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="<spring:message code='admin.common.search'/>" />
				<input id="btnClear" class="btn btn-primary" type="button"
				value="<spring:message code='admin.common.clear'/>" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<weimhc:message resultMessage="${resultMessage}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<c:if test="${searchType eq 'selectLink' }">
					<th><spring:message code="admin.common.choose" /></th>
				</c:if>
				<th><spring:message code="Article.title" /></th>
				<th><spring:message code="Article.categoryId" /></th>
				<th><spring:message code="Article.author" /></th>
				<%-- <th> <spring:message code="Article.isHot"/></th>
				<th> <spring:message code="Article.isRecommend"/></th> --%>
				<th><spring:message code="DataEntity.sort" /></th>
				<th><spring:message code="DataEntity.updateDate" /></th>

				<shiro:hasPermission name="article:article:edit">
					<th><spring:message code="admin.common.handle" /></th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="article">
				<tr>
					<c:if test="${searchType eq 'selectLink' }">
						<td><c:choose>
								<c:when test="${isMultiple}">
									<input type="checkbox" name="id" value="${article.id}" 
										data-type="article" data-id="${article.id}"
										data-title="${article.title}" />
								</c:when>
								<c:otherwise>
									<input type="radio" name="id" value="${article.id}"
										data-type="article" data-id="${article.id}"
										data-title="${article.title}" />
								</c:otherwise>
							</c:choose></td>
					</c:if>
					<td><a href="${ctx}/article/news/form?id=${article.id}">
							${article.title} </a></td>
					<td>${article.category.name}</td>
					<td>${article.author}</td>
					<%-- 	<td>
					${fns:getDictLabel(article.isHot,'true_false','')}
				</td>
				<td>
					${fns:getDictLabel(article.isRecommend,'true_false','')}
				</td> --%>
					<td>${article.sort}</td>
					<td><fmt:formatDate value="${article.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>

					<shiro:hasPermission name="article:article:edit">
						<td><a
							href="${ctx}/article/news/form?id=${article.id}&category.parent.id=${articles.category.parent.id}"><spring:message
									code="admin.common.modify" /></a> <a
							href="${ctx}/article/news/delete?id=${article.id}&category.parent.id=${articles.category.parent.id}"
							onclick="return confirmx('<spring:message code="admin.dialog.deleteConfirm"/>', this.href)">
								<spring:message code="admin.common.delete" />
						</a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<sys:pagination paginationSize="1" pageable="${page.pageable }" />
</body>
</html>