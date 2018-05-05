<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.productExtra" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/product/productExtra/"><spring:message code="admin.productExtra"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="product:productExtra:edit"><li><a href="${ctx}/product/productExtra/form">
		   <spring:message code="admin.productExtra"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="productExtra" action="${ctx}/product/productExtra/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="ProductExtra.productId"/>：</label>
				<form:input path="product" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th> <spring:message code="ProductExtra.vipMonth"/></th>
				<th> <spring:message code="ProductExtra.lessionNumber"/></th>
				<shiro:hasPermission name="product:productExtra:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="productExtra">
			<tr>
				<td><a href="${ctx}/product/productExtra/form?id=${productExtra.id}">
					${productExtra.vipMonth}
				</a></td>
				<td>
					${productExtra.lessonNumber}
				</td>
				<shiro:hasPermission name="product:productExtra:edit"><td>
    				<a href="${ctx}/product/productExtra/form?id=${productExtra.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/product/productExtra/delete?id=${productExtra.id}" 
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