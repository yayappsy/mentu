<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.imageAlbum" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
	<script type="text/javascript">
		$(function(){
            //默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
           $("#inputForm").find(".trueFalse").each(function(){
				$(this).bootstrapSwitch();
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/image/imageAlbum/"><spring:message code="admin.imageAlbum"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/image/imageAlbum/form?id=${imageAlbum.id}">
		    <shiro:hasPermission name="image:imageAlbum:edit">
		       <spring:message code="admin.imageAlbum"/>
		       <spring:message code="admin.common.${not empty imageAlbum.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="image:imageAlbum:edit">
		           <spring:message code="admin.imageAlbum"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="imageAlbum" action="${ctx}/image/imageAlbum/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="ImageAlbum.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="admin.common.parent"/><spring:message code="admin.imageAlbum"/>:</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parent.id" value="${imageAlbum.parent.id}" labelName="parent.name" labelValue="${imageAlbum.parent.name}"
					title="<spring:message code='admin.imageAlbum'/>" url="/image/imageAlbum/treeData" extId="${imageAlbum.id}" cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ImageAlbum.grade"/>：</label>
			<div class="controls">
				<form:input path="grade" htmlEscape="false" maxlength="11" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="grade" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ImageAlbum.description"/>：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="description" cssStyle="color:red"/>
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
            <shiro:hasPermission name="image:imageAlbum:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>