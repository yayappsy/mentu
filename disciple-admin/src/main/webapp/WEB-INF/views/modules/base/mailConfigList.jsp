<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.mailConfig" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/base/mailConfig/"><spring:message code="admin.mailConfig"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="base:mailConfig:edit"><li><a href="${ctx}/base/mailConfig/form">
		   <spring:message code="admin.mailConfig"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mailConfig" action="${ctx}/base/mailConfig/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="MailConfig.address"/>：</label>
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li>
				<label> <spring:message code="MailConfig.displayName"/>：</label>
				<form:input path="displayName" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th> <spring:message code="MailConfig.address"/></th>
				<th> <spring:message code="MailConfig.displayName"/></th>
				<th> <spring:message code="MailConfig.pop3Address"/></th>
				<th> <spring:message code="MailConfig.smtpAddress"/></th>
				<th> <spring:message code="MailConfig.imapAddress"/></th>
				<shiro:hasPermission name="base:mailConfig:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mailConfig">
			<tr>
				<td><a href="${ctx}/base/mailConfig/form?id=${mailConfig.id}">
					${mailConfig.address}
				</a></td>
				<td>
					${mailConfig.displayName}
				</td>
				<td>
					${mailConfig.pop3Address}
				</td>
				<td>
					${mailConfig.smtpAddress}
				</td>
				<td>
					${mailConfig.imapAddress}
				</td>
				<shiro:hasPermission name="base:mailConfig:edit"><td>
    				<a href="${ctx}/base/mailConfig/form?id=${mailConfig.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/base/mailConfig/delete?id=${mailConfig.id}" 
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