<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.setting" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
	<script type="text/javascript">
		$(function(){
                        //默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/setting/setting/"><spring:message code="admin.setting.list"/></a></li>
		<li class="active"><a href="${ctx}/setting/setting/form?id=${setting.id}">
		    <shiro:hasPermission name="setting:setting:edit"><spring:message code="admin.setting.${not empty setting.id?'edit':'add'}"/></shiro:hasPermission>
		    <shiro:lacksPermission name="setting:setting:edit"><spring:message code="admin.setting.view"/></shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="setting" action="${ctx}/setting/setting/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="Setting.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Setting.label"/>：</label>
			<div class="controls">
				<form:input path="label" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Setting.type"/>：</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Setting.description"/>：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Setting.value"/>：</label>
			<div class="controls">
				<form:input path="value" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
            <shiro:hasPermission name="setting:setting:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>