<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.userHealthInfo" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/user/userHealthInfo/"><spring:message code="admin.userHealthInfo"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/user/userHealthInfo/form?id=${userHealthInfo.id}">
		    <shiro:hasPermission name="user:userHealthInfo:edit">
		       <spring:message code="admin.userHealthInfo"/>
		       <spring:message code="admin.common.${not empty userHealthInfo.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="user:userHealthInfo:edit">
		           <spring:message code="admin.userHealthInfo"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="userHealthInfo" action="${ctx}/user/userHealthInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="UserHealthInfo.userInfoId"/>：</label>
			<div class="controls">
				<form:input path="userInfoId" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="userInfoId" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserHealthInfo.height"/>：</label>
			<div class="controls">
				<form:input path="height" htmlEscape="false" 
				          class="input-xlarge  number"/>
			    <form:errors path="height" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserHealthInfo.weight"/>：</label>
			<div class="controls">
				<form:input path="weight" htmlEscape="false" 
				          class="input-xlarge  number"/>
			    <form:errors path="weight" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserHealthInfo.waistline"/>：</label>
			<div class="controls">
				<form:input path="waistline" htmlEscape="false" 
				          class="input-xlarge  number"/>
			    <form:errors path="waistline" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserHealthInfo.bust"/>：</label>
			<div class="controls">
				<form:input path="bust" htmlEscape="false" 
				          class="input-xlarge  number"/>
			    <form:errors path="bust" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserHealthInfo.hipline"/>：</label>
			<div class="controls">
				<form:input path="hipline" htmlEscape="false" 
				          class="input-xlarge  number"/>
			    <form:errors path="hipline" cssStyle="color:red"/>
			</div>
		</div>
		<div class="form-actions">
            <shiro:hasPermission name="user:userHealthInfo:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>