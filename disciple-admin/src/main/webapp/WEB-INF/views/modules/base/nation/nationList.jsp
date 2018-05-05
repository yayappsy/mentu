<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.nation" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/base/nation/"><spring:message code="admin.nation.list"/></a></li>
		<shiro:hasPermission name="base:nation:edit"><li><a href="${ctx}/base/nation/form">
		   <spring:message code="admin.nation.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="nation" action="${ctx}/base/nation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="Nation.name"/>：</label>
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
				<th> <spring:message code="Nation.name"/></th>
				<th> <spring:message code="Nation.sort"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasAnyPermissions name="base:nation:edit,base:nation:delete">
				<th><spring:message code="admin.common.handle"/></th>
				</shiro:hasAnyPermissions>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="nation">
			<tr>
				<td><a href="${ctx}/base/nation/form?id=${nation.id}">
					${nation.name}
				</a></td>
				<td>
					${nation.sort}
				</td>
				<td>
					<fmt:formatDate value="${nation.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${nation.remarks}
				</td>
				<shiro:hasAnyPermissions name="base:nation:edit,base:nation:delete"><td>
    				<a href="${ctx}/base/nation/form?id=${nation.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/base/nation/delete?id=${nation.id}" 
					   onclick="return confirmx('<spring:message code="admin.dialog.deleteConfirm"/>', this.href)">
					   <spring:message code="admin.common.delete"/>
					</a>
				</td></shiro:hasAnyPermissions>
			</tr>
		</c:forEach>
		</tbody>
	</table>
    <sys:pagination paginationSize="1" pageable="${page.pageable }"/>
</body>
</html>