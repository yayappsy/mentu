<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.userHealthInfo" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/user/userHealthInfo/"><spring:message code="admin.userHealthInfo"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="user:userHealthInfo:edit"><li><a href="${ctx}/user/userHealthInfo/form">
		   <spring:message code="admin.userHealthInfo"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="userHealthInfo" action="${ctx}/user/userHealthInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
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
				<shiro:hasAnyPermissions name="user:userHealthInfo:edit,user:userHealthInfo:delete">
					<th><spring:message code="admin.common.handle"/></th>
				</shiro:hasAnyPermissions>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="userHealthInfo">
			<tr>
				<shiro:hasAnyPermissions name="user:userHealthInfo:edit,user:userHealthInfo:delete">
					<td>
						<shiro:hasPermission name="user:userHealthInfo:edit">
	    					<a href="${ctx}/user/userHealthInfo/form?id=${userHealthInfo.id}"><spring:message code="admin.common.modify"/></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="user:userHealthInfo:delete">
							<a href="${ctx}/user/userHealthInfo/delete?id=${userHealthInfo.id}" 
							   onclick="return confirmx('<spring:message code="admin.dialog.deleteConfirm"/>', this.href)">
							   <spring:message code="admin.common.delete"/>
							</a>
						</shiro:hasPermission>
					</td>
				</shiro:hasAnyPermissions>
			</tr>
		</c:forEach>
		</tbody>
	</table>
    <sys:pagination paginationSize="1" pageable="${page.pageable }"/>
</body>
</html>