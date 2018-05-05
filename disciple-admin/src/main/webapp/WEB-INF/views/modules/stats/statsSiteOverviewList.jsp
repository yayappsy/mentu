<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.statsSiteOverview" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/stats/statsSiteOverview/"><spring:message code="admin.statsSiteOverview"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="stats:statsSiteOverview:edit"><li><a href="${ctx}/stats/statsSiteOverview/form">
		   <spring:message code="admin.statsSiteOverview"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="statsSiteOverview" action="${ctx}/stats/statsSiteOverview/" method="post" class="breadcrumb form-search">
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
				<th> <spring:message code="StatsSiteOverview.pageViewCount"/></th>
				<th> <spring:message code="StatsSiteOverview.visitorCount"/></th>
				<th> <spring:message code="StatsSiteOverview.newVisitorCount"/></th>
				<th> <spring:message code="StatsSiteOverview.newVisitorRate"/></th>
				<th> <spring:message code="StatsSiteOverview.browseCount"/></th>
				<shiro:hasPermission name="stats:statsSiteOverview:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="statsSiteOverview">
			<tr>
				<td><a href="${ctx}/stats/statsSiteOverview/form?id=${statsSiteOverview.id}">
					${statsSiteOverview.pageViewCount}
				</a></td>
				<td>
					${statsSiteOverview.visitorCount}
				</td>
				<td>
					${statsSiteOverview.newVisitorCount}
				</td>
				<td>
					${statsSiteOverview.newVisitorRate}
				</td>
				<td>
					${statsSiteOverview.browseCount}
				</td>
				<shiro:hasPermission name="stats:statsSiteOverview:edit"><td>
    				<a href="${ctx}/stats/statsSiteOverview/form?id=${statsSiteOverview.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/stats/statsSiteOverview/delete?id=${statsSiteOverview.id}" 
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