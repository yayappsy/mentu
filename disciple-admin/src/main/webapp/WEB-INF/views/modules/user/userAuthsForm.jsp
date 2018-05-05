<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.userAuths" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/user/userAuths/"><spring:message code="admin.userAuths.list"/></a></li>
		<li class="active"><a href="${ctx}/user/userAuths/form?id=${userAuths.id}">
		    <shiro:hasPermission name="user:userAuths:edit"><spring:message code="admin.userAuths.${not empty userAuths.id?'edit':'add'}"/></shiro:hasPermission>
		    <shiro:lacksPermission name="user:userAuths:edit"><spring:message code="admin.userAuths.view"/></shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="userAuths" action="${ctx}/user/userAuths/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${message}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="UserAuths.userInfoId"/>：</label>
			<div class="controls">
				<form:input path="userInfo" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="userInfo" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserAuths.identityType"/>：</label>
			<div class="controls">
				<form:input path="identityType" htmlEscape="false" maxlength="25" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="identityType" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserAuths.identifier"/>：</label>
			<div class="controls">
				<form:input path="identifier" htmlEscape="false" maxlength="255" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="identifier" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserAuths.credential"/>：</label>
			<div class="controls">
				<form:input path="credential" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="credential" cssStyle="color:red"/>
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
            <shiro:hasPermission name="user:userAuths:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>