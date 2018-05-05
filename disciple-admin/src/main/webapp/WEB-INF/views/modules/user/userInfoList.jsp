<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.userInfo" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/user/userInfo/"><spring:message code="admin.userInfo.list"/></a></li>
		<shiro:hasPermission name="user:userInfo:edit"><li><a href="${ctx}/user/userInfo/form">
		   <spring:message code="admin.userInfo.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="userInfo" action="${ctx}/user/userInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="UserInfo.name"/>：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li>
				<label> <spring:message code="UserInfo.nickname"/>：</label>
				<form:input path="nickname" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th> <spring:message code="UserInfo.sn"/></th>
				<th> <spring:message code="UserInfo.username"/></th>
				<th> <spring:message code="UserInfo.name"/></th>
				<th> <spring:message code="UserInfo.nickname"/></th>
				<th> <spring:message code="UserInfo.birth"/></th>
				<th> <spring:message code="UserInfo.email"/></th>
				<th> <spring:message code="UserInfo.gender"/></th>
				<th> <spring:message code="UserInfo.phone"/></th>
				<th> <spring:message code="UserInfo.mobile"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasPermission name="user:userInfo:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="userInfo">
			<tr>
				<td><a href="${ctx}/user/userInfo/form?id=${userInfo.id}">
					${userInfo.sn}
				</a></td>
				<td>
					${userInfo.username}
				</td>
				<td>
					${userInfo.name}
				</td>
				<td>
					${userInfo.nickname}
				</td>
				<td>
					<fmt:formatDate value="${userInfo.birth}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${userInfo.email}
				</td>
				<td>
					${fns:getDictLabel(userInfo.gender, '', '')}
				</td>
				<td>
					${userInfo.phone}
				</td>
				<td>
					${userInfo.mobile}
				</td>
				<td>
					<fmt:formatDate value="${userInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${userInfo.remarks}
				</td>
				<shiro:hasPermission name="user:userInfo:edit"><td>
    				<a href="${ctx}/user/userInfo/form?id=${userInfo.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/user/userInfo/delete?id=${userInfo.id}" 
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