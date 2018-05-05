<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.productBrand" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/product/productBrand/">
		    <spring:message code="admin.productBrand"/><spring:message code="admin.common.list"/>
		</a></li>
		<shiro:hasPermission name="product:productBrand:edit"><li><a href="${ctx}/product/productBrand/form">
		    <spring:message code="admin.productBrand"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="productBrand" action="${ctx}/product/productBrand/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="ProductBrand.name"/>：</label>
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
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th> <spring:message code="ProductBrand.name"/></th>
				<th> <spring:message code="ProductBrand.logoUrl"/></th>
				<th> <spring:message code="ProductBrand.productCategory"/></th>
				<th> <spring:message code="ProductBrand.sort"/></th>
				<shiro:hasPermission name="product:productBrand:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="productBrand">
			<tr>
				<td><a href="${ctx}/product/productBrand/form?id=${productBrand.id}">
					${productBrand.name}
				</a></td>
				<td>
					    <weimhc:showImage imagePath="${productBrand.logoUrl}"></weimhc:showImage>
				</td>
				<td>
					${productBrand.productCategory.name}
				</td>
				<td>
					${productBrand.sort}
				</td>
				<%-- <td>
					<a href="${productBrand.siteUrl}" data-href="${productBrand.siteUrl}" target="_blank">${productBrand.siteUrl}</a> 
				</td> --%>
				<shiro:hasPermission name="product:productBrand:edit"><td>
    				<a href="${ctx}/product/productBrand/form?id=${productBrand.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/product/productBrand/delete?id=${productBrand.id}" 
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