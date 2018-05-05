<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.statsSiteSource" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/stats/statsSiteSource/"><spring:message code="admin.statsSiteSource"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="stats:statsSiteSource:edit"><li><a href="${ctx}/stats/statsSiteSource/form">
		   <spring:message code="admin.statsSiteSource"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="statsSiteSource" action="${ctx}/stats/statsSiteSource/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="StatsSiteSource.deviceType"/>：</label>
				<form:input path="deviceType" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>
			<li>
				<label> <spring:message code="StatsSiteSource.ifNewVisitor"/>：</label>
				<form:input path="ifNewVisitor" htmlEscape="false" maxlength="1" class="input-medium"/>
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
				<th> <spring:message code="StatsSiteSource.pageViewCount"/></th>
				<th> <spring:message code="StatsSiteSource.visitorCount"/></th>
				<th> <spring:message code="StatsSiteSource.browseCount"/></th>
				<th> <spring:message code="StatsSiteSource.ipCount"/></th>
				<th> <spring:message code="StatsSiteSource.averageBrowsePageCount"/></th>
				<th> <spring:message code="StatsSiteSource.averageAccessTime"/></th>
				<th> <spring:message code="StatsSiteSource.referrer"/></th>
				<th> <spring:message code="StatsSiteSource.searchSite"/></th>
				<th> <spring:message code="StatsSiteSource.sourceType"/></th>
				<th> <spring:message code="StatsSiteSource.deviceType"/></th>
				<th> <spring:message code="StatsSiteSource.ifNewVisitor"/></th>
				<shiro:hasPermission name="stats:statsSiteSource:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="statsSiteSource">
			<tr>
				<td><a href="${ctx}/stats/statsSiteSource/form?id=${statsSiteSource.id}">
					${statsSiteSource.pageViewCount}
				</a></td>
				<td>
					${statsSiteSource.visitorCount}
				</td>
				<td>
					${statsSiteSource.browseCount}
				</td>
				<td>
					${statsSiteSource.ipCount}
				</td>
				<td>
					${statsSiteSource.averageBrowsePageCount}
				</td>
				<td>
					<fmt:formatDate value="${statsSiteSource.averageAccessTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${statsSiteSource.referrer}
				</td>
				<td>
					${statsSiteSource.searchSite}
				</td>
				<td>
					${statsSiteSource.sourceType}
				</td>
				<td>
					${statsSiteSource.deviceType}
				</td>
				<td>
					${statsSiteSource.ifNewVisitor}
				</td>
				<shiro:hasPermission name="stats:statsSiteSource:edit"><td>
    				<a href="${ctx}/stats/statsSiteSource/form?id=${statsSiteSource.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/stats/statsSiteSource/delete?id=${statsSiteSource.id}" 
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