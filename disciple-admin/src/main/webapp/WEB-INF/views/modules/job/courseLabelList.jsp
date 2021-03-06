<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="课程标签"/><spring:message code="admin.common.manager"/></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/job/courseLabel/"><spring:message code="课程标签"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="job:courseLabel:edit"><li><a href="${ctx}/job/courseLabel/form">
		   <spring:message code="课程标签"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="courseLabel" action="${ctx}/job/courseLabel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="名称"/>：</label>
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
				<th> <spring:message code="名称"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasAnyPermissions name="job:courseLabel:edit,job:courseLabel:delete">
					<th><spring:message code="admin.common.handle"/></th>
				</shiro:hasAnyPermissions>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="courseLabel">
			<tr>
				<td><a href="${ctx}/job/courseLabel/form?id=${courseLabel.id}">
					${courseLabel.name}
				</a></td>
				<td>
					<fmt:formatDate value="${courseLabel.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${courseLabel.remarks}
				</td>
				<shiro:hasAnyPermissions name="job:courseLabel:edit,job:courseLabel:delete">
					<td>
						<shiro:hasPermission name="job:courseLabel:edit">
	    					<a href="${ctx}/job/courseLabel/form?id=${courseLabel.id}"><spring:message code="admin.common.modify"/></a>
						</shiro:hasPermission>
							<a href="${ctx}/job/courseLabel/delete?id=${courseLabel.id}"
							   onclick="return confirmx('<spring:message code="admin.dialog.deleteConfirm"/>', this.href)">
							   <spring:message code="admin.common.delete"/>
							</a>
					</td>
				</shiro:hasAnyPermissions>
			</tr>
		</c:forEach>
		</tbody>
	</table>
    <sys:pagination paginationSize="1" pageable="${page.pageable }"/>
</body>
</html>