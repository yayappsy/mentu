<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.rssFeed" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(function(){

	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/article/rssFeed/?searchType=${searchType}"><spring:message
					code="admin.rssFeed" />
				<spring:message code="admin.common.list" /></a></li>
		<c:if test="${searchType ne 'selectLink' }">
			<shiro:hasPermission name="article:rssFeed:edit">
				<li><a href="${ctx}/article/rssFeed/form"> <spring:message
							code="admin.rssFeed" />
						<spring:message code="admin.common.add" /></a></li>
			</shiro:hasPermission>
		</c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="rssFeed"
		action="${ctx}/article/rssFeed/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden"
			value="${page.pageable.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageable.pageSize}" />
		<input id="searchType" name="searchType" type="hidden"
			value="${searchType}" />
		<ul class="ul-form">
			<li><label> <spring:message code="RssFeed.name" />：
			</label> <form:input path="name" htmlEscape="false" maxlength="100"
					class="input-medium" /></li>
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
				<th><spring:message code="RssFeed.name" /></th>
				<th><spring:message code="RssFeed.url" /></th>
				<th><spring:message code="DataEntity.updateDate" /></th>
				<th><spring:message code="DataEntity.remarks" /></th>
				<shiro:hasPermission name="article:rssFeed:edit">
					<th><spring:message code="admin.common.handle" /></th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="rssFeed">
				<tr>
					<c:if test="${searchType eq 'selectLink' }">
						<td><c:choose>
								<c:when test="${isMultiple}">
									<input type="checkbox" name="id" value="${rssFeed.id}"
										data-type="rssFeed" data-id="${rssFeed.id}"
										data-name="${rssFeed.name}" data-url="${rssFeed.url}" />
								</c:when>
								<c:otherwise>
									<input type="radio" name="id" value="${rssFeed.id}"
										data-type="rssFeed" data-id="${rssFeed.id}"
										data-name="${rssFeed.name}" data-url="${rssFeed.url}" />
								</c:otherwise>
							</c:choose></td>
					</c:if>
					<td><a href="${ctx}/article/rssFeed/form?id=${rssFeed.id}">
							${rssFeed.name} </a></td>
					<td>${rssFeed.url}</td>
					<td><fmt:formatDate value="${rssFeed.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${rssFeed.remarks}</td>
					<shiro:hasPermission name="article:rssFeed:edit">
						<td><a href="${ctx}/article/rssFeed/form?id=${rssFeed.id}"><spring:message
									code="admin.common.modify" /></a> <a
							href="${ctx}/article/rssFeed/delete?id=${rssFeed.id}"
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