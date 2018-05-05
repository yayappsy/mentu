<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.department" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/base/department/"><spring:message code="admin.department"/><spring:message code="admin.common.list"/></a></li>
		<li><a href="${ctx}/base/department/form">
		   <spring:message code="admin.department"/><spring:message code="admin.common.add"/></a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="department" action="${ctx}/base/department/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="BaseDepartment.name"/>：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.search'/>"/>
			    <input id="btnClear" class="btn btn-primary" type="button" value="<spring:message code='admin.common.clear'/>"/>
			    <input id="btnDelete" data-url="${ctx}/base/department/deletes" class="btn btn-primary" type="submit" value="<spring:message code='批量删除'/>"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<weimhc:message resultMessage="${resultMessage}"/>		
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th> <input type="checkbox" id="selectAll"/></th>
				<th> <spring:message code="BaseDepartment.name"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasPermission name="base:department:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="department">
			<tr>
			<td>
					<input type="checkbox" name="ids" value="${department.id}"/>
				</td>
				<td><a href="${ctx}/base/department/form?id=${department.id}">
					${department.name}
				</a></td>
				<td>
					<fmt:formatDate value="${department.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${department.remarks}
				</td>
				<td>
    				<a href="${ctx}/base/department/form?id=${department.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/base/department/delete?id=${department.id}" 
					   onclick="return confirmx('<spring:message code="admin.dialog.deleteConfirm"/>', this.href)">
					   <spring:message code="admin.common.delete"/>
					</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
    <sys:pagination paginationSize="1" pageable="${page.pageable }"/>
</body>
</html>