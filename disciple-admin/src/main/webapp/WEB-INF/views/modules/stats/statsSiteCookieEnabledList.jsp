<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.statsSiteCookieEnabled" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/stats/statsSiteCookieEnabled/"><spring:message code="admin.statsSiteCookieEnabled"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="stats:statsSiteCookieEnabled:edit"><li><a href="${ctx}/stats/statsSiteCookieEnabled/form">
		   <spring:message code="admin.statsSiteCookieEnabled"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="statsSiteCookieEnabled" action="${ctx}/stats/statsSiteCookieEnabled/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="StatsSiteCookieEnabled.cookieEnabled"/>：</label>
				<form:input path="cookieEnabled" htmlEscape="false" maxlength="1" class="input-medium"/>
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
				<th> <spring:message code="StatsSiteCookieEnabled.pageViewCount"/></th>
				<th> <spring:message code="StatsSiteCookieEnabled.visitorCount"/></th>
				<th> <spring:message code="StatsSiteCookieEnabled.newVisitorCount"/></th>
				<th> <spring:message code="StatsSiteCookieEnabled.newVisitorRate"/></th>
				<th> <spring:message code="StatsSiteCookieEnabled.browseCount"/></th>
				<th> <spring:message code="StatsSiteCookieEnabled.ipCount"/></th>
				<th> <spring:message code="StatsSiteCookieEnabled.bounceRate"/></th>
				<th> <spring:message code="StatsSiteCookieEnabled.averageAccessTime"/></th>
				<th> <spring:message code="StatsSiteCookieEnabled.cookieEnabled"/></th>
				<shiro:hasPermission name="stats:statsSiteCookieEnabled:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="statsSiteCookieEnabled">
			<tr>
				<td><a href="${ctx}/stats/statsSiteCookieEnabled/form?id=${statsSiteCookieEnabled.id}">
					${statsSiteCookieEnabled.pageViewCount}
				</a></td>
				<td>
					${statsSiteCookieEnabled.visitorCount}
				</td>
				<td>
					${statsSiteCookieEnabled.newVisitorCount}
				</td>
				<td>
					${statsSiteCookieEnabled.newVisitorRate}
				</td>
				<td>
					${statsSiteCookieEnabled.browseCount}
				</td>
				<td>
					${statsSiteCookieEnabled.ipCount}
				</td>
				<td>
					${statsSiteCookieEnabled.bounceRate}
				</td>
				<td>
					<fmt:formatDate value="${statsSiteCookieEnabled.averageAccessTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${statsSiteCookieEnabled.cookieEnabled}
				</td>
				<shiro:hasPermission name="stats:statsSiteCookieEnabled:edit"><td>
    				<a href="${ctx}/stats/statsSiteCookieEnabled/form?id=${statsSiteCookieEnabled.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/stats/statsSiteCookieEnabled/delete?id=${statsSiteCookieEnabled.id}" 
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