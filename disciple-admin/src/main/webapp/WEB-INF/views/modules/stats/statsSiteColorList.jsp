<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.statsSiteColor" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/stats/statsSiteColor/"><spring:message code="admin.statsSiteColor"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="stats:statsSiteColor:edit"><li><a href="${ctx}/stats/statsSiteColor/form">
		   <spring:message code="admin.statsSiteColor"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="statsSiteColor" action="${ctx}/stats/statsSiteColor/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="StatsSiteColor.colorDepth"/>：</label>
				<form:input path="colorDepth" htmlEscape="false" maxlength="11" class="input-medium"/>
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
				<th> <spring:message code="StatsSiteColor.pageViewCount"/></th>
				<th> <spring:message code="StatsSiteColor.visitorCount"/></th>
				<th> <spring:message code="StatsSiteColor.newVisitorCount"/></th>
				<th> <spring:message code="StatsSiteColor.newVisitorRate"/></th>
				<th> <spring:message code="StatsSiteColor.browseCount"/></th>
				<th> <spring:message code="StatsSiteColor.ipCount"/></th>
				<th> <spring:message code="StatsSiteColor.bounceRate"/></th>
				<th> <spring:message code="StatsSiteColor.averageAccessTime"/></th>
				<th> <spring:message code="StatsSiteColor.colorDepth"/></th>
				<shiro:hasPermission name="stats:statsSiteColor:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="statsSiteColor">
			<tr>
				<td><a href="${ctx}/stats/statsSiteColor/form?id=${statsSiteColor.id}">
					${statsSiteColor.pageViewCount}
				</a></td>
				<td>
					${statsSiteColor.visitorCount}
				</td>
				<td>
					${statsSiteColor.newVisitorCount}
				</td>
				<td>
					${statsSiteColor.newVisitorRate}
				</td>
				<td>
					${statsSiteColor.browseCount}
				</td>
				<td>
					${statsSiteColor.ipCount}
				</td>
				<td>
					${statsSiteColor.bounceRate}
				</td>
				<td>
					<fmt:formatDate value="${statsSiteColor.averageAccessTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${statsSiteColor.colorDepth}
				</td>
				<shiro:hasPermission name="stats:statsSiteColor:edit"><td>
    				<a href="${ctx}/stats/statsSiteColor/form?id=${statsSiteColor.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/stats/statsSiteColor/delete?id=${statsSiteColor.id}" 
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