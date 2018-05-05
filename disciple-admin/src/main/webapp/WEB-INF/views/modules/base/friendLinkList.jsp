<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.friendLink" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/base/friendLink/"><spring:message code="admin.friendLink.list"/></a></li>
		<shiro:hasPermission name="base:friendLink:edit"><li><a href="${ctx}/base/friendLink/form">
		   <spring:message code="admin.friendLink.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="friendLink" action="${ctx}/base/friendLink/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="FriendLink.name"/>：</label>
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
				<th> <spring:message code="FriendLink.name"/></th>
				<th> <spring:message code="FriendLink.describe"/></th>
				<th> <spring:message code="FriendLink.url"/></th>
				<th> <spring:message code="DataEntity.sort"/></th>       
				<shiro:hasPermission name="base:friendLink:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="friendLink">
			<tr>
				<td><a href="${ctx}/base/friendLink/form?id=${friendLink.id}">
					${friendLink.name}
				</a></td>
				<td>
					${friendLink.description}
				</td>
				<td>
					${friendLink.url}
				</td>
				<td>
					${friendLink.sort}
				</td>
				<shiro:hasPermission name="base:friendLink:edit"><td>
    				<a href="${ctx}/base/friendLink/form?id=${friendLink.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/base/friendLink/delete?id=${friendLink.id}" 
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