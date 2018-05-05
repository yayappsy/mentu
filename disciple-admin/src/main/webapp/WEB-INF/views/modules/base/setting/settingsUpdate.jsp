<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.setting" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(function(){
		$("#${type}").addClass("active");
		$("#inputForm").validate();
	});
</script>
<style type="text/css">
.bar {
	height: 18px;
	background: green;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li id="site" class="active"><a
			href="${ctx}/setting/setting/site"><spring:message
					code="admin.setting.${type}" /></a></li>
		<li id="productImage" class="hide"><a
			href="${ctx}/setting/setting/productImage"><spring:message
					code="admin.setting.productImage" /></a></li>
		<li id="point" class="hide"><a
			href="${ctx}/setting/setting/point"><spring:message
					code="admin.setting.point" /></a></li>
		<li id="member" class="hide"><a
			href="${ctx}/setting/setting/member"><spring:message
					code="admin.setting.member" /></a></li>
		<li id="mail" class="hide"><a href="${ctx}/setting/setting/mail"><spring:message
					code="admin.setting.mail" /></a></li>
		<%-- <shiro:hasPermission name="setting:setting:edit"><li><a href="${ctx}/setting/setting/form">
		   <spring:message code="admin.setting.add"/></a></li>
		</shiro:hasPermission> --%>
	</ul>

	<weimhc:message resultMessage="${resultMessage}" />
	<form:form id="inputForm" modelAttribute="setting"
		action="${ctx}/setting/setting/update" method="post"
		class="form-horizontal">
		<input type="hidden" name="type" value="${type}" />
		<c:forEach items="${settings }" var="setting" varStatus="setVar">
			<c:if test="${setting.isEditable}">
				<div class="control-group">
					<label class="control-label">${setting.label }</label>
					<div class="controls">
						<input id="${setting.name}" type="text"
							value="${fns:getSettingValue(setting.name,type)}"
							name="settings[${setVar.index}].value" maxlength="200"
							class="input-xlarge ${setting.isRequired?'required':'' }" /> <input
							type="hidden" value="${setting.name}"
							name="settings[${setVar.index}].name" /> <span
							class="help-inline"> <c:if test="${setting.isRequired}">
								<font color="red">*</font>
							</c:if> ${setting.description}

						</span>
					</div>
				</div>
			</c:if>
		</c:forEach>


		<div class="form-actions">
			<shiro:hasPermission name="setting:setting:edit">
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