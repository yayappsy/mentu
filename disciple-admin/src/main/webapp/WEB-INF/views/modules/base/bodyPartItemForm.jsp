<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.bodyPartItem" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/base/bodyPartItem/"><spring:message code="admin.bodyPartItem.list"/></a></li>
		<li class="active"><a href="${ctx}/base/bodyPartItem/form?id=${bodyPartItem.id}">
		    <shiro:hasPermission name="base:bodyPartItem:edit"><spring:message code="admin.bodyPartItem.${not empty bodyPartItem.id?'edit':'add'}"/></shiro:hasPermission>
		    <shiro:lacksPermission name="base:bodyPartItem:edit"><spring:message code="admin.bodyPartItem.view"/></shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bodyPartItem" action="${ctx}/base/bodyPartItem/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${message}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="BodyPartItem.bodyPartId"/>：</label>
			<div class="controls">
				<form:input path="bodyPart" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="bodyPart" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="BodyPartItem.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="BodyPartItem.description"/>：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="description" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="BodyPartItem.sort"/>：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="sort" cssStyle="color:red"/>
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
            <shiro:hasPermission name="base:bodyPartItem:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>