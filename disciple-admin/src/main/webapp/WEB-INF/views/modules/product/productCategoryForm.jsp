<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.productCategory" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
	<script type="text/javascript">
		$(function(){
                        //默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/productCategory/"><spring:message code="admin.productCategory.list"/></a></li>
		<li class="active"><a href="${ctx}/product/productCategory/form?id=${productCategory.id}">
		    <shiro:hasPermission name="product:productCategory:edit"><spring:message code="admin.productCategory.${not empty productCategory.id?'edit':'add'}"/></shiro:hasPermission>
		    <shiro:lacksPermission name="product:productCategory:edit"><spring:message code="admin.productCategory.view"/></shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="productCategory" action="${ctx}/product/productCategory/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="ProductCategory.productTypeId"/>：</label>
			<div class="controls">
				<form:input path="propertyTemplate.id" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="propertyTemplate.id" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ProductCategory.grade"/>：</label>
			<div class="controls">
				<form:input path="grade" htmlEscape="false" maxlength="11" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="grade" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ProductCategory.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ProductCategory.imageUrl"/>：</label>
			<div class="controls">
				<form:input path="imageUrl" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			    <form:errors path="imageUrl" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ProductCategory.isShow"/>：</label>
			<div class="controls">
				<form:input path="isShow" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="isShow" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ProductCategory.isRelateSub"/>：</label>
			<div class="controls">
				<form:input path="isRelateSub" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			    <form:errors path="isRelateSub" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ProductCategory.sort"/>：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			    <form:errors path="sort" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ProductCategory.commissionRatio"/>：</label>
			<div class="controls">
				<form:input path="commissionRatio" htmlEscape="false" class="input-xlarge "/>
			    <form:errors path="commissionRatio" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="DataEntity.remarks"/>：</label>        
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			    <form:errors path="remarks" cssStyle="color:red"/>
			</div>
		</div>
		<div class="form-actions">
            <shiro:hasPermission name="product:productCategory:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>