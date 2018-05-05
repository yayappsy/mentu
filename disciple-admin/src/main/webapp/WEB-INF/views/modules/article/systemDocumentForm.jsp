<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.systemDocument" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
    <%@ include file="/WEB-INF/views/include/ueditor.jsp"%>
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
		<li><a href="${ctx}/article/systemDocument/"><spring:message code="admin.systemDocument.list"/></a></li>
		<li class="active"><a href="${ctx}/article/systemDocument/form?id=${systemDocument.id}">
		    <shiro:hasPermission name="article:systemDocument:edit"><spring:message code="admin.systemDocument.${not empty systemDocument.id?'edit':'add'}"/></shiro:hasPermission>
		    <shiro:lacksPermission name="article:systemDocument:edit"><spring:message code="admin.systemDocument.view"/></shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="systemDocument" action="${ctx}/article/systemDocument/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${message}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="SystemDocument.title"/>：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="title" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="SystemDocument.code"/>：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
				<span class="help-inline">不要修改该代码！ </span>
			    <form:errors path="code" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group" style="height: auto;">
			<label class="control-label"><spring:message
					code="SystemDocument.content" />：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false"
					id="j_ueditorupload" rows="4" style="display: inline-flex;" />
				<weimhc:ueditor ueContainer="j_ueditorupload" uploadPath="/article"></weimhc:ueditor>
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
            <shiro:hasPermission name="article:systemDocument:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>