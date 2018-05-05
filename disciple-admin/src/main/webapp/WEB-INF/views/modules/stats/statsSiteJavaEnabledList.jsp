<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.statsSiteJavaEnabled" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/stats/statsSiteJavaEnabled/"><spring:message code="admin.statsSiteJavaEnabled"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="stats:statsSiteJavaEnabled:edit"><li><a href="${ctx}/stats/statsSiteJavaEnabled/form">
		   <spring:message code="admin.statsSiteJavaEnabled"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="statsSiteJavaEnabled" action="${ctx}/stats/statsSiteJavaEnabled/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="StatsSiteJavaEnabled.javaEnabled"/>：</label>
				<form:input path="javaEnabled" htmlEscape="false" maxlength="1" class="input-medium"/>
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
				<th> <spring:message code="StatsSiteJavaEnabled.pageViewCount"/></th>
				<th> <spring:message code="StatsSiteJavaEnabled.visitorCount"/></th>
				<th> <spring:message code="StatsSiteJavaEnabled.newVisitorCount"/></th>
				<th> <spring:message code="StatsSiteJavaEnabled.newVisitorRate"/></th>
				<th> <spring:message code="StatsSiteJavaEnabled.browseCount"/></th>
				<th> <spring:message code="StatsSiteJavaEnabled.ipCount"/></th>
				<th> <spring:message code="StatsSiteJavaEnabled.bounceRate"/></th>
				<th> <spring:message code="StatsSiteJavaEnabled.averageAccessTime"/></th>
				<th> <spring:message code="StatsSiteJavaEnabled.javaEnabled"/></th>
				<shiro:hasPermission name="stats:statsSiteJavaEnabled:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="statsSiteJavaEnabled">
			<tr>
				<td><a href="${ctx}/stats/statsSiteJavaEnabled/form?id=${statsSiteJavaEnabled.id}">
					${statsSiteJavaEnabled.pageViewCount}
				</a></td>
				<td>
					${statsSiteJavaEnabled.visitorCount}
				</td>
				<td>
					${statsSiteJavaEnabled.newVisitorCount}
				</td>
				<td>
					${statsSiteJavaEnabled.newVisitorRate}
				</td>
				<td>
					${statsSiteJavaEnabled.browseCount}
				</td>
				<td>
					${statsSiteJavaEnabled.ipCount}
				</td>
				<td>
					${statsSiteJavaEnabled.bounceRate}
				</td>
				<td>
					<fmt:formatDate value="${statsSiteJavaEnabled.averageAccessTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${statsSiteJavaEnabled.javaEnabled}
				</td>
				<shiro:hasPermission name="stats:statsSiteJavaEnabled:edit"><td>
    				<a href="${ctx}/stats/statsSiteJavaEnabled/form?id=${statsSiteJavaEnabled.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/stats/statsSiteJavaEnabled/delete?id=${statsSiteJavaEnabled.id}" 
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