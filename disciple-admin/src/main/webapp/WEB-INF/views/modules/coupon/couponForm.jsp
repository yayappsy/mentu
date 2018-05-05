<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.coupon" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/coupon/coupon/"><spring:message code="admin.coupon"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/coupon/coupon/form?id=${coupon.id}">
		    <shiro:hasPermission name="coupon:coupon:edit">
		       <spring:message code="admin.coupon"/>
		       <spring:message code="admin.common.${not empty coupon.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="coupon:coupon:edit">
		           <spring:message code="admin.coupon"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="coupon" action="${ctx}/coupon/coupon/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="exchangeLimit" value="1">
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="Coupon.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Coupon.description"/>：</label>
			<div class="controls">
			<form:textarea path="description" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			    <form:errors path="description" cssStyle="color:red"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label"><spring:message code="Coupon.priceLimit"/>：</label>
			<div class="controls">
				<form:input path="priceLimit" htmlEscape="false" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="priceLimit" cssStyle="color:red"/>
			</div>
		</div>
	<%-- 	<div class="control-group">
			<label class="control-label"><spring:message code="Coupon.priceExpression"/>：</label>
			<div class="controls">
				<form:input path="priceExpression" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="priceExpression" cssStyle="color:red"/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="Coupon.exchangeLimit"/>：</label>
			<div class="controls">
				
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="exchangeLimit" cssStyle="color:red"/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="Coupon.status"/>：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="25" 
				          class="input-xlarge "/>
			    <form:errors path="status" cssStyle="color:red"/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="Coupon.isEnabled"/>：</label>
			<div class="controls">
			
			<form:select path="isEnabled">
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="isEnabled" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Coupon.isLocked"/>：</label>
			<div class="controls">
			<form:select path="isLocked">
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="isLocked" cssStyle="color:red"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><spring:message code="Coupon.quantity"/>：</label>
			<div class="controls">
				<form:input path="quantity" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="quantity" cssStyle="color:red"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="Coupon.point"/>：</label>
			<div class="controls">
				<form:input path="point" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="point" cssStyle="color:red"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><spring:message code="Coupon.beginDate"/>：</label>
			<div class="controls">
				<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${coupon.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			    <form:errors path="beginDate" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Coupon.endDate"/>：</label>
			<div class="controls">
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${coupon.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			    <form:errors path="endDate" cssStyle="color:red"/>
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
            <shiro:hasPermission name="coupon:coupon:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>