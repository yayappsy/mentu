<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.orderInstalment" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order/orderInstalment/"><spring:message code="admin.orderInstalment"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="order:orderInstalment:edit"><li><a href="${ctx}/order/orderInstalment/form">
		   <spring:message code="admin.orderInstalment"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="orderInstalment" action="${ctx}/order/orderInstalment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="OrderInstalment.orderId"/>：</label>
				<form:input path="order" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li>
				<label> <spring:message code="OrderInstalment.productId"/>：</label>
				<form:input path="product" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li>
				<label> <spring:message code="OrderInstalment.name"/>：</label>
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
				<th> <spring:message code="OrderInstalment.orderId"/></th>
				<th> <spring:message code="OrderInstalment.productName"/></th>
				<th> <spring:message code="OrderInstalment.name"/></th>
				<th> <spring:message code="OrderInstalment.price"/></th>
				<th> <spring:message code="OrderInstalment.isOver"/></th>
				<th> <spring:message code="OrderInstalment.expire"/></th>
				<th> <spring:message code="DataEntity.createDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasPermission name="order:orderInstalment:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderInstalment">
			<tr>
				<td><a href="${ctx}/order/orderInstalment/form?id=${orderInstalment.id}">
					${orderInstalment.order}
				</a></td>
				<td>
					${orderInstalment.productName}
				</td>
				<td>
					${orderInstalment.name}
				</td>
				<td>
					${orderInstalment.price}
				</td>
				<td>
					${orderInstalment.isOver}
				</td>
				<td>
					<fmt:formatDate value="${orderInstalment.expire}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${orderInstalment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${orderInstalment.remarks}
				</td>
				<shiro:hasPermission name="order:orderInstalment:edit"><td>
    				<a href="${ctx}/order/orderInstalment/form?id=${orderInstalment.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/order/orderInstalment/delete?id=${orderInstalment.id}" 
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