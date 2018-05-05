<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.statsSiteSearchKeywords" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/stats/statsSiteSearchKeywords/"><spring:message code="admin.statsSiteSearchKeywords"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="stats:statsSiteSearchKeywords:edit"><li><a href="${ctx}/stats/statsSiteSearchKeywords/form">
		   <spring:message code="admin.statsSiteSearchKeywords"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="statsSiteSearchKeywords" action="${ctx}/stats/statsSiteSearchKeywords/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="StatsSiteSearchKeywords.searchKeywords"/>：</label>
				<form:input path="searchKeywords" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li>
				<label> <spring:message code="StatsSiteSearchKeywords.deviceType"/>：</label>
				<form:input path="deviceType" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>
			<li>
				<label> <spring:message code="StatsSiteSearchKeywords.ifNewVisitor"/>：</label>
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
				<th> <spring:message code="StatsSiteSearchKeywords.pageViewCount"/></th>
				<th> <spring:message code="StatsSiteSearchKeywords.visitorCount"/></th>
				<th> <spring:message code="StatsSiteSearchKeywords.browseCount"/></th>
				<th> <spring:message code="StatsSiteSearchKeywords.ipCount"/></th>
				<th> <spring:message code="StatsSiteSearchKeywords.averageBrowsePageCount"/></th>
				<th> <spring:message code="StatsSiteSearchKeywords.averageAccessTime"/></th>
				<th> <spring:message code="StatsSiteSearchKeywords.searchKeywords"/></th>
				<th> <spring:message code="StatsSiteSearchKeywords.deviceType"/></th>
				<th> <spring:message code="StatsSiteSearchKeywords.ifNewVisitor"/></th>
				<shiro:hasPermission name="stats:statsSiteSearchKeywords:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="statsSiteSearchKeywords">
			<tr>
				<td><a href="${ctx}/stats/statsSiteSearchKeywords/form?id=${statsSiteSearchKeywords.id}">
					${statsSiteSearchKeywords.pageViewCount}
				</a></td>
				<td>
					${statsSiteSearchKeywords.visitorCount}
				</td>
				<td>
					${statsSiteSearchKeywords.browseCount}
				</td>
				<td>
					${statsSiteSearchKeywords.ipCount}
				</td>
				<td>
					${statsSiteSearchKeywords.averageBrowsePageCount}
				</td>
				<td>
					${statsSiteSearchKeywords.averageAccessTime}
				</td>
				<td>
					${statsSiteSearchKeywords.searchKeywords}
				</td>
				<td>
					${statsSiteSearchKeywords.deviceType}
				</td>
				<td>
					${statsSiteSearchKeywords.ifNewVisitor}
				</td>
				<shiro:hasPermission name="stats:statsSiteSearchKeywords:edit"><td>
    				<a href="${ctx}/stats/statsSiteSearchKeywords/form?id=${statsSiteSearchKeywords.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/stats/statsSiteSearchKeywords/delete?id=${statsSiteSearchKeywords.id}" 
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