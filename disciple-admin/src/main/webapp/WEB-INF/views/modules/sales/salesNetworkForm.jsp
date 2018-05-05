<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.salesNetwork" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<%@include file="/WEB-INF/views/include/amap.jsp"%>
<script src="http://webapi.amap.com/ui/1.0/main.js"></script>
<script type="text/javascript" src="${ctxStatic}/amap/js/positionPicker.js?ver=01"></script>
<script type="text/javascript">
	$(function(){
		//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		$("#inputForm").find(".trueFalse").each(function() {
			$(this).bootstrapSwitch();
		});
	});

	function onSuccess(positionResult) {
		$("#longitude").val(positionResult.position.getLng());
		$("#latitude").val(positionResult.position.getLat());
		$("#detailedAddress").val(positionResult.address);
    }
	 
	 function onFail(positionResult) {
		$("#longitude").val("");
		$("#latitude").val("");
		$("#detailedAddress").val("");
    }
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sales/salesNetwork/"><spring:message
					code="admin.salesNetwork" />
				<spring:message code="admin.common.list" /></a></li>
		<li class="active"><a
			href="${ctx}/sales/salesNetwork/form?id=${salesNetwork.id}"> <shiro:hasPermission
					name="sales:salesNetwork:edit">
					<spring:message code="admin.salesNetwork" />
					<spring:message
						code="admin.common.${not empty salesNetwork.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="sales:salesNetwork:edit">
					<spring:message code="admin.salesNetwork" />
					<spring:message code="admin.common.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="salesNetwork"
		action="${ctx}/sales/salesNetwork/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<weimhc:message resultMessage="${resultMessage}" />
		<div class="control-group">
			<label class="control-label"><spring:message
					code="SalesNetwork.name" />：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="name" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="admin.common.parent" />
				<spring:message code="admin.salesNetwork" />:</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parent.id"
					value="${salesNetwork.parent.id}" labelName=""
					labelValue="${salesNetwork.parent.name}"
					title="<spring:message code='admin.salesNetwork'/>"
					url="/sales/salesNetwork/treeData" extId="${salesNetwork.id}"
					cssClass="" allowClear="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="SalesNetwork.salesNetworkTypeId" />：</label>
			<div class="controls">
				<form:select path="salesNetworkType.id"
					class="input-xlarge required">
					<form:options items="${salesNetworkTypeList }" itemLabel="name"
						itemValue="id" />
				</form:select>
				<form:errors path="salesNetworkType.id" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="SalesNetwork.url" />：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="2000"
					class="input-xlarge " />
				<form:errors path="url" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="SalesNetwork.manager" />：</label>
			<div class="controls">
				<form:input path="manager" htmlEscape="false" maxlength="100"
					class="input-xlarge " />
				<form:errors path="manager" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="SalesNetwork.phone" />：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="20"
					class="input-xlarge phone" />
				<form:errors path="phone" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="SalesNetwork.email" />：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="20"
					class="input-xlarge email " />
				<form:errors path="email" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="SalesNetwork.location" />：</label>
			<div class="controls">
			    <sys:treeselect id="area" name="location.id"
							value="${salesNetwork.location.id}" labelName="location.name"
							labelValue="${salesNetwork.location.name}"
							title="<spring:message code='admin.commmon.area'/>"
							url="/sys/area/treeData" cssClass="required" allowClear="true"
							notAllowSelectParent="true" />
				<form:errors path="location.id" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="定位" />：</label>
			<div class="controls">
				<div id='container'></div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="SalesNetwork.detailedAddress" />：</label>
			<div class="controls">
				<form:hidden path="latitude" />
				<form:hidden path="longitude" />
				<form:input path="detailedAddress" htmlEscape="false"
					maxlength="255" class="input-xlarge " />
				<form:errors path="detailedAddress" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="SalesNetwork.isShow" />：</label>
			<div class="controls">
				<form:checkbox path="isShow"
					data-on-text="${fns:getMessage(languageType, 'admin.common.true')}"
					data-off-text="${fns:getMessage(languageType, 'admin.common.false')}"
					class=" trueFalse" />

				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="isShow" cssStyle="color:red" />
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
			<shiro:hasPermission name="sales:salesNetwork:edit">
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