<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.remindParameter" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(function(){

	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/remind/remindParameter/"><spring:message
					code="admin.remindParameter" /> <spring:message
					code="admin.common.list" /></a></li>
		<shiro:hasPermission name="remind:remindParameter:edit">
			<li><a href="${ctx}/remind/remindParameter/form"> <spring:message
						code="admin.remindParameter" /> <spring:message
						code="admin.common.add" /></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="remindParameter"
		action="${ctx}/remind/remindParameter/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden"
			value="${page.pageable.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageable.pageSize}" />
		<ul class="ul-form">
			<li><label> <spring:message code="RemindParameter.name" />：
			</label> <form:input path="name" htmlEscape="false" maxlength="255"
					class="input-medium" /></li>
			<li><label> <spring:message
						code="RemindParameter.entityName" />：
			</label> <form:select path="entityName">
					<form:option value="">
						<spring:message code="admin.common.defaultSelect" />
					</form:option>
					<c:forEach items="${entityNames }" var="entityName">
						<form:option value="${entityName }">${entityName }</form:option>
					</c:forEach>
				</form:select></li>
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
				<th><spring:message code="RemindParameter.name" /></th>
				<th><spring:message code="RemindParameter.description" /></th>
				<th><spring:message code="RemindParameter.entityName" /></th>
				<th><spring:message code="RemindParameter.propertyName" /></th>
				<th><spring:message code="DataEntity.updateDate" /></th>
				<shiro:hasPermission name="remind:remindParameter:edit">
					<th><spring:message code="admin.common.handle" /></th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="remindParameter">
				<tr>
					<td><a
						href="${ctx}/remind/remindParameter/form?id=${remindParameter.id}">
							${remindParameter.name} </a></td>
					<td>${remindParameter.description}</td>
					<td>${remindParameter.entityName}</td>
					<td>${remindParameter.propertyName}</td>
					<td><fmt:formatDate value="${remindParameter.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<shiro:hasPermission name="remind:remindParameter:edit">
						<td><a
							href="${ctx}/remind/remindParameter/form?id=${remindParameter.id}"><spring:message
									code="admin.common.modify" /></a> <a
							href="${ctx}/remind/remindParameter/delete?id=${remindParameter.id}"
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