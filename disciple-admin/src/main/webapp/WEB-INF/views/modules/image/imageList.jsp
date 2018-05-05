<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.image" /><spring:message
		code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
    $(function () {
    });
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/image/image/?searchType=${searchType }"><spring:message
					code="admin.image" /> <spring:message code="admin.common.list" /></a></li>
		<shiro:hasPermission name="image:image:edit">
			<li><a href="${ctx}/image/image/form?searchType=${searchType }">
					<spring:message code="admin.image" /> <spring:message
						code="admin.common.add" />
			</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="image"
		action="${ctx}/image/image/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden"
			value="${page.pageable.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageable.pageSize}" />
		<input id="searchType" name="searchType" type="hidden"
			value="${searchType }" />
		<ul class="ul-form">
			<li><label> <spring:message code="Image.name" />：
			</label> <form:input path="name" htmlEscape="false" maxlength="255"
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
				<th><spring:message code="Image.name" /></th>
				<th><spring:message code="Image.albumName" /></th>
				<th><spring:message code="Image.thumbnail" /></th>
				<th><spring:message code="DataEntity.updateDate" /></th>
				<th><spring:message code="DataEntity.remarks" /></th>
				<c:if test="${fns:isBlank(searchType) }">
					<shiro:hasPermission name="image:image:edit">
						<th><spring:message code="admin.common.handle" /></th>
					</shiro:hasPermission>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="image">
				<tr>
					<c:if test="${searchType eq 'selectLink' }">
						<td><c:choose>
								<c:when test="${isMultiple}">
									<input type="checkbox" name="id" value="${image.id}"
										data-type="image" data-id="${image.id}"
										data-name="${image.name}" data-url="${image.url}"
										data-storage-path="${image.storagePath}"
										data-path-type="${image.pathType}" />
								</c:when>
								<c:otherwise>
									<input type="radio" name="id" value="${image.id}"
										data-type="image" data-id="${image.id}"
										data-name="${image.name}" data-url="${image.url}"
										data-storage-path="${image.storagePath}"
										data-path-type="${image.pathType}" />
								</c:otherwise>
							</c:choose></td>
					</c:if>
					<td><a href="${ctx}/image/image/form?id=${image.id}">
							${image.name} </a></td>
					<td>${image.albumName}</td>
					<td><weimhc:showImage imagePath="${image.url}" /></td>
					<td><fmt:formatDate value="${image.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${image.remarks}</td>
					<c:if test="${fns:isBlank(searchType) }">
						<shiro:hasPermission name="image:image:edit">
							<td><a href="${ctx}/image/image/form?id=${image.id}"><spring:message
										code="admin.common.modify" /></a> <a
								href="${ctx}/image/image/delete?id=${image.id}"
								onclick="return confirmx('<spring:message code="admin.dialog.deleteConfirm"/>', this.href)">
									<spring:message code="admin.common.delete" />
							</a></td>
						</shiro:hasPermission>
					</c:if> 
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<sys:pagination paginationSize="1" pageable="${page.pageable }" />
</body>
</html>