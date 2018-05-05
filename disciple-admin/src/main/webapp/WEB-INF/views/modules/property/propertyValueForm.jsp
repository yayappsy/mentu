<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.propertyValue" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/property/propertyValue/"><spring:message code="admin.propertyValue"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/property/propertyValue/form?id=${propertyValue.id}">
		    <shiro:hasPermission name="property:propertyValue:edit">
		       <spring:message code="admin.propertyValue"/>
		       <spring:message code="admin.common.${not empty propertyValue.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="property:propertyValue:edit">
		           <spring:message code="admin.propertyValue"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="propertyValue" action="${ctx}/property/propertyValue/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="PropertyValue.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="PropertyValue.propertyId"/>：</label>
			<div class="controls">
				<form:input path="propertyId" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="propertyId" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="PropertyValue.propertyName"/>：</label>
			<div class="controls">
				<form:input path="propertyName" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="propertyName" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="PropertyValue.propertyTemplateId"/>：</label>
			<div class="controls">
				<form:input path="propertyTemplateId" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="propertyTemplateId" cssStyle="color:red"/>
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
            <shiro:hasPermission name="property:propertyValue:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>