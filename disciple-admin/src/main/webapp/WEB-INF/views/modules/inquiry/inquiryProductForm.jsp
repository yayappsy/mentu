<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.inquiryProduct" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
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
		<li><a href="${ctx}/inquiry/inquiryProduct/"><spring:message code="admin.inquiryProduct"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/inquiry/inquiryProduct/form?id=${inquiryProduct.id}">
		    <shiro:hasPermission name="inquiry:inquiryProduct:edit">
		       <spring:message code="admin.inquiryProduct"/>
		       <spring:message code="admin.common.${not empty inquiryProduct.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="inquiry:inquiryProduct:edit">
		           <spring:message code="admin.inquiryProduct"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="inquiryProduct" action="${ctx}/inquiry/inquiryProduct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="InquiryProduct.inquirySheet.id"/>：</label>
			<div class="controls">
				<form:input path="inquirySheet.id" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="inquirySheet.id" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="InquiryProduct.productId"/>：</label>
			<div class="controls">
				<form:input path="productId" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="productId" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="InquiryProduct.productName"/>：</label>
			<div class="controls">
				<form:input path="productName" htmlEscape="false" maxlength="100" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="productName" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="InquiryProduct.salesPrice"/>：</label>
			<div class="controls">
				<form:input path="salesPrice" htmlEscape="false" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="salesPrice" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="InquiryProduct.expectedPrice"/>：</label>
			<div class="controls">
				<form:input path="expectedPrice" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="expectedPrice" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="InquiryProduct.preOrderQuantity"/>：</label>
			<div class="controls">
				<form:input path="preOrderQuantity" htmlEscape="false" maxlength="11" 
				          class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="preOrderQuantity" cssStyle="color:red"/>
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
            <shiro:hasPermission name="inquiry:inquiryProduct:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>