<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.remind" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<script type="text/javascript">
	$(function(){
		//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		//设置选择样式
		var $inputForm = $("#inputForm");
		$inputForm.find(".trueFalse").each(function() {
			$(this).bootstrapSwitch();
		});
		$inputForm.find(".remindWay").on("click",function(){
			var $this = $(this);
			var remindWayData = $this.data();
			var r = new Date().getTime();  
			layer.open({
				title : remindWayData.name,
				type : 2,
				area : [ '800px', '600px' ],
				shadeClose : true, //点击遮罩关闭
				content : '${ctx}/remind/remindTemplate/form?id='
					+remindWayData.remindTemplateId+"&r="+r
			});
		});

	});
</script>

</head>

<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/remind/remind/setList"><spring:message
					code="admin.remind" /> <spring:message code="admin.common.list" /></a></li>
		<li class="active"><a
			href="${ctx}/remind/remind/setForm?id=${remind.id}"> <shiro:hasPermission
					name="remind:remind:edit">
					<spring:message code="admin.remind" />
					<spring:message
						code="admin.common.${not empty remind.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="remind:remind:edit">
					<spring:message code="admin.remind" />
					<spring:message code="admin.common.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="remind"
		action="${ctx}/remind/remind/setSave" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<weimhc:message resultMessage="${resultMessage}" />
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Remind.name" />：</label>
			<div class="controls">${remind.name }</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Remind.description" />：</label>
			<div class="controls">${remind.description }</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Remind.targetType" />：</label>
			<div class="controls">
				<spring:message code="Remind.TargetType.${remind.targetType }" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="admin.remind.settings" />：</label>
			<div id="remindTemplateList" class="controls">
					<c:forEach items="${remind.remindTemplateList}" var="remindTemplate" varStatus="rtStatus">
				        <c:set var="remindWay" value="${remindTemplate.remindWay}"></c:set>
						
							<a class="btn btn-primary remindWay" href="javascript:;" data-remind-template-id="${remindTemplate.id }"
							      data-id="${remindWay.id }" data-name="${remindWay.name }">
							      ${remindWay.name }<spring:message code="admin.remind.settings" />
							</a>
						
					</c:forEach>
				</ul>
			</div>
		</div>
	</form:form>
</body>
</html>