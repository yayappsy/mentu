<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.statsSiteIsp" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/stats/statsSiteIsp/"><spring:message code="admin.statsSiteIsp"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="stats:statsSiteIsp:edit"><li><a href="${ctx}/stats/statsSiteIsp/form">
		   <spring:message code="admin.statsSiteIsp"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="statsSiteIsp" action="${ctx}/stats/statsSiteIsp/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="StatsSiteIsp.isp"/>：</label>
				<form:input path="isp" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th> <spring:message code="StatsSiteIsp.pageViewCount"/></th>
				<th> <spring:message code="StatsSiteIsp.visitorCount"/></th>
				<th> <spring:message code="StatsSiteIsp.newVisitorCount"/></th>
				<th> <spring:message code="StatsSiteIsp.newVisitorRate"/></th>
				<th> <spring:message code="StatsSiteIsp.browseCount"/></th>
				<th> <spring:message code="StatsSiteIsp.ipCount"/></th>
				<th> <spring:message code="StatsSiteIsp.bounceRate"/></th>
				<th> <spring:message code="StatsSiteIsp.averageAccessTime"/></th>
				<th> <spring:message code="StatsSiteIsp.isp"/></th>
				<shiro:hasPermission name="stats:statsSiteIsp:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="statsSiteIsp">
			<tr>
				<td><a href="${ctx}/stats/statsSiteIsp/form?id=${statsSiteIsp.id}">
					${statsSiteIsp.pageViewCount}
				</a></td>
				<td>
					${statsSiteIsp.visitorCount}
				</td>
				<td>
					${statsSiteIsp.newVisitorCount}
				</td>
				<td>
					${statsSiteIsp.newVisitorRate}
				</td>
				<td>
					${statsSiteIsp.browseCount}
				</td>
				<td>
					${statsSiteIsp.ipCount}
				</td>
				<td>
					${statsSiteIsp.bounceRate}
				</td>
				<td>
					<fmt:formatDate value="${statsSiteIsp.averageAccessTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${statsSiteIsp.isp}
				</td>
				<shiro:hasPermission name="stats:statsSiteIsp:edit"><td>
    				<a href="${ctx}/stats/statsSiteIsp/form?id=${statsSiteIsp.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/stats/statsSiteIsp/delete?id=${statsSiteIsp.id}" 
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