<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.recruitment" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});
	</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/recruit/recruitment/"><spring:message code="admin.recruitment"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="recruit:recruitment:edit"><li><a href="${ctx}/recruit/recruitment/form">
		   <spring:message code="admin.recruitment"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="recruitment" action="${ctx}/recruit/recruitment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
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
			   <th> <spring:message code="Recruitment.jobTitle"/></th> 
			   <th><spring:message code="Recruitment.employType"/>  </th> 
			   <th><spring:message code="Recruitment.number"/></th> 
				<th> <spring:message code="Recruitment.releaseDate"/></th>        
				<th> <spring:message code="Recruitment.deadline"/></th>  
				<th> <spring:message code="Recruitment.isShow"/></th>    	
				<shiro:hasPermission name="recruit:recruitment:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="recruitment">
			<tr>
			<td><a href="${ctx}/recruit/recruitment/form?id=${recruitment.id}">
					${recruitment.jobTitle}
				</a></td>
				<td>
					${recruitment.employType.name}
				</td>
				<td>
					${recruitment.number}
				</td>
				<td>
					<fmt:formatDate value="${recruitment.releaseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${recruitment.deadline}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				    ${fns:getDictLabel(recruitment.isShow,'show_hide','')}
				</td>
				<shiro:hasPermission name="recruit:recruitment:edit"><td>
    				<a href="${ctx}/recruit/recruitment/form?id=${recruitment.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/recruit/recruitment/delete?id=${recruitment.id}" 
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