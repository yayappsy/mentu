<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.productInstalment" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/product/productInstalment/"><spring:message code="admin.productInstalment"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="product:productInstalment:edit"><li><a href="${ctx}/product/productInstalment/form">
		   <spring:message code="admin.productInstalment"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="productInstalment" action="${ctx}/product/productInstalment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="ProductInstalment.productName"/>：</label>
				<form:input path="productName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li>
				<label> <spring:message code="ProductInstalment.name"/>：</label>
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
				<th> <spring:message code="ProductInstalment.productName"/></th>
				<th> <spring:message code="ProductInstalment.name"/></th>
				<th> <spring:message code="ProductInstalment.price"/></th>
				<th> <spring:message code="DataEntity.createDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasPermission name="product:productInstalment:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="productInstalment">
			<tr>
				<td><a href="${ctx}/product/productInstalment/form?id=${productInstalment.id}">
					${productInstalment.productName}
				</a></td>
				<td>
					${productInstalment.name}
				</td>
				<td>
					${productInstalment.price}
				</td>
				<td>
					<fmt:formatDate value="${productInstalment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${productInstalment.remarks}
				</td>
				<shiro:hasPermission name="product:productInstalment:edit"><td>
    				<a href="${ctx}/product/productInstalment/form?id=${productInstalment.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/product/productInstalment/delete?id=${productInstalment.id}" 
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