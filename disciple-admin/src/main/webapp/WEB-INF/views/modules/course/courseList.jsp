<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>培训课程<spring:message code="admin.common.manager"/></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/course/course/">培训课程<spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="course:course:edit"><li><a href="${ctx}/course/course/form">
		   培训课程<spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="course" action="${ctx}/course/course/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> 课程名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li>
				<label> 班级大小</label>
				<form:radiobutton path="size" value="1"/>小班
				<form:radiobutton path="size" value="2"/>中班
				<form:radiobutton path="size" value="3"/>大班
			</li>
			<li>
				<label> 行业名称：</label>
				<form:input path="industryName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li>
				<label> 授课老师：</label>
				<form:input path="teacher" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li>
				<label> 价格：</label>
				<form:input path="price" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th> 课程名称</th>
				<th> 班级大小</th>
				<th> 开始时间</th>
				<th> 结束时间</th>
				<th> 行业名称</th>
				<th> 授课老师</th>
				<th> 价格</th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<shiro:hasAnyPermissions name="course:course:edit,course:course:delete">
					<th><spring:message code="admin.common.handle"/></th>
				</shiro:hasAnyPermissions>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="course">
			<tr>
				<td><a href="${ctx}/course/course/form?id=${course.id}">
					${course.name}
				</a></td>
				<td>
					<c:if test="${course.size=='1'}">
						小班
					</c:if>
					<c:if test="${course.size=='2'}">
						中班
					</c:if>
					<c:if test="${course.size=='3'}">
						大班
					</c:if>


				</td>
				<td>
					<fmt:formatDate value="${course.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${course.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${course.industryName}
				</td>
				<td>
					${course.teacher}
				</td>
				<td>
					${course.price}
				</td>
				<td>
					<fmt:formatDate value="${course.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasAnyPermissions name="course:course:edit,course:course:delete">
					<td>
						<shiro:hasPermission name="course:course:edit">
	    					<a href="${ctx}/course/course/form?id=${course.id}"><spring:message code="admin.common.modify"/></a>
						</shiro:hasPermission>
							<a href="${ctx}/course/course/delete?id=${course.id}"
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