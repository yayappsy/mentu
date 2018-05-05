<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%--通过列表选择实体 --%>
<%@ attribute name="applicationStatus" type="java.lang.String"
	required="true" description="申请状态"%>
<div class="control-group">
	<label class="control-label"><spring:message
			code="admin.common.currentApplicationStatus" />：</label>
	<div class="controls">
		<spring:message code='ApplicationStatus.${applicationStatus }' />
		<span class="help-inline"><font color="red"></font></span> <span
			class="tips-important"> 只有状态为待审核时才允许改变状态</span>
	</div>
</div>

<c:set var="ifAudit" value="${'pending' eq applicationStatus}" />
<div id="auditStatus" class="control-group">
	<label class="control-label"><spring:message
			code="CxGroupApplication.applicationStatus" />：</label>
	<div class="controls">
		<form:radiobutton path="applicationStatus" value="agree"
			class="required audit"  htmlEscape="false"
			label="同意" />
		<form:radiobutton path="applicationStatus" value="refuse"
			class="required audit"  htmlEscape="false"
			label="拒绝" />
		<span class="help-inline"><font color="red">*</font> </span>
		<form:errors path="applicationStatus" cssStyle="color:red" />
	</div>
</div>

<div id="refuseReasonContainer" class="control-group hide">
	<label class="control-label"><spring:message
			code="CxGroupApplication.refuseReason" />：</label>
	<div class="controls">
		<form:textarea path="refuseReason" htmlEscape="false" rows="4"
			 maxlength="255" class="input-xlarge audit" />
		<span class="help-inline"><font color="red">* </font><span
			class="importantTips"></span></span>
		<form:errors path="refuseReason" cssStyle="color:red" />
	</div>
</div>
<script type="text/javascript">
	$(function() {
		$('#auditStatus').find(':radio[name="applicationStatus"]').on("click",
				function() {
					var $this = $(this);
					var ifShow = ($this.val() === 'refuse');
					showRefuseReason(ifShow);
		});
		function showRefuseReason(ifShow) {
			if (ifShow) {
				$("#refuseReasonContainer").show();
				$("#refuseReason").addClass("required");
			} else {
				$("#refuseReasonContainer").hide();
				$("#refuseReason").removeClass("required");
			}
		}
		showRefuseReason("refuse" === "${applicationStatus}");
		$('.control-group :input').prop("disabled","disabled");
		//<c:if test="${ifAudit}">
		$(':input.audit').prop("disabled","");
		//</c:if>
		$("#btnSubmit,#btnCancel").prop("disabled","");
		//<c:if test="${!ifAudit}">
		$("#btnSubmit").remove();
		//</c:if>
	});
</script>