<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.internalMessage" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/message/internalMessage/"><spring:message code="admin.internalMessage.list"/></a></li>
		<li class="active"><a href="${ctx}/message/internalMessage/form?id=${internalMessage.id}">
		    <shiro:hasPermission name="message:internalMessage:edit"><spring:message code="admin.internalMessage.${not empty internalMessage.id?'edit':'add'}"/></shiro:hasPermission>
		    <shiro:lacksPermission name="message:internalMessage:edit"><spring:message code="admin.internalMessage.view"/></shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="internalMessage" action="${ctx}/message/internalMessage/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${message}"/>		
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="InternalMessage.senderId"/>：</label>
			<div class="controls">
				<form:input path="senderId" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="senderId" cssStyle="color:red"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><spring:message code="InternalMessage.title"/>：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="title" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="InternalMessage.messageType"/>：</label>
			<div class="controls">
				<form:select path="messageType">
				<form:option value=""><spring:message code="admin.common.defaultSelect"/></form:option>
				<c:forEach items="${messageTypes}" var="messageType">
				<form:option value="${messageType}"><spring:message code="MessageType.${messageType}"/></form:option>
				</c:forEach>
				</form:select>
			    <form:errors path="messageType" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="InternalMessage.content"/>：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			    <form:errors path="content" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="InternalMessage.isMass"/>：</label>
			<div class="controls">
			<form:checkbox path="isMass"
						data-on-text="${fns:getMessage(languageType, 'admin.common.true',null)}"
						data-off-text="${fns:getMessage(languageType, 'admin.common.false',null)}"
						class="trueFalse" />
			    <form:errors path="isMass" cssStyle="color:red"/>
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
            <shiro:hasPermission name="message:internalMessage:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>