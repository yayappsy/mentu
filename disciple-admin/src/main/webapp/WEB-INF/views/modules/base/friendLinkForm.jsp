<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.friendLink" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<script type="text/javascript">
	$(function(){
		//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		//设置选择样式
		$("#inputForm").find(".trueFalse").each(function() {
			$(this).bootstrapSwitch();
		});
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/base/friendLink/"><spring:message
					code="admin.friendLink.list" /></a></li>
		<li class="active"><a
			href="${ctx}/base/friendLink/form?id=${friendLink.id}"> <shiro:hasPermission
					name="base:friendLink:edit">
					<spring:message
						code="admin.friendLink.${not empty friendLink.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="base:friendLink:edit">
					<spring:message code="admin.friendLink.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="friendLink"
		action="${ctx}/base/friendLink/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<weimhc:message resultMessage="${resultMessage}" />
		<div class="control-group">
			<label class="control-label"><spring:message
					code="FriendLink.name" />：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="name" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="FriendLink.describe" />：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255"
					class="input-xlargbe" />
				<form:errors path="description" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="DataEntity.sort" />：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="64"
					class="input-xlarge digit" />
				<form:errors path="sort" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="FriendLink.url" />：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="255"
					class="input-xlarge required" />
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
			<shiro:hasPermission name="base:friendLink:edit">
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