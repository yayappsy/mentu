<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.receivedMessage" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/message/receivedMessage/"><spring:message code="admin.receivedMessage.list"/></a></li>
		<li class="active"><a href="${ctx}/message/receivedMessage/form?id=${receivedMessage.id}">
		    <shiro:hasPermission name="message:receivedMessage:edit"><spring:message code="admin.receivedMessage.${not empty receivedMessage.id?'edit':'add'}"/></shiro:hasPermission>
		    <shiro:lacksPermission name="message:receivedMessage:edit"><spring:message code="admin.receivedMessage.view"/></shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="receivedMessage" action="${ctx}/message/receivedMessage/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${message}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="ReceivedMessage.messageId"/>：</label>
			<div class="controls">
				<form:input path="message.title" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="message" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ReceivedMessage.recipientId"/>：</label>
			<div class="controls">
				<form:input path="recipientId" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="recipientId" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ReceivedMessage.isRead"/>：</label>
			<div class="controls">
				<form:input path="isRead" htmlEscape="false" maxlength="1" 
				          class="input-xlarge "/>
			    <form:errors path="isRead" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ReceivedMessage.isFavorite"/>：</label>
			<div class="controls">
				<form:input path="isFavorite" htmlEscape="false" maxlength="1" 
				          class="input-xlarge "/>
			    <form:errors path="isFavorite" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="DataEntity.remarks"/>：</label>        
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" class="input-xxlarge "/>
			    <form:errors path="remarks" cssStyle="color:red"/>
			</div>
		</div>
		<div class="form-actions">
            <shiro:hasPermission name="message:receivedMessage:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>