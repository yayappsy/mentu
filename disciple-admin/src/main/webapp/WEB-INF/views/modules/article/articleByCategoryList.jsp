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
			href="${ctx}/article/byCategory/?category.id=${article.category.id}"><spring:message
					code="admin.article.list" /></a></li>
		<shiro:hasPermission name="article:article:edit">
			<li><a
				href="${ctx}/article/byCategory/form?category.id=${article.category.id}">
					<spring:message code="admin.article.add" />
			</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="article"
		action="${ctx}/article/byCategory/?category.id=${article.category.id}"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden"
			value="${page.pageable.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageable.pageSize}" />
		<ul class="ul-form">
			<li><label> <spring:message code="Article.title" />：
			</label> <form:input path="title" htmlEscape="false" maxlength="255"
					class="input-medium" /></li>
			<%-- <li><label> <spring:message code="Article.categoryId" />：
			</label> <sys:treeselect id="category" name="category.id"
					value="${article.category.id}" labelName=""
					labelValue="${article.category.name}" title="文章类别"
					url="/article/articleCategory/treeData" cssClass="required"
					allowClear="true" /></li> --%>
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
					<td><a href="${ctx}/article/byCategory/form?id=${article.id}">
							${article.title} </a></td>
					<td>${article.category.name}</td>
					<td>${article.author}</td>
			    <%-- <td>
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
							href="${ctx}/article/byCategory/form?id=${article.id}&category.id=${article.category.id}"><spring:message
									code="admin.common.modify" /></a> <a
							href="${ctx}/article/byCategory/delete?id=${article.id}"
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