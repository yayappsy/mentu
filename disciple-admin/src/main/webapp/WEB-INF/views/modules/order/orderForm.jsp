<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.order" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/order/order/"><spring:message code="admin.order.list"/></a></li>
		<li class="active"><a href="${ctx}/order/order/form?id=${order.id}">
		    <shiro:hasPermission name="order:order:edit"><spring:message code="admin.order.${not empty order.id?'edit':'add'}"/></shiro:hasPermission>
		    <shiro:lacksPermission name="order:order:edit"><spring:message code="admin.order.view"/></shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="order" action="${ctx}/order/order/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.sn"/>：</label>
			<div class="controls">
				<form:input path="sn" htmlEscape="false" maxlength="100" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="sn" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.memberId"/>：</label>
			<div class="controls">
				<form:input path="member.id" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="member.id" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.memberNickname"/>：</label>
			<div class="controls">
				<form:input path="memberNickname" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="memberNickname" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.detailedAddress"/>：</label>
			<div class="controls">
				<form:input path="detailedAddress" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="detailedAddress" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.contactName"/>：</label>
			<div class="controls">
				<form:input path="contactName" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="contactName" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.mobile"/>：</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="mobile" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.expire"/>：</label>
			<div class="controls">
				<input name="expire" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${order.expire}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			    <form:errors path="expire" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.status"/>：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="25" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="status" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.freight"/>：</label>
			<div class="controls">
				<form:input path="freight" htmlEscape="false" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="freight" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.amountPayable"/>：</label>
			<div class="controls">
				<form:input path="amountPayable" htmlEscape="false" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="amountPayable" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.totalAmount"/>：</label>
			<div class="controls">
				<form:input path="totalAmount" htmlEscape="false" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="totalAmount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.paymentStatus"/>：</label>
			<div class="controls">
				<form:input path="paymentStatus" htmlEscape="false" maxlength="25" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="paymentStatus" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.paymentDate"/>：</label>
			<div class="controls">
				<input name="paymentDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${order.paymentDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="paymentDate" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.paymentMethodId"/>：</label>
			<div class="controls">
				<form:input path="paymentMethodId" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="paymentMethodId" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.paymentMethodName"/>：</label>
			<div class="controls">
				<form:input path="paymentMethodName" htmlEscape="false" maxlength="255" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="paymentMethodName" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.paymentMethodCode"/>：</label>
			<div class="controls">
				<form:input path="paymentMethodCode" htmlEscape="false" maxlength="255" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="paymentMethodCode" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Order.orderRemarks"/>：</label>
			<div class="controls">
				<form:input path="orderRemarks" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="orderRemarks" cssStyle="color:red"/>
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
            <shiro:hasPermission name="order:order:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>