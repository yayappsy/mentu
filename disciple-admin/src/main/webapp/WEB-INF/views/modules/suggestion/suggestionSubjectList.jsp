<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.suggestionSubject" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/suggestion/suggestionSubject/"><spring:message code="admin.suggestionSubject.list"/></a></li>
		<shiro:hasPermission name="suggestion:suggestionSubject:edit"><li><a href="${ctx}/suggestion/suggestionSubject/form">
		   <spring:message code="admin.suggestionSubject.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="suggestionSubject" action="${ctx}/suggestion/suggestionSubject/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="SuggestionSubject.subjectTitle"/>：</label>
				<form:input path="subjectTitle" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th> <spring:message code="SuggestionSubject.subjectTitle"/></th>
				<th> <spring:message code="SuggestionSubject.isShow"/></th>
				<th> <spring:message code="SuggestionSubject.sort"/></th>
				<th> <spring:message code="DataEntity.createDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasPermission name="suggestion:suggestionSubject:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="suggestionSubject">
			<tr>
				<td><a href="${ctx}/suggestion/suggestionSubject/form?id=${suggestionSubject.id}">
					${suggestionSubject.subjectTitle}
				</a></td>
				<td>
					${fns:getDictLabel(suggestionSubject.isShow,'true_false','')}
				</td>
				<td>
					${suggestionSubject.sort}
				</td>
				<td>
					<fmt:formatDate value="${suggestionSubject.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${suggestionSubject.remarks}
				</td>
				<shiro:hasPermission name="suggestion:suggestionSubject:edit"><td>
    				<a href="${ctx}/suggestion/suggestionSubject/form?id=${suggestionSubject.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/suggestion/suggestionSubject/delete?id=${suggestionSubject.id}" 
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