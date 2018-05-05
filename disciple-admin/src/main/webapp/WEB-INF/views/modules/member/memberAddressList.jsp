<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.memberAddress" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
<script type="text/javascript"
	src="${ctxStatic }/modules/member/selectMember.js"></script>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/member/memberAddress/?member.id=${memberAddress.member.id}&searchType=${searchType}"><spring:message
					code="admin.memberAddress.list" /></a></li>
		<li><a
			href="${ctx}/member/memberAddress/form?member.id=${memberAddress.member.id}&searchType=${searchType}"><spring:message
					code="admin.memberAddress.add" /></a></li>
		<%-- <shiro:hasPermission name="member:memberAddress:edit"><li><a href="${ctx}/member/memberAddress/form">
		   <spring:message code="admin.memberAddress.add"/></a></li>
		</shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="memberAddress"
		action="${ctx}/member/memberAddress/?member.id=${memberAddress.member.id}"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden"
			value="${page.pageable.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageable.pageSize}" />
		<input id="searchType" name="searchType" type="hidden"
			value="${searchType}" />
		<ul class="ul-form">
			<li><c:choose>
					<c:when test="${'particularMember' eq searchType}">
						<label> <spring:message code="MemberAddress.memberId" />：
						</label>
						<form:hidden id="memberId" path="member.id" /> 
			        ${fns:isNotBlank(memberAddress.member.name)? memberAddress.member.name : memberAddress.member.mobile}
			     </c:when>
					<c:otherwise>
						<label> <spring:message code="MemberAddress.memberId" />：
						</label>
						<form:hidden id="memberId" path="member.id" />
						<form:input id="memberName" path="member.name" htmlEscape="false"
							maxlength="64" class="input-medium" />
						<input id="btnMemberSelect" class="btn btn-primary" type="button"
							value="选择会员" />
						<input id="btnMemberSelectRemove" class="btn btn-primary"
							type="button" value="清除选择的会员" />
					</c:otherwise>
				</c:choose></li>
			<li><label> <spring:message
						code="MemberAddress.contactName" />：
			</label> <form:input path="contactName" htmlEscape="false" maxlength="64"
					class="input-medium" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="<spring:message code='admin.common.search'/>" />
				<input id="btnClear" class="btn btn-primary" type="button"
				value="<spring:message code='admin.common.clear'/>" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<weimhc:message resultMessage="${resultMessage}"/>		
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th> <spring:message code="MemberAddress.memberId"/></th>
				<th> <spring:message code="MemberAddress.contactName"/></th>
				<th> <spring:message code="MemberAddress.phone"/></th>
				<th> <spring:message code="MemberAddress.mobile"/></th>
				<th> <spring:message code="MemberAddress.detailedAddress"/></th>
				<th><spring:message code="admin.common.handle" /></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="memberAddress">
			<tr>
					<td><a
						href="${ctx}/member/memberAddress/form?id=${memberAddress.id}">
							${memberAddress.member.nickname} </a></td>
					<td>${memberAddress.contactName}</td>
					<td>${memberAddress.phone}</td>
					<td>${memberAddress.mobile}</td>
					<td>${memberAddress.detailedAddress}</td>
					<td><a
						href="${ctx}/member/memberAddress/form?id=${memberAddress.id}"><spring:message
								code="admin.common.modify" /></a> <a
						href="${ctx}/member/memberAddress/delete?id=${memberAddress.id}"
						onclick="return confirmx('<spring:message code="admin.dialog.deleteConfirm"/>', this.href)">
							<spring:message code="admin.common.delete" />
				</a></td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
    <sys:pagination paginationSize="1" pageable="${page.pageable }"/>
</body>
</html>