<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.productInstalment" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
	<script src="${ctxStatic}/modules/product/selectProduct.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
           //默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
           //设置选择样式
           $("#inputForm").find(".trueFalse").each(function(){
				$(this).bootstrapSwitch();
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/productInstalment/"><spring:message code="admin.productInstalment"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/product/productInstalment/form?id=${productInstalment.id}">
		    <shiro:hasPermission name="product:productInstalment:edit">
		       <spring:message code="admin.productInstalment"/>
		       <spring:message code="admin.common.${not empty productInstalment.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="product:productInstalment:edit">
		           <spring:message code="admin.productInstalment"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="productInstalment" action="${ctx}/product/productInstalment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="ProductInstalment.productId"/>：</label>
			<div class="controls">
				<form:hidden id="productId" path="product.id" /> 
						<form:input id="productName" path="productName" htmlEscape="false"
							maxlength="64" class="input-medium" />
						<input id="btnProductSelect" class="btn btn-primary" type="button"
							value="选择商品套餐" />
						<input id="btnProductSelectRemove" class="btn btn-primary"
							type="button" value="清除选择的商品套餐" />
			    <form:errors path="product.id" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ProductInstalment.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" 
				          class="input-xlarge "/>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ProductInstalment.price"/>：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="price" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ProductInstalment.description"/>：</label>
			<div class="controls">
			<form:textarea path="description" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>

			    <form:errors path="description" cssStyle="color:red"/>
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
            <shiro:hasPermission name="product:productInstalment:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>