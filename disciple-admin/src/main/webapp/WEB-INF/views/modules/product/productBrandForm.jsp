<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.productBrand" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />

<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#inputForm").find("input[type='checkbox']").each(function() {
			$(this).bootstrapSwitch();
		});
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/productBrand/"><spring:message
					code="admin.productBrand" /> <spring:message
					code="admin.common.list" /></a></li>
		<li class="active"><a
			href="${ctx}/product/productBrand/form?id=${productBrand.id}"> <shiro:hasPermission
					name="product:productBrand:edit">
					<spring:message code="admin.productBrand" />
					<spring:message
						code="admin.common.${not empty product.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="product:productBrand:edit">
					<spring:message code="admin.productBrand" />
					<spring:message code="admin.common.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="productBrand"
		action="${ctx}/product/productBrand/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<weimhc:message resultMessage="${message}" />
		<div class="control-group">
			<label class="control-label"><spring:message
					code="ProductBrand.name" />：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<form:errors path="name" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Product.productCategoryId" />：</label>
			<div class="controls">
				<sys:treeselect id="productCategory" name="productCategory.id"
					value="${productBrand.productCategory.id}"
					labelName="productCategoryName"
					labelValue="${productBrand.productCategory.name}"
					title="<spring:message code='admin.productCategory'/>"
					url="/product/productCategory/treeData" cssClass=""
					allowClear="true" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="productCategory.id" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="ProductBrand.englishName" />：</label>
			<div class="controls">
				<form:input path="englishName" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<form:errors path="englishName" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="ProductBrand.logoUrl" />：</label>
			<div class="controls">
				<form:hidden path="logoUrl" htmlEscape="false" id="logoUrl"
					maxlength="255" class="input-xlarge" />
				<weimhc:selectImage input="logoUrl" />
				<span class="help-inline"><font color="red">*</font></span>
				<form:errors path="logoUrl" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group hide">
			<label class="control-label"><spring:message
					code="ProductBrand.siteUrl" />：</label>
			<div class="controls">
				<form:input path="siteUrl" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<form:errors path="siteUrl" cssStyle="color:red" />
				<span class="help-inline">网请以http://开头，内网请写相对路径。</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="ProductBrand.description" />：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false"
					class="input-xlarge " />
				<form:errors path="description" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group hide">
			<label class="control-label"><spring:message
					code="ProductBrand.status" />：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="25"
					class="input-xlarge " />
				<form:errors path="status" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="ProductBrand.isRecommend" />：</label>
			<div class="controls">
				<form:checkbox path="isRecommend"
					data-on-text="${fns:getMessage(languageType, 'admin.common.true',null)}"
					data-off-text="${fns:getMessage(languageType, 'admin.common.false',null)}" />
				<form:errors path="isRecommend" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="ProductBrand.sort" />：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11"
					class="input-xlarge number" />
				<form:errors path="sort" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="DataEntity.remarks" />：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
				<form:errors path="remarks" cssStyle="color:red" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="product:productBrand:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="<spring:message code='admin.common.save'/>" />&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button"
				value="<spring:message code='admin.common.back'/>"
				onclick="history.go(-1)" "/>
		</div>
	</form:form>
</body>
</html>