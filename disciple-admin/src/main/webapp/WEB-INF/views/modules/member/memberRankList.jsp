<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.memberRank" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/memberRank/"><spring:message code="admin.memberRank.list"/></a></li>
		<shiro:hasPermission name="member:memberRank:edit"><li><a href="${ctx}/member/memberRank/form">
		   <spring:message code="admin.memberRank.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="memberRank" action="${ctx}/member/memberRank/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="MemberRank.name"/>：</label>
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
				<th> <spring:message code="MemberRank.name"/></th>
				<th> <spring:message code="MemberRank.isDefault"/></th>
				<th> <spring:message code="MemberRank.isSpecial"/></th>
			
				<th> <spring:message code="DataEntity.createDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasPermission name="member:memberRank:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="memberRank">
			<tr>
			<td><a href="${ctx}/member/memberRank/form?id=${memberRank.id}">
					${memberRank.name}</a>
				</td>
				<td>
					${fns:getDictLabel(memberRank.isDefault, 'true_false', '')}
				</td>
				<td>
					${fns:getDictLabel(memberRank.isSpecial, 'true_false', '')}
				</td>
				
				<td>
					<fmt:formatDate value="${memberRank.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${memberRank.remarks}
				</td>
				<shiro:hasPermission name="member:memberRank:edit"><td>
    				<a href="${ctx}/member/memberRank/form?id=${memberRank.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/member/memberRank/delete?id=${memberRank.id}" 
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