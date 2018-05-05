<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="applicationStatus" type="java.lang.String"
	required="true" description="申请状态"%>
<div class="control-group">
	<label class="control-label"><spring:message
			code="CxPlateformApplication.applicationStatus" />：</label>
	<div class="controls">
		<form:select path="applicationStatus" class="required">
			<c:forEach items="${applicationStatusList}" var="status">
				<form:option value="${status }">
					<spring:message code="ApplicationStatus.${status }" />
				</form:option>
			</c:forEach>
		</form:select>
		<span class="help-inline"><font color="red">*</font> </span>
		<form:errors path="applicationStatus" cssStyle="color:red" />
	</div>
</div>
<div id="refuseReasonContainer" class="control-group hide">
	<label class="control-label"><spring:message
			code="CxPlateformApplication.refuseReason" />：</label>
	<div class="controls">
		<form:textarea path="refuseReason" htmlEscape="false" rows="4"
			maxlength="255" class="input-xlarge " />
		<span class="help-inline"><font color="red">*</font>
			请填写拒绝理由，以便申请人尽快通过审核</span>
		<form:errors path="refuseReason" cssStyle="color:red" />
	</div>
</div>
<script type="text/javascript">
	$(function() {
		$("#applicationStatus").on("change", function() {
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
	});
</script>