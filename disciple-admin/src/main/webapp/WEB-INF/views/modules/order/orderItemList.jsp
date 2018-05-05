<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.orderItem" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order/orderItem/"><spring:message code="admin.orderItem.list"/></a></li>
		<shiro:hasPermission name="order:orderItem:edit"><li><a href="${ctx}/order/orderItem/form">
		   <spring:message code="admin.orderItem.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="orderItem" action="${ctx}/order/orderItem/" method="post" class="breadcrumb form-search">
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
				<th> <spring:message code="OrderItem.orderId"/></th>
				<th> <spring:message code="OrderItem.productId"/></th>
				<th> <spring:message code="OrderItem.productName"/></th>
				<th> <spring:message code="OrderItem.price"/></th>
				<th> <spring:message code="OrderItem.pricePayable"/></th>
				<th> <spring:message code="OrderItem.quantity"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasPermission name="order:orderItem:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderItem">
			<tr>
				<td><a href="${ctx}/order/orderItem/form?id=${orderItem.id}">
					${orderItem.order.sn}
				</a></td>
				<td>
					${orderItem.product.name}
				</td>
				<td>
					${orderItem.productName}
				</td>
				<td>
					${orderItem.price}
				</td>
				<td>
					${orderItem.pricePayable}
				</td>
				<td>
					${orderItem.quantity}
				</td>
				<td>
					<fmt:formatDate value="${orderItem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${orderItem.remarks}
				</td>
				<shiro:hasPermission name="order:orderItem:edit"><td>
    				<a href="${ctx}/order/orderItem/form?id=${orderItem.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/order/orderItem/delete?id=${orderItem.id}" 
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