<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.userInfo" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/user/userInfo/"><spring:message code="admin.userInfo.list"/></a></li>
		<li class="active"><a href="${ctx}/user/userInfo/form?id=${userInfo.id}">
		    <shiro:hasPermission name="user:userInfo:edit"><spring:message code="admin.userInfo.${not empty userInfo.id?'edit':'add'}"/></shiro:hasPermission>
		    <shiro:lacksPermission name="user:userInfo:edit"><spring:message code="admin.userInfo.view"/></shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="userInfo" action="${ctx}/user/userInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.sn"/>：</label>
			<div class="controls">
				<form:input path="sn" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="sn" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.username"/>：</label>
			<div class="controls">
				<form:input path="username" htmlEscape="false" maxlength="100" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="username" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.password"/>：</label>
			<div class="controls">
				<form:input path="password" htmlEscape="false" maxlength="255" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="password" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.nickname"/>：</label>
			<div class="controls">
				<form:input path="nickname" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="nickname" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.avatar"/>：</label>
			<div class="controls">
				<form:input path="avatar" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="avatar" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.birth"/>：</label>
			<div class="controls">
				<input name="birth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${userInfo.birth}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			    <form:errors path="birth" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.email"/>：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="email" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.gender"/>：</label>
			<div class="controls">
				<form:radiobuttons path="gender" items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			    <form:errors path="gender" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.mobile"/>：</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="20" 
				          class="input-xlarge "/>
			    <form:errors path="mobile" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.phone"/>：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="20" 
				          class="input-xlarge "/>
			    <form:errors path="phone" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.birthplaceId"/>：</label>
			<div class="controls">
				<form:input path="birthplaceId" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="birthplaceId" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.identityCard"/>：</label>
			<div class="controls">
				<form:input path="identityCard" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="identityCard" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.religiousBelief"/>：</label>
			<div class="controls">
				<form:input path="religiousBelief" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="religiousBelief" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.nation"/>：</label>
			<div class="controls">
				<form:input path="nation" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="nation" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.qq"/>：</label>
			<div class="controls">
				<form:input path="qq" htmlEscape="false" maxlength="20" 
				          class="input-xlarge "/>
			    <form:errors path="qq" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.residenceId"/>：</label>
			<div class="controls">
				<form:input path="residenceId" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="residenceId" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.registerIp"/>：</label>
			<div class="controls">
				<form:input path="registerIp" htmlEscape="false" maxlength="255" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="registerIp" cssStyle="color:red"/>
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
            <shiro:hasPermission name="user:userInfo:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>