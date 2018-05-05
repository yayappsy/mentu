<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.activity" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<%@ include file="/WEB-INF/views/include/ueditor.jsp"%>
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
		<li><a href="${ctx}/activity/activity/"><spring:message
					code="admin.activity" /> <spring:message code="admin.common.list" /></a></li>
		<li class="active"><a
			href="${ctx}/activity/activity/form?id=${activity.id}"> <shiro:hasPermission
					name="activity:activity:edit">
					<spring:message code="admin.activity" />
					<spring:message
						code="admin.common.${not empty activity.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="activity:activity:edit">
					<spring:message code="admin.activity" />
					<spring:message code="admin.common.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="activity"
		action="${ctx}/activity/activity/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<weimhc:message resultMessage="${resultMessage}" />
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Activity.name" />：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<form:errors path="name" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Activity.title" />：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="title" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Activity.address" />：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<form:errors path="address" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Activity.detail" />：</label>
			<div class="controls">
				<form:textarea path="detail" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
				<form:errors path="detail" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Activity.activityTypeId" />：</label>
			<div class="controls">
				<form:select path="activityType.id">
					<form:options items="${activityTypes }" itemLabel="name"
						itemValue="id" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Activity.imgUrl" />：</label>
			<div class="controls">
				<form:hidden path="imgUrl" htmlEscape="false" id="imgUrl"
					maxlength="255" class="input-xlarge" />
				<weimhc:selectImage input="imgUrl" />
				<span class="help-inline"><font color="red">*</font></span>
				<form:errors path="imgUrl" cssStyle="color:red" />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="Activity.background"/>：</label>
			<div class="controls">
				<form:input path="background" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="background" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Activity.flow"/>：</label>
			<div class="controls">
				<form:input path="flow" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="flow" cssStyle="color:red"/>
			</div>
		</div> --%>

		<div class="control-group">
			<label class="control-label"><spring:message
					code="Activity.applyBeginDate" />：</label>
			<div class="controls">
				<input name="applyBeginDate" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${activity.applyBeginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				<form:errors path="applyBeginDate" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Activity.applyEndDate" />：</label>
			<div class="controls">
				<input name="applyEndDate" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${activity.applyEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				<form:errors path="applyEndDate" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Activity.numberLimit" />：</label>
			<div class="controls">
				<form:input path="numberLimit" htmlEscape="false" maxlength="11"
					class="input-small  integer" />
				<form:errors path="numberLimit" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Activity.beginDate" />：</label>
			<div class="controls">
				<input name="beginDate" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${activity.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				<form:errors path="beginDate" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Activity.endDate" />：</label>
			<div class="controls">
				<input name="endDate" type="text" readonly="readonly" maxlength="20"
					class="input-medium Wdate "
					value="<fmt:formatDate value="${activity.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				<form:errors path="endDate" cssStyle="color:red" />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="Activity.url"/>：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="url" cssStyle="color:red"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label"><spring:message code="Activity.isRecommend"/>：</label>
			<div class="controls">
				 <form:checkbox path="isRecommend"
						data-on-text="${fns:getMessage(languageType, 'admin.common.true')}"
						data-off-text="${fns:getMessage(languageType, 'admin.common.false')}"
						class="trueFalse" />
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="isRecommend" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Activity.isHot"/>：</label>
			<div class="controls">
				 <form:checkbox path="isHot"
						data-on-text="${fns:getMessage(languageType, 'admin.common.true')}"
						data-off-text="${fns:getMessage(languageType, 'admin.common.false')}"
						class="trueFalse" />
			    <form:errors path="isHot" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Activity.recommendImage"/>：</label>
			<div class="controls">
				<form:input path="recommendImage" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="recommendImage" cssStyle="color:red"/>
			</div>
		</div> --%>
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
			<shiro:hasPermission name="activity:activity:edit">
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