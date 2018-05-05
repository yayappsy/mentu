<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.remindWay" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/remind/remindWay/"><spring:message code="admin.remindWay"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="remind:remindWay:edit"><li><a href="${ctx}/remind/remindWay/form">
		   <spring:message code="admin.remindWay"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="remindWay" action="${ctx}/remind/remindWay/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="RemindWay.name"/>：</label>
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
				<th> <spring:message code="RemindWay.name"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasPermission name="remind:remindWay:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="remindWay">
			<tr>
				<td><a href="${ctx}/remind/remindWay/form?id=${remindWay.id}">
					${remindWay.name}
				</a></td>
				<td>
					<fmt:formatDate value="${remindWay.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${remindWay.remarks}
				</td>
				<shiro:hasPermission name="remind:remindWay:edit"><td>
    				<a href="${ctx}/remind/remindWay/form?id=${remindWay.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/remind/remindWay/delete?id=${remindWay.id}" 
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