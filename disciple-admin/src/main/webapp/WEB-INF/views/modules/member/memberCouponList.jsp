<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.memberCoupon" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic }/modules/member/selectMember.js"></script>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/memberCoupon/?searchType=${searchType}&student.id=${memberCoupon.student.id}"><spring:message code="admin.memberCoupon"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="member:memberCoupon:edit"><li><a href="${ctx}/member/memberCoupon/form?searchType=${searchType}&student.id=${memberCoupon.student.id}">
		   <spring:message code="admin.memberCoupon"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="memberCoupon" action="${ctx}/member/memberCoupon/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<input id="searchType" name="searchType" type="hidden"
			value="${searchType}" />	
		<ul class="ul-form">
			
			<li>		
				<c:choose>
					<c:when test="${'particularMember' eq searchType}">
						<label> <spring:message code="MemberCoupon.studentId" />：
						</label>
						<form:hidden id="memberId" path="student.id" /> 
			        ${fns:isNotBlank(memberCoupon.student.name)? memberCoupon.student.name : memberCoupon.student.mobile}
			     </c:when>
					<c:otherwise>
					<label> <spring:message code="MemberCoupon.studentId" />：
						</label>
						<form:hidden id="memberId" path="student.id" /> 
						<form:input id="memberName" path="student.name" htmlEscape="false"
							maxlength="64" class="input-medium" />
						<input id="btnMemberSelect" class="btn btn-primary" type="button"
							value="选择会员" />
						<input id="btnMemberSelectRemove" class="btn btn-primary"
							type="button" value="清除选择的会员" />
					</c:otherwise>
				</c:choose>
			</li>
			<li>
				<label> <spring:message code="MemberCoupon.isUsed"/>：</label>
				<form:select path="isUsed">
				<form:option value="">
						<spring:message code="admin.common.defaultSelect" />
					</form:option>
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				
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
				<th> <spring:message code="MemberCoupon.couponId"/></th>
				<th> <spring:message code="MemberCoupon.studentId"/></th>
				<th> <spring:message code="MemberCoupon.code"/></th>
				<th> <spring:message code="MemberCoupon.isUsed"/></th>
				<th> <spring:message code="DataEntity.createDate"/></th>        
				<shiro:hasPermission name="member:memberCoupon:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="memberCoupon">
			<tr>
				<td><a href="${ctx}/member/memberCoupon/form?id=${memberCoupon.id}">
					${memberCoupon.coupon.name}
				</a></td>
				<td>
					${memberCoupon.student.nickname}
				</td>
				<td>
					${memberCoupon.code}
				</td>
				<td>
					${memberCoupon.isUsed}
				</td>
				<td>
					<fmt:formatDate value="${memberCoupon.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="member:memberCoupon:edit"><td>
    				<a href="${ctx}/member/memberCoupon/form?id=${memberCoupon.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/member/memberCoupon/delete?id=${memberCoupon.id}" 
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