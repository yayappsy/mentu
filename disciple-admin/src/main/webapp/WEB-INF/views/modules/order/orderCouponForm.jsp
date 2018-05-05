<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.orderCoupon" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/order/orderCoupon/"><spring:message code="admin.orderCoupon"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/order/orderCoupon/form?id=${orderCoupon.id}">
		    <shiro:hasPermission name="order:orderCoupon:edit">
		       <spring:message code="admin.orderCoupon"/>
		       <spring:message code="admin.common.${not empty orderCoupon.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="order:orderCoupon:edit">
		           <spring:message code="admin.orderCoupon"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="orderCoupon" action="${ctx}/order/orderCoupon/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="OrderCoupon.orderId"/>：</label>
			<div class="controls">
				<form:input path="order" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="order" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="OrderCoupon.couponId"/>：</label>
			<div class="controls">
				<form:input path="coupon" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="coupon" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="OrderCoupon.couponName"/>：</label>
			<div class="controls">
				<form:input path="couponName" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="couponName" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="OrderCoupon.quantity"/>：</label>
			<div class="controls">
				<form:input path="quantity" htmlEscape="false" maxlength="11" 
				          class="input-xlarge "/>
			    <form:errors path="quantity" cssStyle="color:red"/>
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
            <shiro:hasPermission name="order:orderCoupon:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>