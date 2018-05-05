<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.statsSiteOs" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/stats/statsSiteOs/"><spring:message code="admin.statsSiteOs"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="stats:statsSiteOs:edit"><li><a href="${ctx}/stats/statsSiteOs/form">
		   <spring:message code="admin.statsSiteOs"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="statsSiteOs" action="${ctx}/stats/statsSiteOs/" method="post" class="breadcrumb form-search">
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
				<th> <spring:message code="StatsSiteOs.pageViewCount"/></th>
				<th> <spring:message code="StatsSiteOs.visitorCount"/></th>
				<th> <spring:message code="StatsSiteOs.newVisitorCount"/></th>
				<th> <spring:message code="StatsSiteOs.newVisitorRate"/></th>
				<th> <spring:message code="StatsSiteOs.browseCount"/></th>
				<th> <spring:message code="StatsSiteOs.ipCount"/></th>
				<th> <spring:message code="StatsSiteOs.bounceRate"/></th>
				<th> <spring:message code="StatsSiteOs.averageAccessTime"/></th>
				<th> <spring:message code="StatsSiteOs.os"/></th>
				<th> <spring:message code="StatsSiteOs.deviceType"/></th>
				<shiro:hasPermission name="stats:statsSiteOs:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="statsSiteOs">
			<tr>
				<td><a href="${ctx}/stats/statsSiteOs/form?id=${statsSiteOs.id}">
					${statsSiteOs.pageViewCount}
				</a></td>
				<td>
					${statsSiteOs.visitorCount}
				</td>
				<td>
					${statsSiteOs.newVisitorCount}
				</td>
				<td>
					${statsSiteOs.newVisitorRate}
				</td>
				<td>
					${statsSiteOs.browseCount}
				</td>
				<td>
					${statsSiteOs.ipCount}
				</td>
				<td>
					${statsSiteOs.bounceRate}
				</td>
				<td>
					<fmt:formatDate value="${statsSiteOs.averageAccessTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${statsSiteOs.os}
				</td>
				<td>
					${statsSiteOs.deviceType}
				</td>
				<shiro:hasPermission name="stats:statsSiteOs:edit"><td>
    				<a href="${ctx}/stats/statsSiteOs/form?id=${statsSiteOs.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/stats/statsSiteOs/delete?id=${statsSiteOs.id}" 
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