<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.mailConfig" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/base/mailConfig/"><spring:message code="admin.mailConfig"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/base/mailConfig/form?id=${mailConfig.id}">
		    <shiro:hasPermission name="base:mailConfig:edit">
		       <spring:message code="admin.mailConfig"/>
		       <spring:message code="admin.common.${not empty mailConfig.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="base:mailConfig:edit">
		           <spring:message code="admin.mailConfig"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mailConfig" action="${ctx}/base/mailConfig/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="MailConfig.address"/>：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="255" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="address" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MailConfig.password"/>：</label>
			<div class="controls">
				<form:input path="password" htmlEscape="false" maxlength="255" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="password" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MailConfig.displayName"/>：</label>
			<div class="controls">
				<form:input path="displayName" htmlEscape="false" maxlength="100" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="displayName" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MailConfig.pop3Address"/>：</label>
			<div class="controls">
				<form:input path="pop3Address" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="pop3Address" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MailConfig.smtpAddress"/>：</label>
			<div class="controls">
				<form:input path="smtpAddress" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="smtpAddress" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MailConfig.imapAddress"/>：</label>
			<div class="controls">
				<form:input path="imapAddress" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="imapAddress" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MailConfig.charset"/>：</label>
			<div class="controls">
				<form:input path="charset" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="charset" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MailConfig.loginUrl"/>：</label>
			<div class="controls">
				<form:input path="loginUrl" htmlEscape="false" maxlength="255" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="loginUrl" cssStyle="color:red"/>
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
            <shiro:hasPermission name="base:mailConfig:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>