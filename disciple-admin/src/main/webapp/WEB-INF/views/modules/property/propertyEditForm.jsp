<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.property" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/property/property/"><spring:message code="admin.property"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/property/property/form?id=${property.id}">
		    <shiro:hasPermission name="property:property:edit">
		       <spring:message code="admin.property"/>
		       <spring:message code="admin.common.${not empty property.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="property:property:edit">
		           <spring:message code="admin.property"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="property" action="${ctx}/property/property/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="Property.propertyTemplateId"/>：</label>
			<div class="controls">
				<form:input path="propertyTemplate" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="propertyTemplate" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Property.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Property.propertyValues"/>：</label>
			<div class="controls">
				<form:input path="propertyValues" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="propertyValues" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Property.propertyType"/>：</label>
			<div class="controls">
				<form:input path="showType" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="showType" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Property.isSearchAttr"/>：</label>
			<div class="controls">
				<form:checkboxes path="isSearchAttr" items="${fns:getDictList('trueFalse')}" itemLabel="label" itemValue="value" htmlEscape="false" 
				      class=""/>
			
			    <form:errors path="isSearchAttr" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Property.isRequired"/>：</label>
			<div class="controls">
				<form:checkboxes path="isRequired" items="${fns:getDictList('trueFalse')}" itemLabel="label" itemValue="value" htmlEscape="false" 
				      class=""/>
			
			    <form:errors path="isRequired" cssStyle="color:red"/>
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
            <shiro:hasPermission name="property:property:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>