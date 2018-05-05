<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.onlineCustomerService" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/base/onlineCustomerService/"><spring:message code="admin.onlineCustomerService"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="base:onlineCustomerService:edit"><li><a href="${ctx}/base/onlineCustomerService/form">
		   <spring:message code="admin.onlineCustomerService"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="onlineCustomerService" action="${ctx}/base/onlineCustomerService/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="OnlineCustomerService.name"/>：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th> <spring:message code="OnlineCustomerService.name"/></th>
				<th> <spring:message code="OnlineCustomerService.type"/></th>
				<th> <spring:message code="OnlineCustomerService.account"/></th>
				<th> <spring:message code="DataEntity.isShow"/></th>        
				<th> <spring:message code="DataEntity.sort"/></th>        
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<shiro:hasPermission name="base:onlineCustomerService:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="onlineCustomerService">
			<tr>
				<td><a href="${ctx}/base/onlineCustomerService/form?id=${onlineCustomerService.id}">
					${onlineCustomerService.name}
				</a></td>
				<td>
					${onlineCustomerService.type}
				</td>
				<td>
					${onlineCustomerService.account}
				</td>
				<td>
					${fns:getDictLabel(onlineCustomerService.isShow,'true_false','')}
				</td>
				<td>
					${onlineCustomerService.sort}
				</td>
				<td>
					<fmt:formatDate value="${onlineCustomerService.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="base:onlineCustomerService:edit"><td>
    				<a href="${ctx}/base/onlineCustomerService/form?id=${onlineCustomerService.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/base/onlineCustomerService/delete?id=${onlineCustomerService.id}" 
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