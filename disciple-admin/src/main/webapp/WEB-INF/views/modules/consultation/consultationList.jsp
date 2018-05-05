<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.consultation" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(function(){

	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/consultation/consultation/"><spring:message
					code="admin.consultation.list" /></a></li>
		<shiro:hasPermission name="consultation:consultation:edit">
			<li><a href="${ctx}/consultation/consultation/form"> <spring:message
						code="admin.consultation.add" /></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="consultation"
		action="${ctx}/consultation/consultation/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden"
			value="${page.pageable.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageable.pageSize}" />
		<ul class="ul-form">
			<li><label> <spring:message
						code="Consultation.consultant" />：
			</label> <form:input path="consultant.nickname" htmlEscape="false" maxlength="64"
					class="input-medium" /></li>
			<li><label> <spring:message
						code="Consultation.consultationType" />：
			</label> <form:select path="consultationType">
					<form:options items="${consultationTypeList}" itemLabel="name"
						itemValue="id" />
				</form:select>
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
				<th><spring:message code="Consultation.consultant" /></th>
				<th><spring:message code="Consultation.consultationType" /></th>
				<th><spring:message code="DataEntity.updateDate" /></th>
				<th><spring:message code="DataEntity.remarks" /></th>
				<shiro:hasPermission name="consultation:consultation:edit">
					<th><spring:message code="admin.common.handle" /></th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="consultation">
				<tr>
					<td><a
						href="${ctx}/consultation/consultation/form?id=${consultation.id}">
							${consultation.consultant.nickname} </a></td>
					<td>${consultation.consultationType.name}</td>
					<td><fmt:formatDate value="${consultation.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${consultation.remarks}</td>
					<shiro:hasPermission name="consultation:consultation:edit">
						<td><a
							href="${ctx}/consultation/consultation/form?id=${consultation.id}"><spring:message
									code="admin.common.modify" /></a> <a
							href="${ctx}/consultation/consultation/delete?id=${consultation.id}"
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