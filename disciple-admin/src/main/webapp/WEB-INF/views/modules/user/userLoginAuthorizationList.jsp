<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.userLoginAuthorization" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/user/userLoginAuthorization/"><spring:message code="admin.userLoginAuthorization"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="user:userLoginAuthorization:edit"><li><a href="${ctx}/user/userLoginAuthorization/form">
		   <spring:message code="admin.userLoginAuthorization"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="userLoginAuthorization" action="${ctx}/user/userLoginAuthorization/" method="post" class="breadcrumb form-search">
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
				<th> <spring:message code="UserLoginAuthorization.userInfoId"/></th>
				<th> <spring:message code="UserLoginAuthorization.businessSystem"/></th>
				<th> <spring:message code="UserLoginAuthorization.ifEnabled"/></th>
				<th> <spring:message code="UserLoginAuthorization.ifLocked"/></th>
				<th> <spring:message code="UserLoginAuthorization.lockedDate"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasAnyPermissions name="user:userLoginAuthorization:edit,user:userLoginAuthorization:delete">
					<th><spring:message code="admin.common.handle"/></th>
				</shiro:hasAnyPermissions>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="userLoginAuthorization">
			<tr>
				<td><a href="${ctx}/user/userLoginAuthorization/form?id=${userLoginAuthorization.id}">
					${userLoginAuthorization.userInfo.id}
				</a></td>
				<td>
					${userLoginAuthorization.businessSystem}
				</td>
				<td>
					${userLoginAuthorization.ifEnabled}
				</td>
				<td>
					${userLoginAuthorization.ifLocked}
				</td>
				<td>
					<fmt:formatDate value="${userLoginAuthorization.lockedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${userLoginAuthorization.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${userLoginAuthorization.remarks}
				</td>
				<shiro:hasAnyPermissions name="user:userLoginAuthorization:edit,user:userLoginAuthorization:delete">
					<td>
						<shiro:hasPermission name="user:userLoginAuthorization:edit">
	    					<a href="${ctx}/user/userLoginAuthorization/form?id=${userLoginAuthorization.id}"><spring:message code="admin.common.modify"/></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="user:userLoginAuthorization:delete">
							<a href="${ctx}/user/userLoginAuthorization/delete?id=${userLoginAuthorization.id}" 
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