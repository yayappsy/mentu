<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.productCategory" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(function(){
			$("#treeTable").treeTable({expandLevel : 3}).show();
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/product/productCategory/"><spring:message code="admin.productCategory.list"/></a></li>
		<shiro:hasPermission name="product:productCategory:edit"><li><a href="${ctx}/product/productCategory/form">
		   <spring:message code="admin.productCategory.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="productCategory" action="${ctx}/product/productCategory/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li>
				<label> <spring:message code="ProductCategory.name"/>：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.search'/>"/>
			    <input id="btnClear" class="btn btn-primary" type="button" value="<spring:message code='admin.common.clear'/>"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<weimhc:message resultMessage="${resultMessage}"/>		
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th> <spring:message code="ProductCategory.name"/></th>
				<th> <spring:message code="ProductCategory.isShow"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<shiro:hasPermission name="product:productCategory:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList">
		<c:forEach items="${list}" var="product">
				<tr id="${product.id}"
					pId="${product.parent.id ne '1'?product.parent.id:'0'}">
					<td><a
						href="${ctx}/product/productCategory/form?id=${product.id}">
							${product.name} </a></td>
					<td>${fns:getDictLabel(product.isShow, 'show_hide', '显示')}</td>
					<td><fmt:formatDate value="${product.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<shiro:hasPermission name="product:product:edit">
						<td><a
							href="${ctx}/product/product/form?id=${product.id}"><spring:message
									code="admin.common.modify" /></a> <a
							href="${ctx}/product/product/delete?id=${product.id}"
							onclick="return confirmx('<spring:message code="admin.dialog.deleteSubConfirm"/>', this.href)"><spring:message
									code="admin.common.delete" /></a> <a
							href="${ctx}/product/product/form?parent.id=${product.id}"><spring:message
									code="admin.dialog.addSub" /><spring:message code="admin.product" /></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
</body>
</html>