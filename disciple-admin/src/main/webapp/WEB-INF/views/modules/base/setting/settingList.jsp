<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.setting" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/setting/setting/"><spring:message code="admin.setting.list"/></a></li>
		<shiro:hasPermission name="setting:setting:edit"><li><a href="${ctx}/setting/setting/form">
		   <spring:message code="admin.setting.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="setting" action="${ctx}/setting/setting/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="Setting.name"/>：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li>
				<label> <spring:message code="Setting.label"/>：</label>
				<form:input path="label" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th> <spring:message code="Setting.name"/></th>
				<th> <spring:message code="Setting.description"/></th>
				<th> <spring:message code="Setting.value"/></th>
				<shiro:hasPermission name="setting:setting:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="setting">
			<tr>
				<td><a href="${ctx}/setting/setting/form?id=${setting.id}">
					${setting.name}
				</a></td>
				<td>
					${setting.description}
				</td>
				<td>
					${setting.value}
				</td>
				<shiro:hasPermission name="setting:setting:edit"><td>
    				<a href="${ctx}/setting/setting/form?id=${setting.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/setting/setting/delete?id=${setting.id}" 
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