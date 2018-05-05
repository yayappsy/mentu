<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.order" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
			function openLayerByInstalment(url,orderId){
				layer.open({
	 			      type: 2,  	
	 			      shadeClose: true,
	 			      shade: false,
	 			      maxmin: true, //开启最大化最小化按钮
	 			      area: ['800px', '600px'],
	 			      content: url+"?order.id="+orderId+"&searchType=particularMember",
	 			    	  btn: ['确定','关闭'],
	 	                  yes: function(index){ 	    
	 	                        //最后关闭弹出层
	 	                        layer.close(index);
	 	                    },
	 	                    cancel: function(){
	 	                        //右上角关闭回调
	 	                    }
	 			    });
			}
			 $('.findInstalment').click(function(){
				 openLayerByInstalment("${ctx}/order/orderInstalment/list",$(this).data("orderId"));
		   	 });	
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order/order/"><spring:message code="admin.order.list"/></a></li>
		<shiro:hasPermission name="order:order:edit"><li><a href="${ctx}/order/order/form">
		   <spring:message code="admin.order.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="order" action="${ctx}/order/order/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="Order.memberNickname"/>：</label>
				<form:input path="memberNickname" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th> <spring:message code="Order.memberNickname"/></th>
				<th> <spring:message code="Order.detailedAddress"/></th>
				<th> <spring:message code="Order.contactName"/></th>
				<th> <spring:message code="Order.mobile"/></th>
				<th> <spring:message code="Order.status"/></th>
				<th> <spring:message code="Order.amountPayable"/></th>
				<th> <spring:message code="Order.totalAmount"/></th>
				<th> <spring:message code="Order.paymentStatus"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasPermission name="order:order:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="order">
			<tr>
				<td><a href="${ctx}/order/order/form?id=${order.id}">
					${order.memberNickname}
				</a></td>
				<td>
					${order.detailedAddress}
				</td>
				<td>
					${order.contactName}
				</td>
				<td>
					${order.mobile}
				</td>
				<td>
					${order.status}
				</td>
				<td>
					${order.amountPayable}
				</td>
				<td>
					${order.totalAmount}
				</td>
				<td>
					${order.paymentStatus}
				</td>
				<td>
					<fmt:formatDate value="${order.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${order.remarks}
				</td>
				<shiro:hasPermission name="order:order:edit"><td>
				<c:if test="${order.isInstalmentOrder==true}">
				
				<a href="javascript:;" data-order-id="${order.id}" class="findInstalment"><spring:message code="查看分期情况"/></a>
				</c:if>
    				<a href="${ctx}/order/order/form?id=${order.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/order/order/delete?id=${order.id}" 
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