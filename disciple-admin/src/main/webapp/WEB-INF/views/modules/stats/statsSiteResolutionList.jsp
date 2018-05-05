<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.statsSiteResolution" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/stats/statsSiteResolution/"><spring:message code="admin.statsSiteResolution"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="stats:statsSiteResolution:edit"><li><a href="${ctx}/stats/statsSiteResolution/form">
		   <spring:message code="admin.statsSiteResolution"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="statsSiteResolution" action="${ctx}/stats/statsSiteResolution/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="StatsSiteResolution.resolution"/>：</label>
				<form:input path="resolution" htmlEscape="false" maxlength="20" class="input-medium"/>
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
				<th> <spring:message code="StatsSiteResolution.pageViewCount"/></th>
				<th> <spring:message code="StatsSiteResolution.visitorCount"/></th>
				<th> <spring:message code="StatsSiteResolution.newVisitorCount"/></th>
				<th> <spring:message code="StatsSiteResolution.newVisitorRate"/></th>
				<th> <spring:message code="StatsSiteResolution.browseCount"/></th>
				<th> <spring:message code="StatsSiteResolution.ipCount"/></th>
				<th> <spring:message code="StatsSiteResolution.bounceRate"/></th>
				<th> <spring:message code="StatsSiteResolution.conversionsCount"/></th>
				<th> <spring:message code="StatsSiteResolution.averageAccessTime"/></th>
				<th> <spring:message code="StatsSiteResolution.resolution"/></th>
				<shiro:hasPermission name="stats:statsSiteResolution:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="statsSiteResolution">
			<tr>
				<td><a href="${ctx}/stats/statsSiteResolution/form?id=${statsSiteResolution.id}">
					${statsSiteResolution.pageViewCount}
				</a></td>
				<td>
					${statsSiteResolution.visitorCount}
				</td>
				<td>
					${statsSiteResolution.newVisitorCount}
				</td>
				<td>
					${statsSiteResolution.newVisitorRate}
				</td>
				<td>
					${statsSiteResolution.browseCount}
				</td>
				<td>
					${statsSiteResolution.ipCount}
				</td>
				<td>
					${statsSiteResolution.bounceRate}
				</td>
				<td>
					${statsSiteResolution.conversionsCount}
				</td>
				<td>
					<fmt:formatDate value="${statsSiteResolution.averageAccessTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${statsSiteResolution.resolution}
				</td>
				<shiro:hasPermission name="stats:statsSiteResolution:edit"><td>
    				<a href="${ctx}/stats/statsSiteResolution/form?id=${statsSiteResolution.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/stats/statsSiteResolution/delete?id=${statsSiteResolution.id}" 
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