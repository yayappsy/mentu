<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.statsVisitDistrict" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/stats/statsVisitDistrict/"><spring:message code="admin.statsVisitDistrict"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="stats:statsVisitDistrict:edit"><li><a href="${ctx}/stats/statsVisitDistrict/form">
		   <spring:message code="admin.statsVisitDistrict"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="statsVisitDistrict" action="${ctx}/stats/statsVisitDistrict/" method="post" class="breadcrumb form-search">
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
				<th> <spring:message code="StatsVisitDistrict.pageViewCount"/></th>
				<th> <spring:message code="StatsVisitDistrict.visitorCount"/></th>
				<th> <spring:message code="StatsVisitDistrict.newVisitorCount"/></th>
				<th> <spring:message code="StatsVisitDistrict.newVisitorRate"/></th>
				<th> <spring:message code="StatsVisitDistrict.browseCount"/></th>
				<th> <spring:message code="StatsVisitDistrict.country"/></th>
				<th> <spring:message code="StatsVisitDistrict.province"/></th>
				<shiro:hasPermission name="stats:statsVisitDistrict:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="statsVisitDistrict">
			<tr>
				<td><a href="${ctx}/stats/statsVisitDistrict/form?id=${statsVisitDistrict.id}">
					${statsVisitDistrict.pageViewCount}
				</a></td>
				<td>
					${statsVisitDistrict.visitorCount}
				</td>
				<td>
					${statsVisitDistrict.newVisitorCount}
				</td>
				<td>
					${statsVisitDistrict.newVisitorRate}
				</td>
				<td>
					${statsVisitDistrict.browseCount}
				</td>
				<td>
					${statsVisitDistrict.country}
				</td>
				<td>
					${statsVisitDistrict.province}
				</td>
				<shiro:hasPermission name="stats:statsVisitDistrict:edit"><td>
    				<a href="${ctx}/stats/statsVisitDistrict/form?id=${statsVisitDistrict.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/stats/statsVisitDistrict/delete?id=${statsVisitDistrict.id}" 
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