<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.adPosition" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/ad/adPosition/"><spring:message code="admin.adPosition"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/ad/adPosition/form?id=${adPosition.id}">
		    <shiro:hasPermission name="ad:adPosition:edit">
		       <spring:message code="admin.adPosition"/>
		       <spring:message code="admin.common.${not empty adPosition.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="ad:adPosition:edit">
		           <spring:message code="admin.adPosition"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="adPosition" action="${ctx}/ad/adPosition/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="AdPosition.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="AdPosition.code"/>：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="50" 
				          class="input-xlarge "/>
			    <form:errors path="code" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="AdPosition.description"/>：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="description" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="AdPosition.adType"/>：</label>
			<div class="controls">
				<form:input path="adType" htmlEscape="false" maxlength="25" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="adType" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="AdPosition.properHeight"/>：</label>
			<div class="controls">
				<form:input path="properHeight" htmlEscape="false" maxlength="11" 
				          class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="properHeight" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="AdPosition.properWidth"/>：</label>
			<div class="controls">
				<form:input path="properWidth" htmlEscape="false" maxlength="11" 
				          class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="properWidth" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="AdPosition.maxNumber"/>：</label>
			<div class="controls">
				<form:input path="maxNumber" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="maxNumber" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="AdPosition.isEnabled"/>：</label>
			<div class="controls">
				<form:checkbox path="isEnabled" data-on-text="${fns:getMessage(languageType, 'admin.common.true')}"
			         data-off-text="${fns:getMessage(languageType, 'admin.common.false')}"
			          class="required trueFalse"/>			   
			
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="isEnabled" cssStyle="color:red"/>
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
            <shiro:hasPermission name="ad:adPosition:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>