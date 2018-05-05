<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.inquirySheet" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inquiry/inquirySheet/"><spring:message code="admin.inquirySheet"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="inquiry:inquirySheet:edit"><li><a href="${ctx}/inquiry/inquirySheet/form">
		   <spring:message code="admin.inquirySheet"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="inquirySheet" action="${ctx}/inquiry/inquirySheet/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="InquirySheet.name"/>：</label>
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
				<th> <spring:message code="InquirySheet.name"/></th>
				<th> <spring:message code="InquirySheet.memberNickname"/></th>
				<th> <spring:message code="InquirySheet.email"/></th>
				<th> <spring:message code="InquirySheet.mobile"/></th>
				<th> <spring:message code="InquirySheet.status"/></th>
				<th> <spring:message code="InquirySheet.source"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<shiro:hasPermission name="inquiry:inquirySheet:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="inquirySheet">
			<tr>
				<td><a href="${ctx}/inquiry/inquirySheet/form?id=${inquirySheet.id}">
					${inquirySheet.name}
				</a></td>
				<td>
					${inquirySheet.memberNickname}
				</td>
				<td>
					${inquirySheet.email}
				</td>
				<td>
					${inquirySheet.mobile}
				</td>
				<td>
					<spring:message code="InquiryStatus.${inquirySheet.status}"/>
				</td>
				<td>
					<spring:message code="InquirySource.${inquirySheet.source}"/>
				</td>
				<td>
					<fmt:formatDate value="${inquirySheet.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			
				<shiro:hasPermission name="inquiry:inquirySheet:edit"><td>
				    <a href="${ctx}/inquiry/inquirySheet/view?id=${inquirySheet.id}"><spring:message code="admin.common.audit"/></a>
    				<c:if test="${inquirySheet.status eq 'waiting' and inquirySheet.source eq 'admin'}">
    				    <a href="${ctx}/inquiry/inquirySheet/form?id=${inquirySheet.id}"><spring:message code="admin.common.modify"/></a>
					</c:if>
					<a href="${ctx}/inquiry/inquirySheet/delete?id=${inquirySheet.id}" 
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