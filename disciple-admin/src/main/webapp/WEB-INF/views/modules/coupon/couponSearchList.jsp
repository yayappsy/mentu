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
		<li class="active"><a href="${ctx}/coupon/coupon/"><spring:message code="admin.coupon"/><spring:message code="admin.common.list"/></a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="coupon" action="${ctx}/coupon/coupon/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<input id="searchType" name="searchType" type="hidden"
			value="${searchType}" />
		<ul class="ul-form">
			<li>
				<label> <spring:message code="Coupon.name"/>：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>

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
				<th> <spring:message code="Coupon.name"/></th>
				<th> <spring:message code="Coupon.isEnabled"/></th>
				<th> <spring:message code="Coupon.priceLimit"/></th>
				<th> <spring:message code="Coupon.exchangeLimit"/></th>
				<th> <spring:message code="Coupon.status"/></th>
				<th> <spring:message code="Coupon.quantity"/></th>
				<th> <spring:message code="Coupon.point"/></th>
				<th> <spring:message code="DataEntity.createDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>       
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="coupon">
				<tr>
					<td><c:choose>
							<c:when test="${isMultiple}">
								<input type="checkbox" name="id" value="${coupon.id}"
									data-type="coupon" data-id="${coupon.id}"
									data-name="${coupon.name}" 
									/>
							</c:when>
							<c:otherwise>
								<input type="radio" name="id" value="${coupon.id}"
									data-type="coupon" data-id="${coupon.id}"
									data-name="${coupon.name}"/>
							</c:otherwise>
						</c:choose></td>
					<td><a href="${ctx}/coupon/coupon/form?id=${coupon.id}">
					${coupon.name}
				</a></td>
				<td>
					${fns:getDictLabel(coupon.isEnabled,'true_false','')}
				</td>
				<td>
					${coupon.priceLimit}
				</td>
				<td>
					${coupon.exchangeLimit}
				</td>
				<td>
					${coupon.status}
				</td>
				<td>
					${coupon.quantity}
				</td>
				<td>
					${coupon.point}
				</td>
				<td>
					<fmt:formatDate value="${coupon.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${coupon.remarks}
				</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<sys:pagination paginationSize="1" pageable="${page.pageable }" />
</body>
</html>