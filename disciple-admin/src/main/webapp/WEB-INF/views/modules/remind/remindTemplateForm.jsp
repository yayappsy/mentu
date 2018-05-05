<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.remindTemplate" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/remind/remindTemplate/"><spring:message code="admin.remindTemplate"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/remind/remindTemplate/form?id=${remindTemplate.id}">
		    <shiro:hasPermission name="remind:remindTemplate:edit">
		       <spring:message code="admin.remindTemplate"/>
		       <spring:message code="admin.common.${not empty remindTemplate.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="remind:remindTemplate:edit">
		           <spring:message code="admin.remindTemplate"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="remindTemplate" action="${ctx}/remind/remindTemplate/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="RemindTemplate.remind"/>：</label>
			<div class="controls">
				<form:input path="remind" htmlEscape="false" maxlength="255" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="remind" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="RemindTemplate.remindWaysId"/>：</label>
			<div class="controls">
				<form:input path="remindWaysId" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="remindWaysId" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="RemindTemplate.addressee"/>：</label>
			<div class="controls">
				<form:input path="addressee" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="addressee" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="RemindTemplate.displayTitle"/>：</label>
			<div class="controls">
				<form:input path="displayTitle" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="displayTitle" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="RemindTemplate.actualTitle"/>：</label>
			<div class="controls">
				<form:input path="actualTitle" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="actualTitle" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="RemindTemplate.displayContent"/>：</label>
			<div class="controls">
				<form:input path="displayContent" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="displayContent" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="DataEntity.remarks"/>：</label>        
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			    <form:errors path="remarks" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="RemindTemplate.actualContent"/>：</label>
			<div class="controls">
				<form:input path="actualContent" htmlEscape="false" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="actualContent" cssStyle="color:red"/>
			</div>
		</div>
		<div class="form-actions">
            <shiro:hasPermission name="remind:remindTemplate:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>