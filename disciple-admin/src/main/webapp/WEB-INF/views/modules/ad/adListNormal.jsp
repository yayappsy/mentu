<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.ad" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(function(){

	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ad/ad/list?adPositionId=${ad.adPosition.id}&dispalyMoreAdPositon=${dispalyMoreAdPositon}">
		${ad.adPosition.name }
				<spring:message code="admin.common.list" /></a></li>
		<shiro:hasPermission name="ad:ad:edit">
			<li><a href="${ctx}/ad/ad/form?adPositionId=${ad.adPosition.id}&dispalyMoreAdPositon=${dispalyMoreAdPositon}">${ad.adPosition.name }
					<spring:message code="admin.common.add" /></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ad"
		action="${ctx}/ad/ad/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden"
			value="${page.pageable.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageable.pageSize}" />
		<input type="hidden" id="dispalyMoreAdPositon" name="dispalyMoreAdPositon" 
			value="${dispalyMoreAdPositon}" />
		<ul class="ul-form">
		    <c:if test="${dispalyMoreAdPositon }">
				<li>
					<label> <spring:message code="Ad.adPositionId"/>：</label>
					<form:select path="adPosition.id" class="input-xlarge ">
						<form:options items="${adPositionList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
					</form:select>
				</li>	
			</c:if>
			<li><label> <spring:message code="Ad.title" />：
			</label> <form:input path="title" htmlEscape="false" maxlength="255"
					class="input-medium" /></li>
			<li><label> <spring:message code="DataEntity.isShow" />：
			</label> <form:checkboxes path="isShow"
					items="${fns:getDictList('true_false')}" itemLabel="label"
					itemValue="value" htmlEscape="false" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="<spring:message code='admin.common.search'/>" />
				<input id="btnClear" class="btn btn-primary" type="button"
				value="<spring:message code='admin.common.clear'/>" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<weimhc:message resultMessage="${resultMessage}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><spring:message code="Ad.title" /></th>
				<th><spring:message code="Ad.contentType" /></th>
				<th><spring:message code="Ad.description" /></th>
				<th><spring:message code="Ad.beginDate" /></th>
				<th><spring:message code="Ad.endDate" /></th>
				<th><spring:message code="Ad.isForever" /></th>
				<th><spring:message code="DataEntity.isShow" /></th>
				<shiro:hasPermission name="ad:ad:edit">
					<th><spring:message code="admin.common.handle" /></th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="ad">
				<tr>
					<td><a href="${ctx}/ad/ad/form?id=${ad.id}">${ad.title}
							</a></td>
					<td><spring:message code="Ad.ContentType.${ad.contentType }" /></td>
					<td>${ad.description}</td>
				    <td>
					    <fmt:formatDate value="${ad.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				    </td>
					<td>
					    <fmt:formatDate value="${ad.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				    </td>
					<td>${fns:getDictLabel(ad.isForever, 'true_false', '')}</td>
					<td>${fns:getDictLabel(ad.isShow, 'true_false', '')}</td>
					<shiro:hasPermission name="ad:ad:edit">
						<td><a href="${ctx}/ad/ad/form?id=${ad.id}&dispalyMoreAdPositon=${dispalyMoreAdPositon}"><spring:message
									code="admin.common.modify" /></a> <a
							href="${ctx}/ad/ad/delete?id=${ad.id}&dispalyMoreAdPositon=${dispalyMoreAdPositon}"
							onclick="return confirmx('<spring:message code="admin.dialog.deleteConfirm"/>', this.href)">
								<spring:message code="admin.common.delete" />
						</a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<sys:pagination paginationSize="1" pageable="${page.pageable }" />
</body>
</html>