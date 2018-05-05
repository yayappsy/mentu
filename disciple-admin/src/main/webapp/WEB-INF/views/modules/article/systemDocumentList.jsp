<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.systemDocument" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/article/systemDocument/"><spring:message code="admin.systemDocument.list"/></a></li>
		<shiro:hasPermission name="article:systemDocument:edit"><li><a href="${ctx}/article/systemDocument/form">
		   <spring:message code="admin.systemDocument.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="systemDocument" action="${ctx}/article/systemDocument/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="SystemDocument.title"/>：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.search'/>"/>
			    <input id="btnClear" class="btn btn-primary" type="button" value="<spring:message code='admin.common.clear'/>"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<weimhc:message resultMessage="${message}"/>		
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th> <spring:message code="SystemDocument.title"/></th>
				<th> <spring:message code="SystemDocument.code"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasPermission name="article:systemDocument:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="systemDocument">
			<tr>
				<td><a href="${ctx}/article/systemDocument/form?id=${systemDocument.id}">
					${systemDocument.title}
				</a></td>
				<td>
					${systemDocument.code}
				</td>
				<td>
					<fmt:formatDate value="${systemDocument.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${systemDocument.remarks}
				</td>
				<shiro:hasPermission name="article:systemDocument:edit"><td>
    				<a href="${ctx}/article/systemDocument/form?id=${systemDocument.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/article/systemDocument/delete?id=${systemDocument.id}" 
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