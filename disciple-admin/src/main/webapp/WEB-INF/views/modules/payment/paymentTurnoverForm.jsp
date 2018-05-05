<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.paymentTurnover" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/payment/paymentTurnover/"><spring:message code="admin.paymentTurnover"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/payment/paymentTurnover/form?id=${paymentTurnover.id}">
		    <shiro:hasPermission name="payment:paymentTurnover:edit">
		       <spring:message code="admin.paymentTurnover"/>
		       <spring:message code="admin.common.${not empty paymentTurnover.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="payment:paymentTurnover:edit">
		           <spring:message code="admin.paymentTurnover"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="paymentTurnover" action="${ctx}/payment/paymentTurnover/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="PaymentTurnover.sn"/>：</label>
			<div class="controls">
				<form:input path="sn" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="sn" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="PaymentTurnover.orderId"/>：</label>
			<div class="controls">
				<form:input path="order" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="order" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="PaymentTurnover.paymentMethodId"/>：</label>
			<div class="controls">
				<form:input path="paymentMethod" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="paymentMethod" cssStyle="color:red"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="PaymentTurnover.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="PaymentTurnover.code"/>：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="25" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="code" cssStyle="color:red"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><spring:message code="PaymentTurnover.status"/>：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="25" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="status" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="PaymentTurnover.paymentFrom"/>：</label>
			<div class="controls">
				<form:input path="paymentFrom" htmlEscape="false" maxlength="25" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="paymentFrom" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="PaymentTurnover.amount"/>：</label>
			<div class="controls">
				<form:input path="amount" htmlEscape="false" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="amount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="PaymentTurnover.tradeType"/>：</label>
			<div class="controls">
				<form:input path="tradeType" htmlEscape="false" maxlength="5" 
				          class="input-xlarge "/>
			    <form:errors path="tradeType" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="PaymentTurnover.buyerId"/>：</label>
			<div class="controls">
				<form:input path="buyer" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="buyer" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="PaymentTurnover.buyerName"/>：</label>
			<div class="controls">
				<form:input path="buyerNickname" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="buyerNickname" cssStyle="color:red"/>
			</div>
		</div>
		<div class="form-actions">
            <shiro:hasPermission name="payment:paymentTurnover:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>