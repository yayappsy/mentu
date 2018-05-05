<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.remind" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(function(){

	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/remind/remind/form?id=${remind.id}"> 业务提醒 </a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="remind"
		action="${ctx}/remind/remind/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<weimhc:message resultMessage="${message}" />
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Remind.name" />：</label>
			<div class="controls">${remind.name }</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Remind.targetType" />：</label>
			<div class="controls">
				<c:if test="${ remind.targetType ne null}">
					<spring:message code="Remind.TargetType.${remind.targetType}" />
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="admin.remind.ways" />：</label>
			<div class="controls">
				<input type="checkbox" name="" value=""><label>邮件提醒</label>
				<input type="checkbox" name="" value=""><label>短信提醒</label>
				<input type="checkbox" name="" value=""><label>站内信</label>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><spring:message
					code="admin.remind.templates" />：</label>
			<div class="controls">
				<div class="tabbable" id="tabs-895504">
					<ul class="nav nav-tabs">
						<c:forEach items="${remind.remindTemplateList }"
							var="remindTemplate" varStatus="tempStatus">
							<li class="${tempStatus.first?'active':'' }"><a
								href="#panel-${remindTemplate.id}" data-toggle="tab">
									${remindTemplate.remindWay.name} </a></li>
						</c:forEach>
					</ul>
					<div class="tab-content">
						<c:forEach items="${remind.remindTemplateList }"
							var="remindTemplate" varStatus="tempStatus">
							<div class="tab-pane ${tempStatus.first?'active':'' }" id="panel-${remindTemplate.id }">
								<input type="hidden"
									id="remindTemplateList[${tempStatus.index}]_id"
									name="remindTemplateList[${tempStatus.index}].id"
									value="${ remindTemplate.id}">
								<div class="control-group">
									<label class="control-label"><spring:message
											code="RemindTemplate.displayContent" />：</label>
									<div class="controls">
										<input type="text"
											id="remindTemplateList[${tempStatus.index}]_displayContent"
											name="remindTemplateList[${tempStatus.index}].displayContent"
											value="${ remindTemplate.displayContent}">
									</div>
								</div>

							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>

		<footer>
			<div class="form-actions">
				<shiro:hasPermission name="remind:remind:edit">
					<input id="btnSubmit" class="btn btn-primary" type="submit"
						value="<spring:message code='admin.common.save'/>" />&nbsp;
			            </shiro:hasPermission>
				<input id="btnCancel" class="btn" type="button"
					value="<spring:message code='admin.common.back'/>"
					onclick="history.go(-1)" />
			</div>
		</footer>
	</form:form>
</body>
</html>