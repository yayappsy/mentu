<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.companyFile" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<script type="text/javascript">
	$(function(){
		//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		//设置选择样式
		$("#inputForm").find(".trueFalse").each(function() {
			$(this).bootstrapSwitch();
		});
		$("#pathType").on("change",function(){
            var $this = $(this);
            $(".pathType").hide();
            $(".pathType."+$this.val()).show();
		});
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/company/companyFile/"><spring:message
					code="admin.companyFile" />
				<spring:message code="admin.common.list" /></a></li>
		<li class="active"><a
			href="${ctx}/company/companyFile/form?id=${companyFile.id}"> <shiro:hasPermission
					name="company:companyFile:edit">
					<spring:message code="admin.companyFile" />
					<spring:message
						code="admin.common.${not empty companyFile.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="company:companyFile:edit">
					<spring:message code="admin.companyFile" />
					<spring:message code="admin.common.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="companyFile"
		action="${ctx}/company/companyFile/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="extension"/>
		<form:hidden path="size" />
		<weimhc:message resultMessage="${resultMessage}" />
		<div class="control-group">
			<label class="control-label"><spring:message
					code="CompanyFile.name" />：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="name" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="CompanyFile.pathType" />：</label>
			<div class="controls">
				<form:select path="pathType">
					<c:forEach items="${pathTypes}" var="item">
						<form:option value="${item }">
							<spring:message code="PathType.${item }" />
						</form:option>
					</c:forEach>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="pathType" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group pathType local">
			<label class="control-label"><spring:message
					code="CompanyFile.storagePath" />：</label>
			<div class="controls">
				<form:input path="storagePath" htmlEscape="false" maxlength="255"
					class="input-xlarge" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="storagePath" cssStyle="color:red" />
			</div>
		</div>
		
		<div class="control-group pathType remote">
			<label class="control-label"><spring:message
					code="CompanyFile.url" />：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="255"
					class="input-xlarge" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="url" cssStyle="color:red" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label"><spring:message
					code="DataEntity.remarks" />：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
				<form:errors path="remarks" cssStyle="color:red" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="company:companyFile:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="<spring:message code='admin.common.save'/>" />&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button"
				value="<spring:message code='admin.common.back'/>"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>