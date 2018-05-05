<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.consultation" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/consultation/consultation/"><spring:message code="admin.consultation.list"/></a></li>
		<li class="active"><a href="${ctx}/consultation/consultation/form?id=${consultation.id}">
		    <shiro:hasPermission name="consultation:consultation:edit"><spring:message code="admin.consultation.${not empty consultation.id?'edit':'add'}"/></shiro:hasPermission>
		    <shiro:lacksPermission name="consultation:consultation:edit"><spring:message code="admin.consultation.view"/></shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="consultation" action="${ctx}/consultation/consultation/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${message}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="Consultation.consultant"/>：</label>
			<div class="controls">
				<form:input path="consultant" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="consultant" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Consultation.consultationType"/>：</label>
			<div class="controls">
				<form:input path="consultationType" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="consultationType" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Consultation.content"/>：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="content" cssStyle="color:red"/>
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
            <shiro:hasPermission name="consultation:consultation:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>