<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.orderInstalment" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/order/orderInstalment/"><spring:message code="admin.orderInstalment"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/order/orderInstalment/form?id=${orderInstalment.id}">
		    <shiro:hasPermission name="order:orderInstalment:edit">
		       <spring:message code="admin.orderInstalment"/>
		       <spring:message code="admin.common.${not empty orderInstalment.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="order:orderInstalment:edit">
		           <spring:message code="admin.orderInstalment"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="orderInstalment" action="${ctx}/order/orderInstalment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="OrderInstalment.orderId"/>：</label>
			<div class="controls">
				<form:input path="order" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="order" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="OrderInstalment.productId"/>：</label>
			<div class="controls">
				<form:input path="product" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="product" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="OrderInstalment.productName"/>：</label>
			<div class="controls">
				<form:input path="productName" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="productName" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="OrderInstalment.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" 
				          class="input-xlarge "/>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="OrderInstalment.description"/>：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="description" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="OrderInstalment.price"/>：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="price" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="OrderInstalment.isOver"/>：</label>
			<div class="controls">
				<form:input path="isOver" htmlEscape="false" maxlength="1" 
				          class="input-xlarge "/>
			    <form:errors path="isOver" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="OrderInstalment.expire"/>：</label>
			<div class="controls">
				<input name="expire" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${orderInstalment.expire}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			    <form:errors path="expire" cssStyle="color:red"/>
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
            <shiro:hasPermission name="order:orderInstalment:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>