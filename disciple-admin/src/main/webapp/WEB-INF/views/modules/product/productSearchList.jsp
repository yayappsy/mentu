<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.member" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(function(){

	});
</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/product/product/"><spring:message
					code="admin.product.list" /></a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="product"
		action="${ctx}/product/product/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden"
			value="${page.pageable.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageable.pageSize}" />
		<input id="searchType" name="searchType" type="hidden"
			value="${searchType}" />
		<ul class="ul-form">
			<li><label> <spring:message code="Product.name" />：
			</label> <form:input path="name" htmlEscape="false" maxlength="255"
					class="input-medium" /></li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="<spring:message code='admin.common.search'/>" />
				<input id="btnClear" class="btn btn-primary" type="button"
				value="<spring:message code='admin.common.clear'/>" />
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<weimhc:message resultMessage="${resultMessage}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<th><spring:message code="admin.common.choose" /></th>
				<th><spring:message code="Product.name" /></th>
				<th><spring:message code="Product.sn" /></th>
				<th><spring:message code="Product.subtitle" /></th>
				<th><spring:message code="Product.productCategoryName" /></th>
				<th><spring:message code="Product.productBrandName" /></th>
				<th><spring:message code="Product.marketPrice" /></th>
				<th><spring:message code="Product.salesPrice" /></th>
				<th><spring:message code="DataEntity.updateDate" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="product">
				<tr>
					<td><c:choose>
							<c:when test="${isMultiple}">
								<input type="checkbox" name="id" value="${product.id}"
									data-type="product" data-id="${product.id}"
									data-name="${product.name}" 
									/>
							</c:when>
							<c:otherwise>
								<input type="radio" name="id" value="${product.id}"
									data-type="product" data-id="${product.id}"
									data-name="${product.name}"/>
							</c:otherwise>
						</c:choose></td>
					<td><a href="${ctx}/product/product/form?id=${product.id}">
							${product.name} </a></td>
					<td>${product.sn}</td>
					<td>${product.subtitle}</td>
					<td>${product.productCategoryName}</td>
					<td>${product.productBrandName}</td>
					<td>${product.marketPrice}</td>
					<td>${product.salesPrice}</td>
					<td><fmt:formatDate value="${product.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<sys:pagination paginationSize="1" pageable="${page.pageable }" />
</body>
</html>