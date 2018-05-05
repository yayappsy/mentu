<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.userLoginAuthorization" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/user/userLoginAuthorization/"><spring:message code="admin.userLoginAuthorization"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/user/userLoginAuthorization/form?id=${userLoginAuthorization.id}">
		    <shiro:hasPermission name="user:userLoginAuthorization:edit">
		       <spring:message code="admin.userLoginAuthorization"/>
		       <spring:message code="admin.common.${not empty userLoginAuthorization.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="user:userLoginAuthorization:edit">
		           <spring:message code="admin.userLoginAuthorization"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="userLoginAuthorization" action="${ctx}/user/userLoginAuthorization/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="UserLoginAuthorization.userInfoId"/>：</label>
			<div class="controls">
				<form:input path="userInfoId" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="userInfoId" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserLoginAuthorization.businessSystem"/>：</label>
			<div class="controls">
				<form:input path="businessSystem" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="businessSystem" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserLoginAuthorization.ifEnabled"/>：</label>
			<div class="controls">
				<form:input path="ifEnabled" htmlEscape="false" maxlength="1" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="ifEnabled" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserLoginAuthorization.ifLocked"/>：</label>
			<div class="controls">
				<form:input path="ifLocked" htmlEscape="false" maxlength="1" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="ifLocked" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserLoginAuthorization.lockedDate"/>：</label>
			<div class="controls">
				<input name="lockedDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${userLoginAuthorization.lockedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			    <form:errors path="lockedDate" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserLoginAuthorization.loginDate"/>：</label>
			<div class="controls">
				<input name="loginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${userLoginAuthorization.loginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			    <form:errors path="loginDate" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserLoginAuthorization.loginNum"/>：</label>
			<div class="controls">
				<form:input path="loginNum" htmlEscape="false" maxlength="11" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="loginNum" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserLoginAuthorization.loginFailureCount"/>：</label>
			<div class="controls">
				<form:input path="loginFailureCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="loginFailureCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserLoginAuthorization.loginIp"/>：</label>
			<div class="controls">
				<form:input path="loginIp" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="loginIp" cssStyle="color:red"/>
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
            <shiro:hasPermission name="user:userLoginAuthorization:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>