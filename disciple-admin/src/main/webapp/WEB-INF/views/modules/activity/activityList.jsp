<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.activity" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/activity/activity/?searchType=${searchType}"><spring:message code="admin.activity"/><spring:message code="admin.common.list"/></a></li>
		<c:if test="${searchType ne 'selectLink' }">
		<shiro:hasPermission name="activity:activity:edit"><li><a href="${ctx}/activity/activity/form">
		   <spring:message code="admin.activity"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
		</c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="activity" action="${ctx}/activity/activity/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<input id="searchType" name="searchType" type="hidden" value="${searchType}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="Activity.name"/>：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li>
				<label> <spring:message code="Activity.title"/>：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
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
			<tr>				<c:if test="${searchType eq 'selectLink' }">
					<th><spring:message code="admin.common.choose" /></th>
				</c:if>
				<th> <spring:message code="Activity.name"/></th>
				<th> <spring:message code="Activity.title"/></th>
				<th> <spring:message code="Activity.address"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<shiro:hasPermission name="activity:activity:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="activity">
			<tr>
			<c:if test="${searchType eq 'selectLink' }">
						<td><c:choose>
								<c:when test="${isMultiple}">
									<input type="checkbox" name="id" value="${activity.id}"
										data-type="activity" data-activity-id="${activity.id}"
										data-activity-name="${activity.name}" data-activity-sn="${activity.sn}"/>
								</c:when>
								<c:otherwise>
									<input type="radio" name="id" value="${activity.id}"
										data-type="activity" data-activity-id="${activity.id}"
										data-activity-name="${activity.name}" data-activity-sn="${activity.sn}"/>
								</c:otherwise>
							</c:choose></td>
					</c:if>
					
				<td><a href="${ctx}/activity/activity/form?id=${activity.id}">
					${activity.name}
				</a></td>
				<td>
					${activity.title}
				</td>
				<td>
					${activity.address}
				</td>
				<td>
					<fmt:formatDate value="${activity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="activity:activity:edit"><td>
    				<a href="${ctx}/activity/activity/form?id=${activity.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/activity/activity/delete?id=${activity.id}" 
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