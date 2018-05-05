<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.sn" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sn/sn/"><spring:message code="admin.sn.list"/></a></li>
		<shiro:hasPermission name="sn:sn:edit"><li><a href="${ctx}/sn/sn/form">
		   <spring:message code="admin.sn.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sn" action="${ctx}/sn/sn/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="Sn.lastValue"/>：</label>
				<form:input path="lastValue" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li>
				<label> <spring:message code="Sn.snType"/>：</label>
				<form:select path="snType" class="input-medium">
					<form:option value=""><spring:message code="admin.common.defaultSelect"/></form:option>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th> <spring:message code="Sn.lastValue"/></th>
				<th> <spring:message code="Sn.snType"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasPermission name="sn:sn:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sn">
			<tr>
				<td><a href="${ctx}/sn/sn/form?id=${sn.id}">
					${sn.lastValue}
				</a></td>
				<td>
					${fns:getDictLabel(sn.snType, '', '')}
				</td>
				<td>
					<fmt:formatDate value="${sn.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sn.remarks}
				</td>
				<shiro:hasPermission name="sn:sn:edit"><td>
    				<a href="${ctx}/sn/sn/form?id=${sn.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/sn/sn/delete?id=${sn.id}" 
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