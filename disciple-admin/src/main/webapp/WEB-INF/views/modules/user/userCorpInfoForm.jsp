<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.userCorpInfo" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/user/userCorpInfo/"><spring:message code="admin.userCorpInfo"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/user/userCorpInfo/form?id=${userCorpInfo.id}">
		    <shiro:hasPermission name="user:userCorpInfo:edit">
		       <spring:message code="admin.userCorpInfo"/>
		       <spring:message code="admin.common.${not empty userCorpInfo.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="user:userCorpInfo:edit">
		           <spring:message code="admin.userCorpInfo"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="userCorpInfo" action="${ctx}/user/userCorpInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="UserCorpInfo.userInfoId"/>：</label>
			<div class="controls">
				<form:input path="userInfoId" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="userInfoId" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserCorpInfo.corpName"/>：</label>
			<div class="controls">
				<form:input path="corpName" htmlEscape="false" maxlength="100" 
				          class="input-xlarge "/>
			    <form:errors path="corpName" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserCorpInfo.corpWebsite"/>：</label>
			<div class="controls">
				<form:input path="corpWebsite" htmlEscape="false" maxlength="100" 
				          class="input-xlarge "/>
			    <form:errors path="corpWebsite" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserCorpInfo.corpPhone"/>：</label>
			<div class="controls">
				<form:input path="corpPhone" htmlEscape="false" maxlength="20" 
				          class="input-xlarge "/>
			    <form:errors path="corpPhone" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserCorpInfo.corpJob"/>：</label>
			<div class="controls">
				<form:input path="corpJob" htmlEscape="false" maxlength="100" 
				          class="input-xlarge "/>
			    <form:errors path="corpJob" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserCorpInfo.corpIndustryId"/>：</label>
			<div class="controls">
				<form:input path="corpIndustryId" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="corpIndustryId" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserCorpInfo.corpAddress"/>：</label>
			<div class="controls">
				<weimhc:regionSelect id="corpAddress" name="corpAddress.id"
							value="${userCorpInfo.corpAddress.id}" labelName="corpAddressName"
							labelValue="${userCorpInfo.corpAddressName}"
							cssClass="" allowClear="true"
							notAllowSelectParent="true" />
			    <form:errors path="corpAddress" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserCorpInfo.corpDetailedAddress"/>：</label>
			<div class="controls">
				<form:input path="corpDetailedAddress" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="corpDetailedAddress" cssStyle="color:red"/>
			</div>
		</div>
		<div class="form-actions">
            <shiro:hasPermission name="user:userCorpInfo:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>