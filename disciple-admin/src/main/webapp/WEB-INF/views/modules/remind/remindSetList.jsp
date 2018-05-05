<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.remind" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(function(){

	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/remind/remind/setList"><spring:message
					code="admin.remind" /> <spring:message code="admin.common.list" /></a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="remind"
		action="${ctx}/remind/remind/setList" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden"
			value="${page.pageable.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageable.pageSize}" />
		<ul class="ul-form">
			<li><label> <spring:message code="Remind.name" />：
			</label> <form:input path="name" htmlEscape="false" maxlength="255"
					class="input-medium" /></li>
			<li><label> <spring:message code="Remind.targetType" />：
			</label> <form:select path="targetType">
					<form:option value="">
						<spring:message code="admin.common.defaultSelect" />
					</form:option>
					<c:forEach items="${targetTypes }" var="item">
						<form:option value="${item }"><spring:message code="Remind.TargetType.${item }" /></form:option>
					</c:forEach>
				</form:select></li>
			<li><label> <spring:message code="Remind.businessAction" />：
			</label> <form:select path="businessAction">
					<form:option value="">
						<spring:message code="admin.common.defaultSelect" />
					</form:option>
					<c:forEach items="${businessActions }" var="item">
						<form:option value="${item }"><spring:message code="Remind.BusinessAction.${item }" /></form:option>
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
				<th><spring:message code="Remind.name" /></th>
				<th><spring:message code="Remind.description" /></th>
				<th><spring:message code="Remind.targetType" /></th>
				<shiro:hasPermission name="remind:remind:edit">
					<th><spring:message code="admin.common.handle" /></th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="remind">
				<tr>
					<td><a href="${ctx}/remind/remind/form?id=${remind.id}">
							${remind.name} </a></td>
					<td>${remind.description}</td>
					<td><spring:message
							code="Remind.TargetType.${remind.targetType}" /></td>
					<shiro:hasPermission name="remind:remind:edit">
						<td><a href="${ctx}/remind/remind/setForm?id=${remind.id}"><spring:message
									code="admin.common.setting" /></a> </td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<sys:pagination paginationSize="1" pageable="${page.pageable }" />
</body>
</html>