<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.statsSiteBrowser" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/stats/statsSiteBrowser/"><spring:message code="admin.statsSiteBrowser"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="stats:statsSiteBrowser:edit"><li><a href="${ctx}/stats/statsSiteBrowser/form">
		   <spring:message code="admin.statsSiteBrowser"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="statsSiteBrowser" action="${ctx}/stats/statsSiteBrowser/" method="post" class="breadcrumb form-search">
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
				<th> <spring:message code="StatsSiteBrowser.pageViewCount"/></th>
				<th> <spring:message code="StatsSiteBrowser.visitorCount"/></th>
				<th> <spring:message code="StatsSiteBrowser.newVisitorCount"/></th>
				<th> <spring:message code="StatsSiteBrowser.newVisitorRate"/></th>
				<th> <spring:message code="StatsSiteBrowser.browseCount"/></th>
				<th> <spring:message code="StatsSiteBrowser.ipCount"/></th>
				<th> <spring:message code="StatsSiteBrowser.bounceRate"/></th>
				<th> <spring:message code="StatsSiteBrowser.conversionsCount"/></th>
				<th> <spring:message code="StatsSiteBrowser.averageAccessTime"/></th>
				<th> <spring:message code="StatsSiteBrowser.browserName"/></th>
				<th> <spring:message code="StatsSiteBrowser.browserType"/></th>
				<shiro:hasPermission name="stats:statsSiteBrowser:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="statsSiteBrowser">
			<tr>
				<td><a href="${ctx}/stats/statsSiteBrowser/form?id=${statsSiteBrowser.id}">
					${statsSiteBrowser.pageViewCount}
				</a></td>
				<td>
					${statsSiteBrowser.visitorCount}
				</td>
				<td>
					${statsSiteBrowser.newVisitorCount}
				</td>
				<td>
					${statsSiteBrowser.newVisitorRate}
				</td>
				<td>
					${statsSiteBrowser.browseCount}
				</td>
				<td>
					${statsSiteBrowser.ipCount}
				</td>
				<td>
					${statsSiteBrowser.bounceRate}
				</td>
				<td>
					${statsSiteBrowser.conversionsCount}
				</td>
				<td>
					<fmt:formatDate value="${statsSiteBrowser.averageAccessTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${statsSiteBrowser.browserName}
				</td>
				<td>
					${statsSiteBrowser.browserType}
				</td>
				<shiro:hasPermission name="stats:statsSiteBrowser:edit"><td>
    				<a href="${ctx}/stats/statsSiteBrowser/form?id=${statsSiteBrowser.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/stats/statsSiteBrowser/delete?id=${statsSiteBrowser.id}" 
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