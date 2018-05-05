<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.suggestion" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/suggestion/suggestion/"><spring:message code="admin.suggestion.list"/></a></li>
		<li class="active"><a href="${ctx}/suggestion/suggestion/form?id=${suggestion.id}">
		    <shiro:hasPermission name="suggestion:suggestion:edit"><spring:message code="admin.suggestion.${not empty suggestion.id?'edit':'add'}"/></shiro:hasPermission>
		    <shiro:lacksPermission name="suggestion:suggestion:edit"><spring:message code="admin.suggestion.view"/></shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="suggestion" action="${ctx}/suggestion/suggestion/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="member.id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="Suggestion.memberId"/>：</label>
			<div class="controls">
				<form:input path="memberId" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="memberId" cssStyle="color:red"/>
			</div>
		</div>	 --%>
		<div class="control-group">
			<label class="control-label"><spring:message code="Suggestion.memberNickname"/>：</label>
			<div class="controls">
				<form:input path="memberNickname" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="memberNickname" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Suggestion.suggestionSubjectId"/>：</label>
			<div class="controls">
				<form:select path="suggestionSubject" class="input-xlarge ">
					<form:option value=""><spring:message code="admin.common.defaultSelect"/></form:option>
					<form:options items="${suggestionSubjectList}" itemLabel="subjectTitle" itemValue="id" htmlEscape="false"/>
				</form:select>
			    <form:errors path="suggestionSubjectId" cssStyle="color:red"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label"><spring:message code="Suggestion.content"/>：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="content" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Suggestion.mobile"/>：</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="20" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="mobile" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Suggestion.images"/>：</label>
			<div class="controls">
				<form:input path="images" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="images" cssStyle="color:red"/>
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
           <%--  <shiro:hasPermission name="suggestion:suggestion:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission> --%>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>