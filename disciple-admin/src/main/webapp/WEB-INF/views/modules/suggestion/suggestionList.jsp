<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.suggestion" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(function(){

	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/suggestion/suggestion/"><spring:message
					code="admin.suggestion.list" /></a></li>
		<%-- <shiro:hasPermission name="suggestion:suggestion:edit"><li><a href="${ctx}/suggestion/suggestion/form">
		   <spring:message code="admin.suggestion.add"/></a></li>
		</shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="suggestion"
		action="${ctx}/suggestion/suggestion/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden"
			value="${page.pageable.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageable.pageSize}" />
		<ul class="ul-form">
			<li><label> <spring:message
						code="Suggestion.suggestionSubjectId" />：
			</label> <form:select path="suggestionSubject.id" class="input-medium">
					<form:option value="">
						<spring:message code="admin.common.defaultSelect" />
					</form:option>
					<form:options items="${suggestionSubjectList}"
						itemLabel="subjectTitle" itemValue="id" htmlEscape="false" />
				</form:select></li>
			<li><label> <spring:message
						code="Suggestion.mobile" />：
			</label> <form:input path="mobile" htmlEscape="false" maxlength="64"
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
				<th><spring:message code="Suggestion.mobile" /></th>
				<th><spring:message code="Suggestion.memberNickname" /></th>
				<th><spring:message code="Suggestion.suggestionSubjectId" /></th>
				<th><spring:message code="DataEntity.createDate" /></th>
				<th><spring:message code="DataEntity.remarks" /></th>
				<shiro:hasPermission name="suggestion:suggestion:edit">
					<th><spring:message code="admin.common.handle" /></th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="suggestion">
				<tr>
					<td>${suggestion.mobile}</td>
					<td>${suggestion.memberNickname}</td>
					<td><a
						href="${ctx}/suggestion/suggestion/form?id=${suggestion.id}">
							${suggestion.suggestionSubject.subjectTitle} </a></td>
					<td><fmt:formatDate value="${suggestion.createDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${suggestion.remarks}</td>
					<shiro:hasPermission name="suggestion:suggestion:edit">
						<td><a
							href="${ctx}/suggestion/suggestion/form?id=${suggestion.id}"><spring:message
									code="admin.common.modify" /></a> <a
							href="${ctx}/suggestion/suggestion/delete?id=${suggestion.id}"
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