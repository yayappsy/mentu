<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.statsSiteLanguage" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/stats/statsSiteLanguage/"><spring:message code="admin.statsSiteLanguage"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="stats:statsSiteLanguage:edit"><li><a href="${ctx}/stats/statsSiteLanguage/form">
		   <spring:message code="admin.statsSiteLanguage"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="statsSiteLanguage" action="${ctx}/stats/statsSiteLanguage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="StatsSiteLanguage.language"/>：</label>
				<form:input path="language" htmlEscape="false" maxlength="10" class="input-medium"/>
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
				<th> <spring:message code="StatsSiteLanguage.pageViewCount"/></th>
				<th> <spring:message code="StatsSiteLanguage.visitorCount"/></th>
				<th> <spring:message code="StatsSiteLanguage.newVisitorCount"/></th>
				<th> <spring:message code="StatsSiteLanguage.newVisitorRate"/></th>
				<th> <spring:message code="StatsSiteLanguage.browseCount"/></th>
				<th> <spring:message code="StatsSiteLanguage.ipCount"/></th>
				<th> <spring:message code="StatsSiteLanguage.bounceRate"/></th>
				<th> <spring:message code="StatsSiteLanguage.averageAccessTime"/></th>
				<th> <spring:message code="StatsSiteLanguage.language"/></th>
				<shiro:hasPermission name="stats:statsSiteLanguage:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="statsSiteLanguage">
			<tr>
				<td><a href="${ctx}/stats/statsSiteLanguage/form?id=${statsSiteLanguage.id}">
					${statsSiteLanguage.pageViewCount}
				</a></td>
				<td>
					${statsSiteLanguage.visitorCount}
				</td>
				<td>
					${statsSiteLanguage.newVisitorCount}
				</td>
				<td>
					${statsSiteLanguage.newVisitorRate}
				</td>
				<td>
					${statsSiteLanguage.browseCount}
				</td>
				<td>
					${statsSiteLanguage.ipCount}
				</td>
				<td>
					${statsSiteLanguage.bounceRate}
				</td>
				<td>
					<fmt:formatDate value="${statsSiteLanguage.averageAccessTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${statsSiteLanguage.language}
				</td>
				<shiro:hasPermission name="stats:statsSiteLanguage:edit"><td>
    				<a href="${ctx}/stats/statsSiteLanguage/form?id=${statsSiteLanguage.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/stats/statsSiteLanguage/delete?id=${statsSiteLanguage.id}" 
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