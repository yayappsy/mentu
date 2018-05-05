<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.adPosition" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ad/adPosition/"><spring:message code="admin.adPosition"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="ad:adPosition:edit"><li><a href="${ctx}/ad/adPosition/form">
		   <spring:message code="admin.adPosition"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="adPosition" action="${ctx}/ad/adPosition/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="AdPosition.name"/>：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th> <spring:message code="AdPosition.name"/></th>
				<th> <spring:message code="AdPosition.description"/></th>
				<th> <spring:message code="AdPosition.adType"/></th>
				<shiro:hasPermission name="ad:adPosition:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="adPosition">
			<tr>
				<td><a href="${ctx}/ad/adPosition/form?id=${adPosition.id}">
					${adPosition.name}
				</a></td>
				<td>
					${adPosition.description}
				</td>
				<td>
					${adPosition.adType}
				</td>
				<shiro:hasPermission name="ad:adPosition:edit"><td>
    				<a href="${ctx}/ad/adPosition/form?id=${adPosition.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/ad/adPosition/delete?id=${adPosition.id}" 
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