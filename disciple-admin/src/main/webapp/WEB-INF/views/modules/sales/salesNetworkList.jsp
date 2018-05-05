<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.salesNetwork" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@include file="/WEB-INF/views/include/treetable.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#treeTable").treeTable({
			expandLevel : 3
		}).show();
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sales/salesNetwork/"><spring:message
					code="admin.salesNetwork" /> <spring:message
					code="admin.common.list" /></a></li>
		<shiro:hasPermission name="sales:salesNetwork:edit">
			<li><a href="${ctx}/sales/salesNetwork/form"> <spring:message
						code="admin.salesNetwork" /> <spring:message
						code="admin.common.add" /></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="salesNetwork"
		action="${ctx}/sales/salesNetwork/" method="post"
		class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label> <spring:message code="SalesNetwork.name" />：
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
	<table id="treeTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><spring:message code="SalesNetwork.name" /></th>
				<th><spring:message code="SalesNetwork.phone" /></th>
				<th><spring:message code="SalesNetwork.detailedAddress" /></th>
				<th><spring:message code="DataEntity.updateDate" /></th>
				<shiro:hasPermission name="sales:salesNetwork:edit">
					<th><spring:message code="admin.common.handle" /></th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList">
			<c:forEach items="${list}" var="row">
				<tr id="${row.id}"
					pId="${row.parent.id ne '1'?product.parent.id:'0'}">
					<td><a href="${ctx}/sales/salesNetwork/form?id=${row.id}">
							${row.name} </a></td>
					<td>${row.phone}</td>
					<td>${row.detailedAddress}</td>
					<td><fmt:formatDate value="${row.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<shiro:hasPermission name="sales:salesNetwork:edit">
						<td><a href="${ctx}/sales/salesNetwork/form?id=${row.id}"><spring:message
									code="admin.common.modify" /></a> <a
							href="${ctx}/sales/salesNetwork/delete?id=${row.id}"
							onclick="return confirmx('<spring:message code="admin.dialog.deleteSubConfirm"/>', this.href)"><spring:message
									code="admin.common.delete" /></a> <a
							href="${ctx}/sales/salesNetwork/form?parent.id=${row.id}"><spring:message
									code="admin.dialog.addSub" /> <spring:message
									code="admin.salesNetwork" /></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>