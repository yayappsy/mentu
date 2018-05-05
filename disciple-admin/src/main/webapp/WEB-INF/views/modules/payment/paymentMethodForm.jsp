<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.paymentMethod" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/payment/paymentMethod/"><spring:message code="admin.paymentMethod.list"/></a></li>
		<li class="active"><a href="${ctx}/payment/paymentMethod/form?id=${paymentMethod.id}">
		    <shiro:hasPermission name="payment:paymentMethod:edit"><spring:message code="admin.paymentMethod.${not empty paymentMethod.id?'edit':'add'}"/></shiro:hasPermission>
		    <shiro:lacksPermission name="payment:paymentMethod:edit"><spring:message code="admin.paymentMethod.view"/></shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="paymentMethod" action="${ctx}/payment/paymentMethod/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="ShopPaymentMethod.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ShopPaymentMethod.description"/>：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="description" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ShopPaymentMethod.icon"/>：</label>
			<div class="controls">
				<form:input path="icon" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="icon" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ShopPaymentMethod.paymentMethodType"/>：</label>
			<div class="controls">
				<form:input path="paymentMethodType" htmlEscape="false" maxlength="25" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="paymentMethodType" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ShopPaymentMethod.timeout"/>：</label>
			<div class="controls">
				<input name="timeout" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${paymentMethod.timeout}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			    <form:errors path="timeout" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ShopPaymentMethod.config"/>：</label>
			<div class="controls">
				<form:input path="config" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="config" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ShopPaymentMethod.content"/>：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" class="input-xxlarge "/>
			    <form:errors path="content" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ShopPaymentMethod.sort"/>：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11" 
				          class="input-xlarge "/>
			    <form:errors path="sort" cssStyle="color:red"/>
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
            <shiro:hasPermission name="payment:paymentMethod:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>