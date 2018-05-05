<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.salesNetworkType" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sales/salesNetworkType/"><spring:message code="admin.salesNetworkType"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="sales:salesNetworkType:edit"><li><a href="${ctx}/sales/salesNetworkType/form">
		   <spring:message code="admin.salesNetworkType"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="salesNetworkType" action="${ctx}/sales/salesNetworkType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="SalesNetworkType.name"/>：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.search'/>"/>
			    <input id="btnClear" class="btn btn-primary" type="button" value="<spring:message code='admin.common.clear'/>"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<weimhc:message resultMessage="${resultMessage}"/>		
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th> <spring:message code="SalesNetworkType.name"/></th>
				<th> <spring:message code="SalesNetworkType.description"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<shiro:hasPermission name="sales:salesNetworkType:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="salesNetworkType">
			<tr>
				<td><a href="${ctx}/sales/salesNetworkType/form?id=${salesNetworkType.id}">
					${salesNetworkType.name}
				</a></td>
				<td>
					${salesNetworkType.description}
				</td>
				<td>
					<fmt:formatDate value="${salesNetworkType.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="sales:salesNetworkType:edit"><td>
    				<a href="${ctx}/sales/salesNetworkType/form?id=${salesNetworkType.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/sales/salesNetworkType/delete?id=${salesNetworkType.id}" 
					   onclick="return confirmx('<spring:message code="admin.dialog.deleteConfirm"/>', this.href)">
					   <spring:message code="admin.common.delete"/>
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
    <sys:pagination paginationSize="1" pageable="${page.pageable }"/>
</body>
</html>