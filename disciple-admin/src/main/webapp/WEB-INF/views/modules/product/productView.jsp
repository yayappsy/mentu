<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.product.audit" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<script type="text/javascript">
	$(document).ready(
			function() {
				//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
				//设置选择样式
				$("#inputForm").find(".trueFalse").each(function() {
					$(this).bootstrapSwitch();
				});
				$('input[name="isPassed"]').on('switchChange.bootstrapSwitch',
						function(event, state) {
							//console.log(state); // true | false
							if (state) {
								$("#remarksInfo").hide();
							} else {
								$("#remarksInfo").show();
							}
						});
			});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/product/"><spring:message
					code="admin.product.list" /></a></li>
		<li class="active"><a
			href="${ctx}/product/product/detail?handle=${handle }&id=${product.id}"> <spring:message
					code="admin.product.audit" />

		</a></li>
	</ul>
	<br />
	<div class="row">
		<div class="offset2 span10">
			<form:form id="inputForm" modelAttribute="product"
				action="${ctx}/product/product/${handle }" method="post"
				class="form-horizontal">
				<form:hidden path="id" />
				<shops:message message="${message}" />
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed">
					<tr>
						<th colspan="6" class="title text-center"><spring:message
								code="admin.product.base" /></th>
					</tr>
					<tr>
						<td class="name" width="120"><spring:message
								code="Product.name" />：</td>
						<td>${product.name}</td>
						<td class="name"><spring:message code="Product.subtitle" />：</td>
						<td colspan="3">${product.subtitle}</td>
					</tr>
					<tr>
						<td class="name"><spring:message
								code="Product.productCategory" />：</td>
						<td>${product.productCategory.name}</td>
						<td class="name" width="120"><spring:message
								code="Product.productType" />：</td>
						<td>${product.productType.name}</td>
						<td class="name" width="120"><spring:message
								code="Product.articleNumber" />：</td>
						<td>${product.articleNumber}</td>
					</tr>
					<tr>
						<td class="name"><spring:message code="Product.costPrice" />：</td>
						<td><fmt:formatNumber type="currency"
								value="${product.costPrice}" /></td>
						<td class="name"><spring:message code="Product.marketPrice" />：</td>
						<td><fmt:formatNumber type="currency"
								value="${product.marketPrice}" /></td>
						<td class="name"><spring:message code="Product.salesPrice" />：</td>
						<td><fmt:formatNumber type="currency"
								value="${product.salesPrice}" /></td>
					</tr>
				</table>

				<c:if test="${handle ne null and handle ne '' }">
					<table id="contentTable"
						class="table table-striped table-bordered table-condensed">
						<c:choose>
							<c:when test="${handle eq 'audit' }"> 
								<tr>
									<th colspan="2" class="title text-center"><spring:message
											code="admin.shop.join.certificate" /></th>
								</tr>
								<tr>
									<td class="name" width="120"><spring:message
											code="admin.product.audit" />：</td>
									<td>
										<div class="control-group">
											<div class="controls">
												<form:checkbox path="isPassed" class="trueFalse"
													data-on-text="${fns:getMessage(languageType, 'admin.common.agree',null)}"
													data-off-text="${fns:getMessage(languageType, 'admin.common.refuse',null)}" />
											</div>
										</div>
									</td>
								</tr>
								<tr id="remarksInfo" style="display: none">
									<td class="name" width="120"><spring:message
											code="admin.product.refuseReason" />：</td>
									<td>
										<div class="control-group">
											<div class="controls" style="margin-left: 0px;">
												<form:textarea path="productAuditRemark" htmlEscape="false"
													rows="4" maxlength="255" class="input-xxlarge " />
											</div>
										</div>
									</td>
								</tr>
							</c:when>
							<c:when test="${handle eq 'illegal' }">
								<tr>
									<th colspan="2" class="title text-center"><spring:message
											code="admin.product.illegal" /></th>
								</tr>
								<tr id="remarksInfo">
									<td class="name" width="120"><spring:message
											code="admin.product.illegalReason" />：</td>
									<td>
										<div class="control-group">
											<div class="controls" style="margin-left: 0;">
												<form:textarea path="productAuditRemark" htmlEscape="false"
													rows="4" maxlength="255" class="input-xxlarge " />
											</div>
										</div>
									</td>
								</tr>
							</c:when>
						</c:choose>

					</table>
				</c:if>
				<div class="form-actions">
					<shiro:hasPermission name="product:product:edit">
						<input id="btnSubmit" class="btn btn-primary" type="submit"
							value="<spring:message code='admin.common.save'/>" />&nbsp;
			         </shiro:hasPermission>
					<input id="btnCancel" class="btn" type="button"
						value="<spring:message code='admin.common.back'/>"
						onclick="history.go(-1)" />
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>