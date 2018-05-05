<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.mailConfig" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<script type="text/javascript">
	$(function(){
		//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		//设置选择样式
		var $inputForm = $("#mailConfigForm");
		$inputForm.validate();
	});
</script>
</head>
<body>
	<br />
	<form:form id="mailConfigForm" modelAttribute="mailConfig"
		action="${ctx}/base/mailConfig/ajaxSave" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<weimhc:message resultMessage="${resultMessage}" />
		<div class="control-group">
			<label class="control-label"><spring:message
					code="MailConfig.address" />：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="255"
					class="input-xlarge required email" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="MailConfig.password" />：</label>
			<div class="controls">
				<form:password path="newPassword" htmlEscape="false" maxlength="255"
					class="input-xlarge" />
				<span class="help-inline"><font color="red">*</font>如果不修改密码，请不要录入 </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="MailConfig.displayName" />：</label>
			<div class="controls">
				<form:input path="displayName" htmlEscape="false" maxlength="100"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="MailConfig.smtpAddress" />：</label>
			<div class="controls">
				<form:input path="smtpAddress" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
			</div>
		</div>
	</form:form>
</body>
</html>