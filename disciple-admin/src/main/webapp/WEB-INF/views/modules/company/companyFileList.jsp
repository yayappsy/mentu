<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.companyFile" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/company/companyFile/"><spring:message code="admin.companyFile"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="company:companyFile:edit"><li><a href="${ctx}/company/companyFile/form">
		   <spring:message code="admin.companyFile"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="companyFile" action="${ctx}/company/companyFile/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="CompanyFile.name"/>：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th> <spring:message code="CompanyFile.name"/></th>
				<th> <spring:message code="CompanyFile.url"/></th>
				<th> <spring:message code="CompanyFile.extension"/></th>
				<th> <spring:message code="CompanyFile.size"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasPermission name="company:companyFile:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="companyFile">
			<tr>
				<td><a href="${ctx}/company/companyFile/form?id=${companyFile.id}">
					${companyFile.name}
				</a></td>
				<td>
					${companyFile.url}
				</td>
				<td>
					${companyFile.extension}
				</td>
				<td>
					${companyFile.size}
				</td>
				<td>
					<fmt:formatDate value="${companyFile.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${companyFile.remarks}
				</td>
				<shiro:hasPermission name="company:companyFile:edit"><td>
    				<a href="${ctx}/company/companyFile/form?id=${companyFile.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/company/companyFile/delete?id=${companyFile.id}" 
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